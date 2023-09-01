package com.example.charactermakerrpg

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.FileWriter

const val SAVE_FILE = "mikemmo.csv";
const val SAVE_FILE_SEP = '¬'
class saveSystem(_context: Context) {
    var context : Context = _context

    fun saveChar(savechar : character, char_list:MutableList<character>) {
        val file = File(context.filesDir, SAVE_FILE)
        if (file.exists()) {
            Log.d("savesys", "saving character")
            var char_i = -1
            for (i in char_list.indices) { //checa persona igual
                val cur_char = char_list[i]
                if (cur_char.name == savechar.name) {
                    char_i = i //tem persona igual
                    break;
                }
            }
            if (char_i > -1) {
                val chars_parsed = mutableListOf<String>()
                char_list[char_i] = savechar
                char_list.forEach { chars_parsed.add(this.char2Str(it)) }
                file.writeText(chars_parsed.joinToString("\n"))
            } else {// add persona
                    val out = BufferedWriter(FileWriter(file, true))
                    out.write("\n" + this.char2Str(savechar))
                    out.close()
                }
        } else { //add persona e arquivo
            Log.d("savesys", "saving character new file")
            file.createNewFile()
            file.writeText( this.char2Str(savechar) )
        }
        Log.d("savesys", this.char2Str(savechar))
        Toast.makeText(this.context, "Personagens salvo com sucesso.", Toast.LENGTH_SHORT)
    }

    fun char2Str(char:character) : String {
        return arrayOf( char.name, char.gender, char.level.toString(), char.age.toString(), char.class_index.toString() ).joinToString(
            SAVE_FILE_SEP.toString())
    }

    fun parseLine(str:String) : character {
        val chardata = str.split(SAVE_FILE_SEP)
        return character(chardata[0],chardata[1],chardata[2].toUInt(),chardata[3].toUInt(),chardata[4].toInt())
    }

    fun loadChars() : MutableList<character> {
        val file = File(context.filesDir, SAVE_FILE)
        val chars = mutableListOf<character>()
        if (file.exists()) {
            file.forEachLine{ charstr ->
                Log.d("savesys_cock", charstr)
                if (charstr.length > 2)
                    chars.add(this.parseLine(charstr))
            }
            Toast.makeText(this.context, "Personagens carregados com sucesso.", Toast.LENGTH_SHORT)
        } else
            file.createNewFile()
        Log.d("savesys", chars.toString())
        return chars
    }

    fun deleteChar(charname : String, char_list: MutableList<character> = this.loadChars()) : Boolean {
        val file = File(context.filesDir, SAVE_FILE)
        if (file.exists()) {
            var char_i = -1
            for (i in char_list.indices) { //checa persona igual
                val cur_char = char_list[i]
                if (cur_char.name == charname) {
                    char_i = i //tem persona igual
                    break;
                }
            }
            if (char_i > -1) {
                val chars_parsed = mutableListOf<String>()
                char_list.removeAt(char_i)
                char_list.forEach { chars_parsed.add(this.char2Str(it)) }
                file.writeText(chars_parsed.joinToString("\n"))
                return true
            }
        }
        return false
    }
}