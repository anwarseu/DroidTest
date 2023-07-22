package com.miu.droidtest.viewmodel

import com.miu.droidtest.repo.FakeArtRepository
import org.junit.Before

class ArtViewModelTest {

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup(){
        viewModel = ArtViewModel(FakeArtRepository())
    }
}