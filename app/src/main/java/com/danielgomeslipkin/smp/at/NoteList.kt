package com.danielgomeslipkin.smp.at

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.danielgomeslipkin.smp.at.data.*
import com.danielgomeslipkin.smp.at.databinding.ActivityMainBinding
import com.danielgomeslipkin.smp.at.databinding.ActivityNoteListBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.*


class NoteList : AppCompatActivity() {
    private lateinit var binding : ActivityNoteListBinding
    private lateinit var fbAuth : FireAuth
    private var isPro = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteListBinding.inflate(layoutInflater)

        isPro = getPremium()

        setupFirebase()
        setupAds()
        reloadList()

        Utils.makeViewListVisibility( listOf(binding.adView, binding.btnBuyPremium), !isPro )

        binding.btnAddNote.setOnClickListener {
            startActivity( Intent( this, NoteCreator::class.java ) )
        }

        binding.btnBuyPremium.setOnClickListener {
            setPremium(true)
        }

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        reloadList()
    }

    private fun setupFirebase() {
        fbAuth = FireAuth(FirebaseAuth.getInstance(), this)
    }

    private fun setupAds() {
        if (!isPro)
            binding.adView.loadAd( AdRequest.Builder().build() )
    }


    private fun reloadList() {
        binding.recyclerNotes.adapter = NoteListAdapter( {note -> noteClicked(note)}, {note->noteRemoveClicked(note)} ).also {
            it.submitList( composeList(obterNotas()) )
        }
    }

    private fun getEmail() : String {
        val user = fbAuth.auth.currentUser?.email
        if (user != null ) {
            return user
        }
        return ""
    }

    private fun noteClicked(note:Note) {
        if (note.email == getEmail()) {
            val sep = Note.sep
            val content = "${note.title}$sep${note.date}$sep${note.content}"
            startActivity( Intent( this, NoteView::class.java )
                .putExtra("content", content)
                .putExtra("img", note.getByteArray())
                .putExtra("filepath", note.filepath))
        }
    }

    private fun noteRemoveClicked(note:Note) {
        if (note.filepath.isNotEmpty()) {
            val file = File(note.filepath)

            removerArquivo(file)
            reloadList()
        }
    }

    private fun composeList(files:List<File>) : List<Note> {
        val notes = mutableListOf<Note>()

        for (file in files) {
            if (file.isFile() && file.canRead()) {
                val content = Note.StrToNote( lerNota(file) ).also { it?.filepath = file.absolutePath }
                if (content != null && content.email == getEmail()) {
                    notes.add(content)
                }
            }
        }

        return notes
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

    fun obterDiretorio(diretorio: String, criar: Boolean):File{
        var dirArq = getExternalFilesDir(null)!!.path + "/" + diretorio
        var dirFile = File(dirArq)
        if(!dirFile.exists()&&(!criar||!dirFile.mkdirs()))
            throw Exception("Diretório indisponível")
        return dirFile
    }

    fun removerArquivo(dirFile : File){
        if(!dirFile.exists() || !dirFile.isFile)
            throw Exception("Não é possível remover")
        dirFile.delete()
    }

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

    fun getPremium() : Boolean {
        return this.getPreferences(Context.MODE_PRIVATE).getBoolean(getString(R.string.premium_key), false)
    }

    fun setPremium(boolean: Boolean) {
        val pro = this.getPreferences(Context.MODE_PRIVATE).edit()
            .putBoolean(getString(R.string.premium_key), boolean)
            .apply()
        startActivity(Intent(this, this::class.java))
        this.finish()
    }

    override fun onBackPressed() {
        Snackbar.make(binding.root, "Tem certeza que quer sair?", Snackbar.LENGTH_LONG).setAction("Sim") {
			fbAuth.signOut()
            this.finish()
        }.show()
    }

}