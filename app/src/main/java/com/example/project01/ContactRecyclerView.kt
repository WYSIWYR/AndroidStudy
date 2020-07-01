package com.example.project01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_contact_recycler_view.*
import kotlinx.android.synthetic.main.contacts.view.*

class ContactRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_recycler_view)

        /*
        파라미터가 많아지면 파라미터의 이름을 적어주는 것도 좋다(개인취향)
        변수를 적절히 줄여서 사용하는 것이 좋다. 반복되는 부분은 with를 통해 줄이는 것이 좋다.
         */
        with(recycler_contact) {
            this.adapter = ContactRecyclerAdapter(
                contact = createContacts(30),
                inflater = LayoutInflater.from(this@ContactRecyclerView),
                activity = this@ContactRecyclerView
            )
            this.layoutManager = LinearLayoutManager(this@ContactRecyclerView)
        }
    }

    fun createContacts(listSize: Int = 10): Contacts {
        val contacts: Contacts = Contacts()

        for (i in 0 until listSize) {
            contacts.addPerson(
                Person(name = "친구$i", number = "친구${i}의 전화 번호")
            )
        }

        return contacts
    }

}

/*
AppCompatActivity를 상속받지 않았기 때문에 startActivity를 사용할 수 없다. 그렇기 때문에 activity를
매개변수로 가져와서 startActivity를 호출한다.
 */
class ContactRecyclerAdapter(
    val contact: Contacts,
    val inflater: LayoutInflater,
    val activity: Activity
) : RecyclerView.Adapter<ContactRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(contactView: View) : RecyclerView.ViewHolder(contactView) {
        val contactName: TextView

        init {
            contactName = contactView.name
            contactView.setOnClickListener {
                val intent = Intent(activity, ContactDetail::class.java)
                intent.putExtra("name", contact.personList.get(adapterPosition).name)
                intent.putExtra("number", contact.personList.get(adapterPosition).number)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.contacts, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contact.personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contactName.text = contact.personList.get(position).name
    }
}