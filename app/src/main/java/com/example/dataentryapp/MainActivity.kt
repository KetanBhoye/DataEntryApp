package com.example.dataentryapp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var contactsRV: RecyclerView
    private lateinit var FAB: FloatingActionButton
    private lateinit var EdtName: EditText
    private lateinit var EdtNum: EditText
    private lateinit var ImgProf: ImageView
    private lateinit var BtnChoose: Button
    private lateinit var BtnSave: Button
    private lateinit var contactAdp: contactAdapter
    private lateinit var dialog: Dialog
    val listOfContacts = mutableListOf<contactData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactsRV = findViewById(R.id.ContactListRV)
        contactsRV.layoutManager = LinearLayoutManager(this)

        contactAdp = contactAdapter(listOfContacts)
        contactsRV.adapter = contactAdp
        FAB = findViewById(R.id.fab)

        FAB.setOnClickListener{
            showDialog()
        }

    }


    private fun showDialog() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.crud_dialog)
        dialog.show()
          EdtName = dialog.findViewById(R.id.inp_name)
          EdtNum = dialog.findViewById(R.id.inp_mobNo)
          ImgProf = dialog.findViewById(R.id.inp_profileImg)
          BtnChoose = dialog.findViewById(R.id.btn_ChooseImg)
          BtnSave = dialog.findViewById(R.id.btn_save)


        BtnChoose.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent,101)
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101&&resultCode== RESULT_OK){
            ImgProf.visibility = View.VISIBLE
          ImgProf.setImageURI(data?.data)

            BtnSave.setOnClickListener {

                val inpName = EdtName.text.toString()
                val inpPhone = EdtNum.text.toString()
                val inpImg  = data?.data

                val contact = contactData(
                    img = inpImg,
                    name =  inpName,
                    mobNo = inpPhone,

                )

                listOfContacts.add(contact)

                contactAdp.notifyDataSetChanged()
                 dialog.dismiss()

            }
        }
    }

}