package com.miu.droidtest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.miu.droidtest.R
import com.miu.droidtest.adapters.ArtGalleryAdapter
import com.miu.droidtest.databinding.FragmentGalleryBinding
import com.miu.droidtest.utils.Status
import com.miu.droidtest.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GalleryFragment @Inject constructor(
    private val artGalleryAdapter: ArtGalleryAdapter
): Fragment(R.layout.fragment_gallery) {

    private lateinit var viewModel: ArtViewModel
    private lateinit var binding: FragmentGalleryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGalleryBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        var job: Job? = null

        binding.edtSearch.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.searchForImage(it.toString())
                    }
                }
            }
        }

        subscribeToObservers()

        binding.rvGallery.adapter = artGalleryAdapter
        binding.rvGallery.layoutManager = GridLayoutManager(requireContext(),3)

        artGalleryAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }
    }

    private fun subscribeToObservers() {
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    val urls = it.data?.hits?.map { imageResult ->  imageResult.previewURL }
                    artGalleryAdapter.images = urls ?: listOf()
                    binding?.progressBar?.visibility = View.GONE

                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error", Toast.LENGTH_LONG).show()
                    binding?.progressBar?.visibility = View.GONE

                }

                Status.LOADING -> {
                    binding?.progressBar?.visibility = View.VISIBLE

                }
            }

        })
    }
}