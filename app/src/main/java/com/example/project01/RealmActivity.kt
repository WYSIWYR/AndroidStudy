package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_realm.*

class RealmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)

        Realm.init(this@RealmActivity)
        /*
        Builder 메소드가 있는 경우 대부분 메서드 체인을 사용한다.
        아래의 config는 마이그레이션이 필요한 경우 데이터를 지워버리는 설정을 저장한다.
         */
        val config: RealmConfiguration =
            RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()

        Realm.setDefaultConfiguration(config)
        val realm = Realm.getDefaultInstance()

        saveButton.setOnClickListener {
            realm.executeTransaction {
                with(it.createObject(School::class.java)) {
                    this.name = "한양대"
                    this.location = "서울"
                }
            }
        }
        loadButton.setOnClickListener {
            realm.executeTransaction {
                val data = it.where(School::class.java).findFirst()
                Log.d("Realm", "Data: $data")
            }
        }
        deleteButton.setOnClickListener {
            realm.executeTransaction {
                it.where(School::class.java).findAll().deleteAllFromRealm()
//                it.where(School::class.java).findFirst().deleteFromRealm()
            }
        }
    }
}