package com.example.charactermakerrpg

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlin.math.max
import kotlin.math.min

class ClassActivity : AppCompatActivity() {
    lateinit var btn_finish : Button
    lateinit var btn_next : Button
    lateinit var btn_back : Button
    lateinit var frag_skills : ClassSkillFragment

    private lateinit var viewModel: ClassFluffViewModel

    var cindex : Int = 0
        set(value) { field = max(min(value, viewModel.charClasses.size - 1), 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)
        viewModel = ViewModelProvider(this).get(ClassFluffViewModel::class.java)

        frag_skills = supportFragmentManager.findFragmentById(R.id.frag_classskills) as ClassSkillFragment

        btn_next = findViewById<Button>(R.id.btn_class_next)
        btn_back = findViewById<Button>(R.id.btn_class_back)
        btn_finish = findViewById<Button>(R.id.btn_finish)

        viewModel.charFluffName.value = intent.getStringExtra("name")
        viewModel.charFluffGender.value = intent.getStringExtra("gender")
        viewModel.charFluffAge.value = intent.getIntExtra("age", 0).toUInt()
        viewModel.charFluffLevel.value = intent.getIntExtra("level", 0).toUInt()

        viewModel.charCurClass.value = cindex
        viewModel.charCurClass.observe(this, Observer {
            val class_info = viewModel.charClasses[cindex]
            findViewById<TextView>(R.id.txt_class_name).text = class_info.name
            findViewById<TextView>(R.id.txt_class_desc).text =  class_info.desc
            findViewById<ImageView>(R.id.img_class_logo).setImageResource( class_info.logo )
        })

        if (frag_skills != null) {
            btn_next.setOnClickListener {
                cindex += 1
                viewModel.charCurClass.value = cindex
                replaceSkillList()
            }
            btn_back.setOnClickListener {
                cindex -= 1
                viewModel.charCurClass.value = cindex
                replaceSkillList()
            }
        }
        btn_finish.setOnClickListener {
            startActivity(Intent(this, CharacterListActivity::class.java).apply {
                val new_char = character(
                    viewModel.charFluffName.value!!,
                    viewModel.charFluffGender.value!!,
                    viewModel.charFluffLevel.value!!.toUInt(),
                    viewModel.charFluffAge.value!!.toUInt(),
                    viewModel.charCurClass.value!!)
                val joe = saveSystem(applicationContext)
                joe.saveChar(new_char, joe.loadChars())
                putExtra("character_new", new_char.name)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            })
            finish()
        }

        replaceSkillList()
    }

    fun replaceSkillList() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val new_frag = ClassSkillFragment()
            val args = Bundle()
            args.putInt("cindex", cindex)
            args.putInt("clevel", viewModel.charFluffLevel.value!!.toInt() )
            new_frag.arguments = args
            replace(R.id.frag_classskills, new_frag)
        }
    }

    override fun onBackPressed() {
        if (cindex > 0)
            findViewById<Button>(R.id.btn_class_back).performClick()
        else
            super.onBackPressed()
    }
}