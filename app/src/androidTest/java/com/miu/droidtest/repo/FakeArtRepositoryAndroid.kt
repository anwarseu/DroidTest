package com.miu.droidtest.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miu.droidtest.db.Art
import com.miu.droidtest.models.ImageResponse
import com.miu.droidtest.utils.Resource

class FakeArtRepositoryAndroid: ArtRepository {
    private val arts = mutableListOf<Art>()
    private val artsLiveData = MutableLiveData<List<Art>>(arts)

    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshLiveData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshLiveData()
    }

    override fun getArts(): LiveData<List<Art>> {
        return artsLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(), 0, 0))
    }

    private fun refreshLiveData() {
        artsLiveData.postValue(arts)
    }

}