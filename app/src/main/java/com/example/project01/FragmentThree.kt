package com.example.project01

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentThree : Fragment() {
    /*
    fragment가 UI를 처음 그릴 때 호출된다.
    inflater는 그려줄 뷰를 의미한다.
    container는 뷰가 그려질 부모를 의미한다
    */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("life_cycle", "onCreateView_Fragment")

        return inflater.inflate(R.layout.fragment_1, container, false)
    }
}