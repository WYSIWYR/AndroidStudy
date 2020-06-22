package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_fragment.*

class Fragment : AppCompatActivity(), Fragment_1.OnDataPassListener {
    override fun onDataPass(data: String?) {
        Log.d("pass", "" + data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        Log.d("life_cycle", "onCreate")
        /*
        프래그먼트가 commit 될 때 데이터가 넘어가기 때문에 fragment_1에서 데이터를 사용할 경우
        activity_fragment에서 commit전에 fragment_1을 생성하면 앱이 nullPointerError를 발생시킨다.
         */
        val fragment_1: Fragment_1 = Fragment_1()
        val bundle: Bundle = Bundle()
        bundle.putString("안냥", "안녕")
        fragment_1.arguments = bundle

        /*
        프래그먼트 동적으로 사용하기
        AppCompatActivity에서 supportFragmentManager를 가져온다.
        이후 Transaction을 생성해야 한다.
        Transaction은 작업의 단위이다. 작업의 시작과 끝을 설정해주는 형식으로 프래그먼트를 사용한다.
        작업을 끝내는 방법으로는 대표적으로는 2가지가 있다.
        1. commit() -> 작업을 끝낼 시간이 있으면 실행한다.
        2. commitnow() -> 작업을 바로 끝낸다.
        프래그먼트 추가하는 방법
        1. add: 프래그먼트를 추가한다.
        2. replace: 존재하는 프래그먼트를 교체한다. remove를 호출해 프래그먼트를 제거하고 add를 호출해
        프래그먼트를 추가하는 것과 동일하다.
        -> 둘이 거의 동일하다.
         */
        call_fragment.setOnClickListener {
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment_1)
            fragmentTransaction.commit()
        }

        /*
        프래그먼트를 제거하는 방법
        1. detach: UI에서만 프래그먼트를 제거한다. onDestroyView()까지 실행
        2. remove: 생성한 프래그먼트를 완전히 제거한다. 컨테이너에 추가된 경우 뷰도 제거한다. onDetach()까지 실행
         */
        remove_fragment.setOnClickListener {
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(fragment_1)
            fragmentTransaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("life_cycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life_cycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life_cycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life_cycle", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("life_cycle", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life_cycle", "onDestroy")
    }
}