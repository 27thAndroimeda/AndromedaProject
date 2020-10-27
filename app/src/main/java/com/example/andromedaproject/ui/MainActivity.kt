package com.example.andromedaproject.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andromedaproject.R
import com.example.andromedaproject.Utils.ItemDivider
import com.example.andromedaproject.adapter.DragManageAdapter
import com.example.andromedaproject.adapter.ProfileAdapter
import com.example.andromedaproject.adapter.SwipeToDeleteAdapter
import com.example.andromedaproject.model.UserInformationModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var datas = mutableListOf<UserInformationModel>()
    lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setResult(Activity.RESULT_OK, intent)
        init()
    }

    fun init() {
        profileAdapter = ProfileAdapter(this)
        recyclerview_profile.adapter = profileAdapter
        recyclerview_profile.layoutManager = LinearLayoutManager(this)

        //리사이클러뷰의 아이템들을 높이 5dp, 좌우로 5dp를 띄우겠다는 뜻입니다.
        //Utils 파일에 ItemDivider를 만들어 놓았으니 참고해주세요!!
        //리사이클러뷰 이름에 addItemDecoration이라는 함수를 통해서 설정해줍니다.
        //addItemDecoration을 ctrl클릭해보면 생성자로 ItemDecoration을 넣어주여야하는데
        //우리는 아까 ItemDivider를 만들 때 ItemDecoration을 상속시켰으니
        //addItemDecoration() 여기 괄호안에 ItemDivider를 넣어주면 됩니다.
        recyclerview_profile.addItemDecoration(ItemDivider(5, 5))
        moveItemHelper()
        deleteItemHelper()
        loadDatas()
    }

    fun moveItemHelper() {
        val callback = DragManageAdapter(
            profileAdapter, this,
            ItemTouchHelper.UP
                .or(
                    ItemTouchHelper
                        .DOWN
                ),
            ItemTouchHelper.LEFT
                .or(
                    ItemTouchHelper
                        .RIGHT
                )
        )
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(recyclerview_profile)
    }

    fun deleteItemHelper() {
        var itemTouchHelper = ItemTouchHelper(SwipeToDeleteAdapter(profileAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerview_profile)
    }

    fun loadDatas() {
        datas.apply {
            add(
                UserInformationModel(
                    userProfileImage = R.drawable.sopt_logo,
                    userName = "Hand Peace",
                    userDescription = "27기 ON SOPT 부회장"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = R.drawable.sopt_logo,
                    userName = "손평화",
                    userDescription = "안드로이드 파트"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = R.drawable.sopt_logo,
                    userName = "sson_peace7",
                    userDescription = "평화 인스타 아이디!!!"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = R.drawable.sopt_logo,
                    userName = "Hand Peace",
                    userDescription = "27기 ON SOPT 부회장"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = R.drawable.sopt_logo,
                    userName = "Hand Peace",
                    userDescription = "27기 ON SOPT 부회장"
                )
            )
            profileAdapter.datas = datas
            profileAdapter.notifyDataSetChanged()
        }
    }

}