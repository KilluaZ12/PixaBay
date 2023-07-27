package com.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabay.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var adapter = ImageAdapter(arrayListOf())
    var page = 1
    var isAddToList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            searchBtn.setOnClickListener {
                isAddToList = false
                getImage()
            }
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(
                    recyclerView: RecyclerView,
                    newState: Int
                ) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        ++page
                        getImage()
                    }
                }
            })
        }
    }

    private fun ActivityMainBinding.getImage() {
        RetrofitService.api.getPictures(wordEt.text.toString(), page = page)
            .enqueue(object : retrofit2.Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    if (response.isSuccessful) {
                        recyclerView.adapter = adapter

                        if (!isAddToList) {
                            adapter.list
                        }
                        adapter.list.addAll(response.body()?.hits!!)

                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }
}