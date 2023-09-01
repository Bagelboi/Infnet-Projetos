package com.danielgomeslipkin.smp.at

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.core.widget.addTextChangedListener
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.danielgomeslipkin.smp.at.data.Note
import com.danielgomeslipkin.smp.at.data.Utils
import com.danielgomeslipkin.smp.at.databinding.ActivityNoteCreatorBinding
import com.danielgomeslipkin.smp.at.databinding.ActivityNoteViewBinding
import java.io.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class NoteView : AppCompatActivity() {

    private lateinit var binding : ActivityNoteViewBinding
    private val locationCode = 1020
    private lateinit var locManager : Utils.Locationer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteViewBinding.inflate(layoutInflater)

        setContentView(binding.root)


        locManager = Utils.Locationer(this, binding.root, locationCode, false)


        binding.btnNoteViewSave.hide()

        val args1 = intent.getStringExtra("content")
        val args2 = intent.getByteArrayExtra("img")
        val args3 = intent.getStringExtra("filepath")

        if (args1 != null) {
            val content = args1.split(Note.sep)

            binding.txtTitle.setText(content[0])
            binding.txtDatePlace.setText(content[1])
            binding.txtContent.setText(content[2])
        }

        if (args2 != null) {
            binding.imageView.setImageBitmap( BitmapFactory.decodeByteArray(args2, 0, args2.size) )
        }

        if (args3 != null) {
            binding.btnNoteViewSave.setOnClickListener {
                saveNota(File(args3))
            }

            binding.txtContent.addTextChangedListener( object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    binding.btnNoteViewSave.show()
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            } )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationCode) {
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(baseContext, "Permissoes de localização nao foram concedidas!", Toast.LENGTH_SHORT)
            }

            locManager.initialize()
        }
    }

    private fun obterNotas():List<File>{
        var diretorio = obterDiretorio("notas",true).listFiles()
        if (diretorio != null) {
            return diretorio.filter {
                    t -> t.name.endsWith(".txt")
            }
        }
        return listOf<File>()
    }

    fun obterDiretorio(diretorio: String, criar: Boolean): File {
        var dirArq = getExternalFilesDir(null)!!.path + "/" + diretorio
        var dirFile = File(dirArq)
        if(!dirFile.exists()&&(!criar||!dirFile.mkdirs()))
            throw Exception("Diretório indisponível")
        return dirFile
    }

    private fun saveNota(file:File) {
        if (file.exists()) {

            val noteData = Note.StrToNote(lerNota(file))
            if (noteData != null) {
                val note = Note(
                    binding.txtContent.text.toString(),
                    noteData.title,
                    "Editado : ${getLocation()} em ${getDateStr()}",
                    noteData.imgUri,
                    noteData.email,
                    noteData.filepath
                )
                removerArquivo(file)
                gravarNota(file, note)
                binding.txtDatePlace.setText(note.date)
                binding.btnNoteViewSave.hide()
            }

        }
    }

    private fun gravarNota(nota:File, note:Note): File{
        val content = note.toString()
        val masterKeyAlias: String =
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        var encryptedOut = EncryptedFile.Builder(
            nota, applicationContext, masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build().openFileOutput()
        val pw = PrintWriter(encryptedOut)
        pw.println(content)
        pw.flush()
        encryptedOut.close()
        return nota
    }

    fun removerArquivo(dirFile : File){
        if(!dirFile.exists() || !dirFile.isFile)
            throw Exception("Não é possível remover")
        dirFile.delete()
    }


    private fun getDateStr() : String {
        val cal = Calendar.getInstance()
        val today = LocalDateTime.now(ZoneId.systemDefault())
        cal.set(today.year, today.monthValue, today.dayOfMonth, today.hour, today.minute)
        return cal.time.toString()
    }

    private fun getDateTitleStr() : String {
        val cal = Calendar.getInstance()
        val today = LocalDateTime.now(ZoneId.systemDefault())
        cal.set(today.year, today.monthValue, today.dayOfMonth, today.hour, today.minute)
        return "(${today.dayOfMonth}-${today.month.value}-${today.year})"
    }

    private fun getLocation() : String = locManager.getAddress()



    fun lerNota(file:File): String{
        val masterKeyAlias: String =
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        var nota = file
        var encryptedIn = EncryptedFile.Builder(
            nota, applicationContext, masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build().openFileInput()
        val br = BufferedReader(InputStreamReader(encryptedIn))
        val sb = StringBuffer()
        br.lines().forEach{ t -> sb.append(t+"\n") }
        encryptedIn.close()
        return sb.toString()
    }

}