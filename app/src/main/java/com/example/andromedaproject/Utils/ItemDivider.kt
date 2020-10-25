package com.example.andromedaproject.Utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

/*리사이클러뷰의 아이템들 간격을 조절해주는 녀석입니다!
ItemDivider() 여기 괄호 안에 Int형의 divHeight와 divWidth라는 변수들을 생성자로 넣어주었기 때문에
나중에 사용할 때  ItemDivider(6,8) 이런식으로 값을 넣어주어야합니다.
MainActivity로 가면 어떻게 사용하는지 또 설명해놓았는데 보면 또 이해될거예요!!*/

class ItemDivider(private val divHeight: Int, private val divWidth: Int): RecyclerView.ItemDecoration(){
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        // 밑의 4줄이 다 필요하지 않고 본인이 상,하,좌,우 설정하고 싶은것만 넣어주어도 됩니다.
        outRect.top = divHeight
        outRect.bottom = divHeight
        outRect.left = divWidth
        outRect.right = divWidth
    }
}