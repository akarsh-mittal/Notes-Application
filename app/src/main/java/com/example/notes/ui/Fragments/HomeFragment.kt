package com.example.notes.ui.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notes.R
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.model.Notes
import com.example.notes.ui.Adapter.NotesAdapter
import com.example.notes.viewModel.NotesViewModel
import com.example.notes.viewModel.UsersViewModel


class HomeFragment : Fragment(), NotesAdapter.NoteClickDeleteInterface, NotesAdapter.AllDeleteInterface {

    lateinit var binding: FragmentHomeBinding //declare variables that are guaranteed to be initialised in future
    val viewModel: NotesViewModel by viewModels()
    val viewModel2: UsersViewModel by viewModels()
    private lateinit var navController: NavController

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        //first we have to make view binding for this home fragment and inflate the layout of the same fragment
        //same will be done for other two fragments
        binding=FragmentHomeBinding.inflate(layoutInflater, container, false)

//        var toast = Toast.makeText(context, "Click the PLUS icon to make a new note!", Toast.LENGTH_LONG)
//        toast.show()



        // we can observe the data coz live data, we will get a list of notes
        viewModel.getNotes().observe(viewLifecycleOwner, {

            for(i in it) { // use to check if data is storing properly in local database
                i.title?.let { it1 -> Log.i("###", it1) }
            }

//            // deleteall button is gone (from xml file) ..remove comments below to make visible
//            if(true) {
//                binding.btnDelAll.setVisibility(View.VISIBLE)
//            }

            binding.rcvAllNotes.layoutManager=GridLayoutManager(requireContext(), 1)
            binding.rcvAllNotes.adapter=NotesAdapter(requireContext(), it, this, this)
        })

        viewModel2.getUsers().observe(viewLifecycleOwner, {

            for(i in it) { // use to check if data is storing properly in local database
                i.mobile?.let { it1 -> Log.e("###", it1) }
            }

//            // deleteall button is gone (from xml file) ..remove comments below to make visible
//            if(true) {
//                binding.btnDelAll.setVisibility(View.VISIBLE)
//            }

//            binding.rcvAllNotes.layoutManager=GridLayoutManager(requireContext(), 1)
//            binding.rcvAllNotes.adapter=NotesAdapter(requireContext(), it, this, this)
        })



        binding.btnAdd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotes2)
            //navigate-> kaha se kaha jaana hai, this id automatically creating when we created our nav_graph
        }

        binding.btnProfile.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_register)
        }


        return binding.root

    }

    override fun onDeleteIconClick(notes: Notes) {
        viewModel.deleteNotes(notes)
        Toast.makeText(context, "Note Deleted!", Toast.LENGTH_SHORT).show()
    }

    override fun allDelete(allNotes: List<Notes>) {
        binding.btnDelAll.setOnClickListener {
            viewModel.deleteAllNotes(allNotes)
            Toast.makeText(context, "All Notes Deleted!", Toast.LENGTH_SHORT).show()
        }
    }
}