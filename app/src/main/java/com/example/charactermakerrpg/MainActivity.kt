package com.example.charactermakerrpg

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.charactermakerrpg.databinding.ActivityMainBinding
import com.example.charactermakerrpg.databinding.ClassFluffFragmentBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

// NOTA : O arquivo xml utiliza strings, veja as EditText views

class MainActivity : AppCompatActivity() {
    lateinit var btn : Button
    lateinit var viewModel : ClassFluffViewModel
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var charLogoLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            findViewById<ImageView>(R.id.logo).setImageURI(uri)
        }
        findViewById<Button>(R.id.btn_logo).setOnClickListener {
            charLogoLauncher.launch("image/*")
        }

        viewModel = ViewModelProvider(this).get(ClassFluffViewModel::class.java)

        var charList = saveSystem(applicationContext).loadChars()//intent.getStringArrayExtra("character_list")
        findViewById<ImageView>(R.id.logo).setOnClickListener {
            if (charList != null && charList.size > 0) {
                startActivity(Intent(this, CharacterListActivity::class.java)/*.apply {
                    putExtra("character_list", charList)
                }*/)
                finish()
            }
            else
                Toast.makeText(this,"Nenhum personagem foi criado ainda!", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btn_info).setOnClickListener {
            startActivity(Intent(this, CreditsActivity::class.java))
        }

        btn = findViewById(R.id.btn_classmenu)


        btn.setOnClickListener {
            var textAge = findViewById<EditText>(R.id.txt_age).text.toString()
            var textGender = findViewById<EditText>(R.id.txt_gender).text.toString()
            var textLevel = findViewById<EditText>(R.id.txt_level).text.toString()
            var textName = findViewById<EditText>(R.id.txt_name).text.toString()

            if (textAge.length < 1 ||
                textGender.length < 1 ||
                textLevel.length < 1 ||
                textName.length < 1) {
                    Toast.makeText(this, "Os campos nao foram preenchidos corretamente!", Toast.LENGTH_SHORT).show()
            }
            else {
                startActivity( Intent(this, ClassActivity::class.java).apply {
                    putExtra("name", textName)
                    putExtra("gender", textGender)
                    putExtra("level", textLevel.toInt())
                    putExtra("age", textAge.toInt())
                    //putExtra("character_list", charList)
                })

            }

        }
    }



}