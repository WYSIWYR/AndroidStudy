package com.example.project01

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_1.*

class Fragment_1: Fragment() {
    interface OnDataPassListener {
        fun onDataPass  (data: String?)
    }

    //지연 초기화 lateinit
    lateinit var dataPassListener: OnDataPassListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("life_cycle", "onAttach_Fragment")
        dataPassListener = context as OnDataPassListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("life_cycle", "onCreate_Fragment")
        val data = arguments?.getString("안냥")
        Log.d("data", data)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("life_cycle", "onViewCreated_Fragment")

        /*
        프래그먼트는 액티비티의 onCreate에서 했던 작업을 여기에서 한다.
         */
        passer.setOnClickListener {
            dataPassListener.onDataPass("Hellow")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("life_cycle", "onActivityCreated_Fragment")
    }

    override fun onStart() {
        super.onStart()
        Log.d("life_cycle", "onStart_Fragment")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life_cycle", "onResume_Fragment")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life_cycle", "onPause_Fragment")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life_cycle", "onStop_Fragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("life_cycle", "onDestroyView_Fragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life_cycle", "onDestroy_Fragment")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("life_cycle", "onDetach_Fragment")
    }
}