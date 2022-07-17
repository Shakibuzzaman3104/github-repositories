package com.shakibuzzaman.githubrepos.di

import android.app.Application
import android.app.NotificationManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.viewbinding.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.shakibuzzaman.githubrepos.Constants
import com.shakibuzzaman.githubrepos.db.GithubRepositoryDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNetworkRequest(): NetworkRequest {
        return NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
    }


   /* @Singleton
    @Provides
    fun providesPreferenceStorage(application: Application): PreferenceStorage {
        return DataStoreManager(application)
    }*/

    @RequiresApi(Build.VERSION_CODES.M)
    @Singleton
    @Provides
    fun provideNotificationManager(application: Application): NotificationManager {
        return application.getSystemService(NotificationManager::class.java)
    }


    @Provides
    fun provideNetworkInterceptor(): OkHttpClient {
        val build = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES).addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                val requestBuilder: Request.Builder =
                    original
                        .newBuilder()
                        .method(original.method, original.body)
                val request = requestBuilder.build()
                chain.proceed(request)
            }

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            build.addInterceptor(httpLoggingInterceptor)
        }

        return build.build()
    }



   /* @Singleton
    @Provides
    fun provideDataStore(application: Application): DataStoreManager =
        DataStoreManager(application.applicationContext)*/


    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

/*    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.color.color_alice_blue)
            .error(R.color.colorPrimary)
    }*/


    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }




    @Singleton
    @Provides
    fun provideRoomInstance(application: Application): GithubRepositoryDb {
        return GithubRepositoryDb.getInstance(application)
    }


}