package com.example.notes.ui.Fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.R.menu
import com.example.notes.databinding.FragmentEditNotesBinding
import com.example.notes.model.Notes
import com.example.notes.viewModel.NotesViewModel
import android.view.Menu
import android.view.MenuItem
import com.example.notes.ui.Adapter.GlideApp

class EditNotes : Fragment(){


    val viewModel: NotesViewModel by viewModels()
    val oldnotes by navArgs<EditNotesArgs>()
    lateinit var binding:FragmentEditNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //first we have to make binding for this editNotes fragment , then inflate the layout of the same fragment
        binding=FragmentEditNotesBinding.inflate(layoutInflater, container, false)


//        var img:String=""
//        if((oldnotes.data.id)!! % 2==0) {
//            img="https://commondatastorage.googleapis.com/codeskulptor-demos/riceracer_assets/img/car_1.png"
//            GlideApp.with(
//            )
//        }

        var img:String="https://commondatastorage.googleapis.com/codeskulptor-demos/riceracer_assets/img/tree_3.png"

        if(binding.photo2.context !=null) {
        GlideApp.with(binding.photo2.context) //binding should be used
            .load(img)
            .placeholder(R.drawable.loading)
            .error(R.drawable.failed)
            .into(binding.photo2)
        }

//        else  {
//            img="https://commondatastorage.googleapis.com/codeskulptor-demos/riceracer_assets/img/car_1.png"
//            GlideApp.with(binding.photo2.context)
//                .load(img)
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.failed)
//                .into(binding.photo2)
//        }

        binding.edtTitle.text = Editable.Factory.getInstance().newEditable(oldnotes.data.title)
        //either of the up down method can be used to set data in edittext views in editnotes fragement
        binding.edtContent.setText(oldnotes.data.content)

        binding.btnEdtDone.setOnClickListener {
            updatesNotes(it)
        }
        binding.btnEdtDel.setOnClickListener {
            viewModel.deleteNotes(oldnotes.data)
            Navigation.findNavController(it).navigate(R.id.action_editNotes_to_homeFragment)
            Toast.makeText(context, "Note Deleted!", Toast.LENGTH_SHORT).show()
        }

        return binding.root

    }

    private fun updatesNotes(it: View?) {
        val edtTitle=binding.edtTitle.text.toString()
        val edtContent=binding.edtContent.text.toString()

        //id as null can't be used for update notes, for create notes it was auto incrementing, but here specific id position is to sent
        val data = Notes(oldnotes.data.id, title=edtTitle, content=edtContent)


        //passing data to viewmodel
        viewModel.updateNotes(data)

        Toast.makeText(context, "Note Updated Successfully", Toast.LENGTH_SHORT).show()

        if (it != null) {
            Navigation.findNavController(it).navigate(R.id.action_editNotes_to_homeFragment)
        }


    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.delete_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }



//    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//        menuInflater.inflate(R.menu.delete_menu, menu)
//    }
//
//    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//        if(menuItem.itemId==R.id.menu_delete) {
//            Log.e("@@@", "onOptionsItemsSelected: Delete")
//        }
//
//        return true
//    }

//    override fun onCreateOptionsMenu(menu: Menu?):Boolean {
//        val menuInflater=menuInflater
//        menuInflater.inflate(R.menu.delete_menu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }

}