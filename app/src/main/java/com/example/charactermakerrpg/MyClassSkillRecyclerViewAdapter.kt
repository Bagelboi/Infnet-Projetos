package com.example.charactermakerrpg

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

import com.example.charactermakerrpg.placeholder.PlaceholderContent.PlaceholderItem
import com.example.charactermakerrpg.databinding.FragmentClassSkillBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyClassSkillRecyclerViewAdapter(
    private val skills : List<charskill>
) : RecyclerView.Adapter<MyClassSkillRecyclerViewAdapter.ViewHolder>() {

    var charlevel = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentClassSkillBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = skills[position]
        holder.skNameView.text = item.name
        holder.skDescView.text = item.desc
        holder.skLevelView.text = item.level.toString()
        holder.skTypeView.setImageResource( getSkillTypeDrawable(item.type) )

        if (charlevel >= item.level.toInt())
        {
            Log.i("ComparasionError", "$charlevel == ${item.level.toInt()}")
            holder.skLearnView.isChecked = true
        }
        else
            holder.skLearnView.isChecked = false
    }

    override fun getItemCount(): Int = skills.size

    inner class ViewHolder(binding: FragmentClassSkillBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val skNameView: TextView = binding.txtSkillName
        val skLevelView: TextView = binding.txtSkillLevel
        val skDescView: TextView = binding.txtSkillDesc
        val skTypeView: ImageView = binding.imgSkillType
        val skLearnView : CheckBox = binding.btnCheckskill

        //override fun toString(): String {
        //    return super.toString() + " '" + skLevel.text + "'"
       // }
    }

}