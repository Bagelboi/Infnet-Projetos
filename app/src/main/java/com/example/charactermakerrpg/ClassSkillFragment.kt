package com.example.charactermakerrpg

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.charactermakerrpg.placeholder.PlaceholderContent
import kotlin.math.max
import kotlin.math.min

/**
 * A fragment representing a list of Items.
 */
class ClassSkillFragment : Fragment() {
    private var columnCount = 1
    private lateinit var viewModel: ClassFluffViewModel
    private lateinit var skillRecycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_class_skill_list, container, false)
        viewModel = ViewModelProvider(this).get(ClassFluffViewModel::class.java)

        var cindex = 0
        var clevel = 0
        var cskills = arrayListOf<Int>()
        arguments.let {
            val new_index = it?.getInt("cindex")
            val new_level = it?.getInt("clevel")
            val cskills_i = it?.getIntegerArrayList("cskills_i")
            if (new_index != null)
                cindex = new_index
            if (new_level !=null)
                clevel = new_level
            if (cskills_i != null)
                cskills = cskills_i
        }

        // Set the adapter
        val adapt_list = viewModel.charClasses[cindex].skills
        var new_adapt_list = adapt_list
        if (cskills.size > 0)
        {
            new_adapt_list = mutableListOf<charskill>()
            for (i in cskills)
                new_adapt_list.add(adapt_list[i])
        }

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                adapter = MyClassSkillRecyclerViewAdapter(new_adapt_list).also {
                    it.charlevel = clevel
                }


            }
        }

        return view
    }


}