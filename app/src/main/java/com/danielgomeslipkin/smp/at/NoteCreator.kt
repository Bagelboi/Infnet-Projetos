package com.danielgomeslipkin.smp.at

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Base64.encodeToString
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.drawToBitmap
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.danielgomeslipkin.smp.at.data.FireAuth
import com.danielgomeslipkin.smp.at.data.Note
import com.danielgomeslipkin.smp.at.data.Utils
import com.danielgomeslipkin.smp.at.databinding.ActivityNoteCreatorBinding
import com.google.android.gms.common.util.Base64Utils
import com.google.firebase.auth.FirebaseAuth
import java.io.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


class NoteCreator : AppCompatActivity() {
    private lateinit var binding : ActivityNoteCreatorBinding
    private val locationCode = 1020
    private lateinit var locManager : Utils.Locationer
    private var imgPicker = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it is Uri) {
            binding.btnImgNote.setImageURI(it)
        }
    }
    private var imgPhoto = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        if (it is Bitmap) {
            binding.btnImgNote.setImageBitmap(it)
        }
    }
    private var camPerm = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) {
            imgPhoto.launch(null)
        } else
            requestPermissions(arrayOf<String>(Manifest.permission.CAMERA), 420)
    }
    private lateinit var fbAuth : FireAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteCreatorBinding.inflate(layoutInflater)

        setupFirebase()


        binding.btnImgNoteFolder.setOnClickListener {
            imgPicker.launch("image/*")
        }

        binding.btnImgNoteCamera.setOnClickListener {
            camPerm.launch(Manifest.permission.CAMERA)
        }

        binding.btnCreateNote.setOnClickListener {
            val title = binding.itxtTitle.text.toString()
            val content = binding.itxtContent.text.toString()
            if (title.isNotEmpty()) {
                val noteDir = obterDiretorio("notas", true)
                if (noteDir.exists()) {
                    val nota = gravarNota(title + getDateTitleStr(), content)
                }
            }

            this.finish()
        }


        setContentView(binding.root)

        locManager = Utils.Locationer(this, binding.root, locationCode, false)

        binding.btnLocGet.setOnClickListener {
            locManager = Utils.Locationer(this, binding.root, locationCode, true)
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

            locManager = Utils.Locationer(this, binding.root, locationCode, false)
        }
    }

    private fun setupFirebase() {
        fbAuth = FireAuth(FirebaseAuth.getInstance(), this)
    }
    private fun getEmail() : String {
        val user = fbAuth.auth.currentUser?.email
        if (user != null ) {
            return user
        }
        return ""
    }



    /*
    private fun obterNotas():List<File>{
        var diretorio = obterDiretorio("notas",true)
        return diretorio.listFiles().filter {
                t -> t.name.endsWith(".note")
        }
    }
     */

    fun obterDiretorio(diretorio: String, criar: Boolean):File{
        var dirArq = getExternalFilesDir(null)!!.path + "/" + diretorio
        var dirFile = File(dirArq)
        if(!dirFile.exists()&&(!criar||!dirFile.mkdirs()))
            throw Exception("Diretório indisponível")
        return dirFile
    }

    fun removerDiretorio(diretorio: String){
        var dirArq = getExternalFilesDir(null)!!.path + "/" + diretorio
        var dirFile = File(dirArq)
        if(!dirFile.exists() || !dirFile.isDirectory ||
            !dirFile.listFiles().isEmpty())
            throw Exception("Não é possível remover")
        dirFile.delete()
    }

    private fun gravarNota(nome:String, texto: String): File{
        var diretorio = obterDiretorio("notas",false)

        val content = getNoteContent(texto,
            nome,
            Utils.upperCaseFirstLetter("${getLocation()} em ${getDateStr()}" ),
            binding.btnImgNote.drawToBitmap(Bitmap.Config.ARGB_8888),
            getEmail())

        val masterKeyAlias: String =
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        var nota = File(diretorio.path+"/"+ nome + ".txt")
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
    /*
    fun lerNota(nomeNota: String): String{
        var diretorio = obterDiretorio("notas",false)
        val masterKeyAlias: String =
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        var nota = File(diretorio.path+"/"+nomeNota)
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

    */

    private fun getNoteContent(text:String, title:String, date:String, img: Bitmap, email:String) : String {
        val baos = ByteArrayOutputStream()
        img.compress(Bitmap.CompressFormat.PNG, 90, baos)
        val b = Base64.getEncoder().encodeToString(baos.toByteArray())
        return Note(text, title, date, b , email).toString()
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

}

