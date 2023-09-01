package com.example.charactermakerrpg

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import org.w3c.dom.Text
import java.net.URI
import kotlin.math.max
import kotlin.math.min

class CharacterListActivity : AppCompatActivity() {
    var charlist: MutableList<character> = mutableListOf<character>()
    lateinit var new_char: character
    var charindex: Int = 0
        set(value) {
            field = max(min(value, charlist.size - 1), 0)
        }
    //val gson = Gson()
    private lateinit var viewModel: ClassFluffViewModel
    private lateinit var savSys : saveSystem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        viewModel = ViewModelProvider(this).get(ClassFluffViewModel::class.java)

        savSys = saveSystem(applicationContext)
        charlist = savSys.loadChars()
        charlist.sortWith( ( compareBy({it.name}) ) )

        val newchar = intent.getStringExtra("character_new")
        //val charlist_i = intent.getStringArrayExtra("character_list")

        if (newchar != null) {
            //new_char = gson.fromJson(newchar, character::class.java)
            //charlist.add(new_char)
            charindex = charlist.indexOfFirst { it.name == newchar }
        }

        findViewById<Button>(R.id.btn_charselect_next).setOnClickListener {
            charindex += 1
            fillCharInfo()
            replaceSkillList()

        }

        findViewById<Button>(R.id.btn_charselect_back).setOnClickListener {
            charindex -= 1
            fillCharInfo()
            replaceSkillList()

        }

        findViewById<ImageButton>(R.id.btn_newchar).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java)/*.apply {
                val convertedCharlist = mutableListOf<String>()
                for (i in charlist)
                    convertedCharlist.add( savSys.char2Str(i) )
                putExtra("character_list", convertedCharlist.toTypedArray())
            }*/)
        }

        findViewById<ImageButton>(R.id.btn_removechar).setOnClickListener {
            if (charlist.size > 1 && savSys.deleteChar( charlist[charindex].name, charlist.toMutableList())) {
                val removeIndex = charindex
                charindex -= 1
                charlist.removeAt(removeIndex)
                fillCharInfo()
                replaceSkillList()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (charlist.size > 0) {
            fillCharInfo()
            replaceSkillList()
        }
    }

    fun replaceSkillList() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val new_frag = ClassSkillFragment()
            val args = Bundle()
            val cur_char = charlist[charindex]!!
            args.putInt("cindex", cur_char.class_index)
            args.putInt("clevel", cur_char.level.toInt())
            args.putIntegerArrayList("cskills_i", cur_char.getSkillsIndexList( viewModel.charClasses[cur_char.class_index].skills ) )
            new_frag.arguments = args
            replace(R.id.frag_charselect_skills, new_frag)
        }
    }

    fun fillCharInfo() {
        val char_info = charlist[charindex]
        val class_info = viewModel.charClasses[char_info.class_index]

        val classNameTxt = findViewById<TextView>(R.id.txt_class_name)
        classNameTxt.text = "[${char_info.level}]${char_info.name}"
        classNameTxt.setTextColor( getCharNameColor(char_info.level) )
        findViewById<TextView>(R.id.txt_class_desc).text = "Um(a) ${class_info.name} ${char_info.gender.lowercase()} ${char_info.getAgeSuffix()} com nivel ${char_info.level}."
        findViewById<ImageView>(R.id.img_class_logo).setImageResource(class_info.logo)
    }

    override fun onBackPressed() {
        findViewById<Button>(R.id.btn_charselect_back).performClick()
    }

    fun getCharNameColor(level : UInt) : Int {
        val compatLevel = level.toFloat()
        val HSV_array = floatArrayOf(
            max(0f , 180f - compatLevel*1.5f),
            min( 1f, compatLevel / 60f),
            if (level > 120U) max(0f, 1f - compatLevel/120f) else 1f
        )
        return Color.HSVToColor(HSV_array)
    }
}