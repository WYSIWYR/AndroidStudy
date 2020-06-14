package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calculator.*

class Calculator : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        var newSum: String = ""
        var oldSum: String = "0"

        one.setOnClickListener {
            newSum += "1"
            sum.text = newSum
        }

        two.setOnClickListener {
            newSum += "2"
            sum.text = newSum
        }

        three.setOnClickListener {
            newSum += "3"
            sum.text = newSum
        }

        four.setOnClickListener {
            newSum += "4"
            sum.text = newSum
        }


        five.setOnClickListener {
            newSum += "5"
            sum.text = newSum
        }

        six.setOnClickListener {
            newSum += "6"
            sum.text = newSum
        }

        seven.setOnClickListener {
            newSum += "7"
            sum.text = newSum
        }

        eight.setOnClickListener {
            newSum += "8"
            sum.text = newSum
        }

        nine.setOnClickListener {
            newSum += "9"
            sum.text = newSum
        }

        zero.setOnClickListener {
            newSum += "0"
            sum.text = newSum
        }

        ca.setOnClickListener {
            sum.text = "0"
            newSum = "0"
            oldSum = "0"
        }

        plus.setOnClickListener {
            oldSum = (oldSum.toInt() + newSum.toInt()).toString()
            sum.text = oldSum
            newSum = "0"
        }
    }

}
