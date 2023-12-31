package com.miu.droidtest.repo

import androidx.lifecycle.LiveData
import com.miu.droidtest.api.RetrofitAPI
import com.miu.droidtest.db.Art
import com.miu.droidtest.db.ArtDao
import com.miu.droidtest.models.ImageResponse
import com.miu.droidtest.utils.Resource
import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitAPI: RetrofitAPI
): ArtRepository {
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art = art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art = art)
    }

    override fun getArts(): LiveData<List<Art>> = artDao.observeArts()

    override suspend fun searchImage(artName: String): Resource<ImageResponse> {
        return try {
            val response = retrofitAPI.imageSearch(artName)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error!", null)
            }else{
                Resource.error("Error!", null)
            }
        } catch (e: Exception){
            Resource.error("No Data!", null)
        }

    }
}