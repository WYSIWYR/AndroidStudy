package com.example.project01

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_async.*
import java.lang.Exception

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        var task: TestTask? = null
        startButton.setOnClickListener {
            task = TestTask(progressBar, comment)
            task?.execute()
        }

        cancelButton.setOnClickListener {
            task?.cancel(true)
        }
    }
}

/*
AsyncTask는 상속받을 때 params, progress, result의 타입을 설정해줘야한다.
params는 doInBackground에서 사용한다.
progress는 onProgressUpdate에서 사용한다.
result는 onPostExecute에서 사용한다.
 */
class TestTask(
    val progressBar: ProgressBar,
    val progressText: TextView
): AsyncTask<Int, Int, Int>() {
    var percent: Int = 0

    override fun onPreExecute() {
        super.onPreExecute()
        percent = 0
        progressBar.setProgress(percent)
    }

    override fun doInBackground(vararg params: Int?): Int {
        while (!isCancelled) {
            percent++
            if (percent > 100) {
                break
            } else {
                publishProgress(percent)
            }
            try {
                Thread.sleep(100)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return percent
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        progressBar.setProgress(values[0] ?: 0)
        val text = "${values[0]}% 완료"
        progressText.text = text
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        progressText.text = "작업이 완료됐습니다."
    }

    override fun onCancelled() {
        super.onCancelled()
        progressBar.setProgress(0)
        progressText.text = "작업이 취소됐습니다."
    }
}