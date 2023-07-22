package com.miu.droidtest.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.droidtest.R
import com.example.droidtest.databinding.FragmentDataBinding

class DataFragment : Fragment(R.layout.fragment_data) {

    private lateinit var binding:FragmentDataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDataBinding.bind(view)
    }

}