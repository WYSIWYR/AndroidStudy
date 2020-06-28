package com.example.project01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.contacts.view.*

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        val contact =  createContacts(20)
        createView(contact)
    }

    fun createContacts(listSize: Int = 10): Contacts {
        val contacts:Contacts = Contacts()

        for (i in 0 until listSize) {
            contacts.addPerson(
                Person(name = "친구$i",  number = "친구${i}의 전화 번호")
            )
        }

        return contacts
    }

    fun createView(contacts: Contacts) {
        val layoutInflater = LayoutInflater.from(this@ContactActivity)
        val container = contacts_container

        for (i in 0 until contacts.personList.size) {
            val itemView = layoutInflater.inflate(R.layout.contacts, null)
            val nameView = itemView.name
            nameView.text = contacts.personList.get(i).name
            addSetOnClickListener(contacts.personList.get(i), itemView)
            container.addView(itemView)
        }
    }

    fun addSetOnClickListener(person: Person, view: View) {
        view.setOnClickListener{
            val intent = Intent(this@ContactActivity, ContactDetail::class.java)
            intent.putExtra("name", person.name)
            intent.putExtra("number", person.number)
            startActivity(intent)
        }
    }
}

class Contacts() {
    val personList = ArrayList<Person>()

    fun addPerson(person: Person) {
        personList.add(person)
    }
}

class Person(val name: String, val number: String) {

}