package com.example.mars_photos.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mars_photos.R
import com.example.mars_photos.data.MarsPhotosArrayDto
import com.example.mars_photos.databinding.FragmentMarsPhotosBinding
import com.example.mars_photos.di.DaggerAppComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MarsPhotosFragment : Fragment() {

    private var _binding: FragmentMarsPhotosBinding?=null
    val binding get() = _binding!!

    val viewModel: MarsPhotosViewModel by viewModels{
        DaggerAppComponent.create().marsPhotosViewModelFactory()
    }
    private val marsPhotosAdapter=MarsPhotosAdapter()
    private val marsPhotosListAdapter=MarsPhotosListAdapter{ onItemClick(it) }

    var linkForGlide:String?=null

    companion object {
        fun newInstance() = MarsPhotosFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadPhotos()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=FragmentMarsPhotosBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel=viewModel
        binding.lifecycleOwner=viewLifecycleOwner

        binding.recyclerView.adapter=marsPhotosListAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.marsPhotos.onEach {
                marsPhotosListAdapter.submitList(it.toList())
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }

    fun onItemClick(item: MarsPhotosArrayDto){
        findNavController().navigate(
            R.id.action_MarsPhotosListFragment_to_FullPhotoScreenFragment,
            Bundle().apply {
                putString("img_src",item.img_src)
            })
    }



}