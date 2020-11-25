package com.example.andromedaproject.mypage.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.andromedaproject.R
import com.example.andromedaproject.mypage.adapter.GitHubUserAdapter
import com.example.andromedaproject.mypage.model.Repository
import com.example.andromedaproject.mypage.network.RequestMypage
import com.example.andromedaproject.utils.ItemVerticalDivider
import kotlinx.android.synthetic.main.fragment_friends_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FriendsListFragment : Fragment() {
    lateinit var friendsListAdapter: GitHubUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFriendsListAdapter(view)
        getFriendsData()
    }

    private fun setFriendsListAdapter(view: View) {
        friendsListAdapter = GitHubUserAdapter(view.context)
        recyclerview_friends_list.adapter = friendsListAdapter
        recyclerview_friends_list.addItemDecoration(ItemVerticalDivider(15))
    }

    //Retrofit으로 서버통신 구현
    private fun getFriendsData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitFriendsList = retrofit.create(RequestMypage::class.java)
        retrofitFriendsList.getFriendsList()
            .enqueue(object : Callback<List<Repository>> {
                override fun onResponse(
                    call: Call<List<Repository>>,
                    response: Response<List<Repository>>
                ) {
                    if (response.isSuccessful) {
                        friendsListAdapter.datas.addAll(response.body() as List<Repository>)
                        friendsListAdapter.notifyDataSetChanged()
                    } else {
                        Log.d("ERROR", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    Log.e("ERROR", "error message: $t")

                }
            })
    }
}