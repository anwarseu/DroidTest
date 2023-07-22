package com.miu.droidtest.repo

import androidx.lifecycle.LiveData
import com.example.droidtest.db.Art
import com.example.droidtest.models.ImageResponse
import com.example.droidtest.utils.Resource

interface ArtRepository {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArts(): LiveData<List<Art>>

    suspend fun searchImage(artName: String): Resource<ImageResponse>

}