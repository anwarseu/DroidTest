package com.miu.droidtest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.droidtest.R
import com.example.droidtest.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private lateinit var binding: FragmentGalleryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGalleryBinding.bind(view)
    }
}