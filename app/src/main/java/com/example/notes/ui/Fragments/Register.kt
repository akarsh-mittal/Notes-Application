package com.example.notes.ui.Fragments

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import coil.load
import coil.transform.CircleCropTransformation
import com.example.notes.R
import com.example.notes.databinding.FragmentCreateNotesBinding
import com.example.notes.databinding.FragmentRegisterBinding
import com.example.notes.model.Users
import com.example.notes.viewModel.NotesViewModel
import com.example.notes.viewModel.UsersViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener

@Suppress("DEPRECATION")
class Register: Fragment() {

    lateinit var binding : FragmentRegisterBinding
    val viewModel: UsersViewModel by viewModels()
    private val CAMERA_REQUEST_CODE = 1
    private val GALLERY_REQUEST_CODE = 2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        //first we have to make binding for this createNotes fragment and then inflate the layout of the same fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)


        //when we click on profile
        binding.profilePic.setOnClickListener {
            val pictureDialog = AlertDialog.Builder(binding.profilePic.context)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItem = arrayOf("Select photo from Gallery",
                "Capture photo from Camera")
            pictureDialog.setItems(pictureDialogItem) { dialog, which ->

                when (which) {
                    0 -> gallery()//when we click on "Select photo from Gallery"
                    1 -> cameraCheckPermission()//when we click on "Capture photo from Camera"
                }
            }
            pictureDialog.show()
        }

        binding.registerBtn.setOnClickListener{
            createUsers(it)
            Navigation.findNavController(it).navigate(R.id.action_register_to_homeFragment)
        }

        return binding.root
    }

    private fun createUsers(it: View?) {

        var name=binding.profileName.text.toString()
        var mobile=binding.profileMobileNumber.text.toString()

        val data: Users =
            Users(null,name=name, mobile=mobile)
        viewModel.insertUsers(data)

    }

//    private fun galleryCheckPermission() {
//
//        Dexter.withContext(context).withPermission(
//            android.Manifest.permission.READ_EXTERNAL_STORAGE
//        ).withListener(object : PermissionListener {
//            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
//                gallery()
//            }
//
//            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
//                Toast.makeText(
//                    context,
//                    "You have denied the storage permission to select image",
//                    Toast.LENGTH_SHORT
//                ).show()
////                showRotationalDialogForPermission()
//            }
//
//            override fun onPermissionRationaleShouldBeShown(
//                p0: com.karumi.dexter.listener.PermissionRequest?,
//                p1: PermissionToken?
//            ) {
////                showRotationalDialogForPermission()
//            }
//        }).onSameThread().check()
//    }

    private fun gallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }


    private fun cameraCheckPermission() {

        Dexter.withContext(context)
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA).withListener(

                object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        report?.let {

                            if (report.areAllPermissionsGranted()) {
                                camera()
                            }

                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?) {
                        showRotationalDialogForPermission()
                    }

                }
            ).onSameThread().check()
    }


    private fun camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            when (requestCode) {

                CAMERA_REQUEST_CODE -> {

                    val bitmap = data?.extras?.get("data") as Bitmap

                    //we are using coroutine image loader (coil)
                    binding.profilePic.load(bitmap) {
                        crossfade(true)
                        crossfade(1000)
                        transformations(CircleCropTransformation())
                    }
                }

                GALLERY_REQUEST_CODE -> {

                    binding.profilePic.load(data?.data) {
                        crossfade(true)
                        crossfade(1000)
                        transformations(CircleCropTransformation())
                    }

                }
            }

        }

    }


    private fun showRotationalDialogForPermission() {
        AlertDialog.Builder(requireContext())
            .setMessage("It looks like you have turned off permissions"
                    + "required for this feature. It can be enable under App settings!!!")

            .setPositiveButton("Go TO SETTINGS") { _, _ ->

                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package","com.example.notes", null)
                    intent.data = uri
                    startActivity(intent)

                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }

            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }


}