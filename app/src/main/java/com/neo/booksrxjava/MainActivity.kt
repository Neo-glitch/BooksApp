package com.neo.booksrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.neo.booksrxjava.adapter.BooksRvAdapter
import com.neo.booksrxjava.network.model.BooksListModel
import com.neo.booksrxjava.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    
    private val mViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }
    private lateinit var mAdapter: BooksRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRv()
        initSearchEt()
    }

    private fun initSearchEt() {
        inputBooksName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadApiData(s.toString())
            }
        })
    }

    private fun initRv() {
        booksListRv.layoutManager = LinearLayoutManager(this@MainActivity)
//        val decoration = DividerItemDecoration(applicationContext, VERTICAL)
//        addItemDecoration(decoration)

        mAdapter = BooksRvAdapter()
        booksListRv.adapter = mAdapter
    }

    private fun loadApiData(input: String) {
        mViewModel.getBooksObservable().observe(this, Observer<BooksListModel>{
            if(it != null){
                mAdapter.booksListData = it.items
                mAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in data retrieval", Toast.LENGTH_SHORT).show()
            }
        })
        mViewModel.makeApiCall(input)
    }

}