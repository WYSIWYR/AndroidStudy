package com.example.project01

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_permission.*

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        /*
        PERMISSION_GRANTED와 ContextCompat.checkSelfPermission으로 가져온 값이 일치하면 이미 권한을
        가지고 있다.
         */
        askPermission.setOnClickListener {
            val hasCameraPermission = ContextCompat.checkSelfPermission(
                this@PermissionActivity,
                android.Manifest.permission.CAMERA
            )

            if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this@PermissionActivity,
                    arrayOf(android.Manifest.permission.CAMERA),
                    1000
                )
            } else {
                Log.d("Permission", "Camera 권한이 있습니다!")
            }
        }
    }

    /*
    onRequestPermissionResult를 통해 권한 요청에 대한 결과를 처리할 수 있다.
    permission를 통해 권한이 있는 것을 배열로 반환해 알려준다.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Permission", "Camera 권한 허가")
            } else {
                Log.d("Permission", "Camera 권한 거부")
            }
        }
    }
}