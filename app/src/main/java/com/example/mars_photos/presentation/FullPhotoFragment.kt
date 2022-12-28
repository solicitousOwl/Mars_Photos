package com.example.mars_photos.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mars_photos.R
import com.example.mars_photos.databinding.FragmentFullPhotoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "img_src"


/**
 * A simple [Fragment] subclass.
 * Use the [FullPhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FullPhotoFragment : Fragment() {

    private var _binding: FragmentFullPhotoBinding? = null
    val binding get() = _binding!!


    // TODO: Rename and change types of parameters
    private var param1: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentFullPhotoBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("aslan555", "FINAL:$param1 ")
        param1?.let {


            val nasaLink=if (param1!!.contains("http://mars.jpl.nasa.gov")){
                val temp=param1?.removePrefix("http://mars.jpl.nasa.gov")
                "https://mars.nasa.gov$temp"
            }else{
                param1
            }

            Glide
                .with(binding.FullScreenImageView.context)
                .load(nasaLink)
                .into(binding.FullScreenImageView)

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FullPhotoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FullPhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)

                }
            }
    }
}