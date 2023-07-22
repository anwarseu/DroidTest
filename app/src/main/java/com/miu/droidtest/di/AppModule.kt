package com.miu.droidtest.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.miu.droidtest.R
import com.miu.droidtest.api.RetrofitAPI
import com.miu.droidtest.db.ArtDao
import com.miu.droidtest.db.ArtDatabase
import com.miu.droidtest.repo.ArtRepository
import com.miu.droidtest.repo.ArtRepositoryImpl
import com.miu.droidtest.utils.Utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context = context, klass = ArtDatabase::class.java, name = "droidDB").build()

    @Singleton
    @Provides
    fun provideDao(artDatabase: ArtDatabase) = artDatabase.artDao()

    @Singleton
    @Provides
    fun provideRetrofitAPI() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).build().create(RetrofitAPI::class.java)

    @Singleton
    @Provides
    fun provideArtRepository(artDao: ArtDao, retrofitAPI: RetrofitAPI) = ArtRepositoryImpl(artDao, retrofitAPI) as ArtRepository

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context: Context) = Glide
        .with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )

}