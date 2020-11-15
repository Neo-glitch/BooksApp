package com.neo.booksrxjava.network

import com.neo.booksrxjava.network.model.BooksListModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("volumes")   //volumes?q=query
    fun getBooksList(@Query("q")query: String): Observable<BooksListModel>
        // fun makes a call to api with our query and returns an observable
}