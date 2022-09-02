package com.example.notes.ui.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.Downsampler
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.notes.R
import com.example.notes.databinding.ItemNotesBinding
import com.example.notes.model.Notes
import com.example.notes.model.Users
import com.example.notes.ui.Fragments.HomeFragmentDirections


@GlideModule
class AppNameGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.apply { RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL) }
    }

}


//add val so that we can use them
class NotesAdapter(
    val requireContext: Context,
    val notesList: List<Notes>,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val allDeleteInterface: AllDeleteInterface,
) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {


    class notesViewHolder(val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) { //inner class // others use 'itemView' instead of 'binding.root'
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): notesViewHolder { //return view
        var itemView = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return notesViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) { //set data

        RequestOptions().set(Downsampler.ALLOW_HARDWARE_CONFIG, true)

        holder.binding.notesTitle.text = notesList[position].title
        holder.binding.notesContent.text = notesList[position].content


//        var img: String =
//            "https://unsplash.com/photos/I6WugeyPcWE/download?ixid=MnwxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjYxNTAxMzky&force=true"
//
//        if (holder.binding.photo1.context != null) {
//            GlideApp.with(holder.binding.photo1.context)
//                .load(R.drawable.img9mb)
//                .placeholder(R.drawable.loading) //holder.binding should be used
//                .error(R.drawable.failed)
//                .into(holder.binding.photo1)
//        }

//        else {
//            img =
//                "https://cdn.pixabay.com/photo/2018/05/03/21/49/android-3372580_1280.png"
//            GlideApp.with(holder.binding.photo.context).load(img).placeholder(R.drawable.loading)
//                .error(R.drawable.failed)
//                .circleCrop()
//                .transition(withCrossFade())
//                .into(holder.binding.photo)
//        }

//        holder.binding.photo.setOnClickListener {
//            run {
//                val intent = Intent(context, FullScreenImage().javaClass)
//                intent.putExtra("fullImageUrl", imageUrls.fullImageUrl)
//                context.startActivity(intent)
//            }
//        }

//        //using glide
//        GlideApp.with(holder.binding.photo.context).load(img).placeholder(R.drawable.loading)
//            .error(R.drawable.failed)
//            .into(holder.binding.photo)

        //passing data to EditNotes Fragment
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotes(notesList[position])
            Navigation.findNavController(it).navigate(action)//put your custom action here
        }

        holder.binding.btnDel.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(notesList[position])
        }

        allDeleteInterface.allDelete(notesList)
    }

    override fun getItemCount() = notesList.size

    interface NoteClickDeleteInterface {
        // creating a method for click
        // action on delete image view.
        fun onDeleteIconClick(notes: Notes)
    }

    interface AllDeleteInterface {
        fun allDelete(allNotes: List<Notes>)
    }

}