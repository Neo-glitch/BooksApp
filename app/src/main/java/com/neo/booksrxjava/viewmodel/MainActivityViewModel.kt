package com.neo.booksrxjava.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neo.booksrxjava.network.RetroInstance
import com.neo.booksrxjava.network.RetroService
import com.neo.booksrxjava.network.model.BooksListModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel: ViewModel() {
    private val TAG = "MainActivityViewModel"

    lateinit var booksList: MutableLiveData<BooksListModel>

    init {
        booksList = MutableLiveData()
    }

    fun getBooksObservable(): MutableLiveData<BooksListModel>{  // used to get the booksList LiveData
        return booksList
    }


    /*
    should have been done in repo. # bad practice
     */
    fun makeApiCall(query: String){
        val retrofitInstance =  RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        // since fun ret and observable, we can do normal rx java stuff
        retrofitInstance.getBooksList(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBooksObserverRx())
    }

    /*
    ret observer to observe observable returned in makeApiCall() above
     */
    private fun getBooksObserverRx(): Observer<BooksListModel>{
        return object : Observer<BooksListModel>{
            override fun onComplete() {
                // hide progress bar indicator
            }

            override fun onSubscribe(d: Disposable) {
                // start showing progress bar
            }

            override fun onNext(t: BooksListModel) {
                booksList.postValue(t)
            }

            override fun onError(e: Throwable) {
                booksList.postValue(null)
            }
        }
    }
}