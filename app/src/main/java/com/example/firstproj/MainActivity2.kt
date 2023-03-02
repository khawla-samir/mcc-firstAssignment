package com.example.firstproj

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class MainActivity2 : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var desc:EditText
    lateinit var num:EditText
    lateinit var add:Button
    lateinit var show:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val db = Firebase.firestore
        add = findViewById(R.id.add)
        show = findViewById(R.id.show)
        name=findViewById(R.id.nameTF)
        desc=findViewById(R.id.descTF)
        num=findViewById(R.id.numTF)

        add.setOnClickListener {
            if (name.text.isEmpty() || desc.text.isEmpty() || num.text.isEmpty()){
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_LONG).show()
            }
            else {
                val note = hashMapOf(
                    "name" to name.text.toString(),
                    "desc" to desc.text.toString(),
                    "num" to num.text.toString()
                )

                // Add a new document with a generated ID
                db.collection("notes")
                    .add(note)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                        Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show()
                        name.hint=" "
                        desc.hint=" "
                        num.hint=" "
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            }
        }
        show.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    }
}