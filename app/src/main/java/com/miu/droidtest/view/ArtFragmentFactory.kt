package com.miu.droidtest.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.miu.droidtest.adapters.ArtAdapter
import com.miu.droidtest.adapters.ArtGalleryAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val artAdapter: ArtAdapter,
    private val glide: RequestManager,
    private val artGalleryAdapter: ArtGalleryAdapter
) : FragmentFactory() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ListFragment::class.java.name -> ListFragment(artAdapter)
            DetailsFragment::class.java.name -> DetailsFragment(glide)
            GalleryFragment::class.java.name -> GalleryFragment(artGalleryAdapter)
            FavoriteFragment::class.java.name -> FavoriteFragment()
            DataFragment::class.java.name -> DataFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}