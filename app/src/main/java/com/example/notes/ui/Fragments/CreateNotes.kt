package com.example.notes.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.notes.R
import com.example.notes.databinding.FragmentCreateNotesBinding
import com.example.notes.model.Notes
import com.example.notes.viewModel.NotesViewModel


class CreateNotes : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        //first we have to make binding for this createNotes fragment and then inflate the layout of the same fragment
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        binding.btnDone.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
//        val title=binding.title.text.toString()
//        val content=binding.content.text.toString()

//
//        var title = binding.title.text.toString()
//        var content = binding.content.text.toString()
//        val data: Notes =
//            Notes(null, title = title, content = content) //? add : Notes...yes working
//        viewModel.insertNotes(data)

        //adding 1000 notes check
        var title=""
        var content=""
        val charset=('a'..'z')
        var i=1000
        while(i>=0) {
            title=(1..10).map{charset.random()}.joinToString("")
            content=(1..30).map{charset.random()}.joinToString("")
            val data: Notes =
                Notes(null, title = title, content = content) //? add : Notes...yes working
            viewModel.insertNotes(data)
            i--
        }


        Toast.makeText(context, "Note created successfully", Toast.LENGTH_SHORT).show()

        if (it != null) {
            Navigation.findNavController(it).navigate(R.id.action_createNotes2_to_homeFragment)
        }
    }

}