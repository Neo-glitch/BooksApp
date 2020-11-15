package com.neo.booksrxjava.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object{
        val baseURL = "https://www.googleapis.com/books/v1/"  // volumes?q=harry or anything to be added to be url

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())   // mandatory inorder to use rx java for call
                .build()
        }

    }
}