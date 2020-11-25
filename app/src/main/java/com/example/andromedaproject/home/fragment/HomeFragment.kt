package com.example.andromedaproject.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.andromedaproject.R
import com.example.andromedaproject.home.network.RequestWebSearch
import com.example.andromedaproject.home.adapter.WebSearchAdapter
import com.example.andromedaproject.home.model.WebSearchModel
import com.example.andromedaproject.utils.ItemHorizontalDivider
import com.example.andromedaproject.utils.ItemVerticalDivider
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {
    lateinit var searchResultAdapter: WebSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResultAdapter = WebSearchAdapter(view.context)
        recyclerview_docs_list.adapter = searchResultAdapter
        recyclerview_docs_list.addItemDecoration(ItemVerticalDivider(30))
        recyclerview_docs_list.addItemDecoration(ItemHorizontalDivider(30))

        searchDocs(view)
    }

    fun searchDocs(view:View){
        button_search.setOnClickListener {
            Log.d("버튼눌림", "버튼눌림")
            loadSearchResult(view)
        }
    }

    fun loadSearchResult(view: View){
        val token = "KakaoAK bccddec2477515123ee06ae249c39f95"

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitWebSearch = retrofit.create(RequestWebSearch::class.java)

        retrofitWebSearch.requestWebSearch(
            token, edittext_search_web.text.toString()
        ).enqueue(
            object: Callback<WebSearchModel>{
                override fun onResponse(
                    call: Call<WebSearchModel>,
                    response: Response<WebSearchModel>
                ) {
                    if(response.isSuccessful){
                        searchResultAdapter.datas = response.body()!!.documents
                        searchResultAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<WebSearchModel>, t: Throwable) {
                    Log.d("error", "$t")
                }
            }
        )
    }
}