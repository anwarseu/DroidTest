package com.miu.droidtest.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miu.droidtest.db.Art
import com.miu.droidtest.models.ImageResponse
import com.miu.droidtest.utils.Resource

class FakeArtRepository : ArtRepository {

    private val arts = mutableListOf<Art>()
    private val artsLiveData = MutableLiveData<List<Art>>(arts)

    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshData()
    }

    override fun getArts(): LiveData<List<Art>> {
        return artsLiveData
    }

    override suspend fun searchImage(artName: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(), 0, 0))
    }

    private fun refreshData(){
        artsLiveData.postValue(arts)
    }
}