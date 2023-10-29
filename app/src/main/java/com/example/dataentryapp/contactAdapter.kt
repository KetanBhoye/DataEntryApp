package com.example.dataentryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class contactAdapter(
    private val listOfContacts: List<contactData>
): RecyclerView.Adapter<contactAdapter.contactViewHolder>() {

    class contactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val profileImg: ImageView = itemView.findViewById(R.id.profile_img)
        val contactName: TextView = itemView.findViewById(R.id.user_name)
        val contactNum: TextView = itemView.findViewById(R.id.mob_number)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        return contactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  listOfContacts.size
    }

    override fun onBindViewHolder(holder: contactViewHolder, position: Int) {
        val currentContact = listOfContacts[position]
        holder.contactName.text = currentContact.name
        holder.profileImg.setImageURI(currentContact.img)
        holder.contactNum.text = currentContact.mobNo

    }
}