package com.example.firstproj

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproj.Adabter.NoteRV
import com.example.firstproj.Model.Note
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.EventListener

class MainActivity : AppCompatActivity() {

   lateinit var rv:RecyclerView
    lateinit var add :ImageButton
    lateinit var notes : ArrayList<Note>
    private var db = Firebase.firestore

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv=findViewById(R.id.recyclerView)
        rv.layoutManager=LinearLayoutManager(this)
        add = findViewById(R.id.btnAdd)
        notes = ArrayList()

        var noteAdabter=NoteRV(this,notes)


        add.setOnClickListener {
            val i = Intent(this, MainActivity2::class.java)
            startActivity(i)
        }

      //  db=FirebaseFirestore.getInstance()

        db.collection("notes")
            .get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for (data in it.documents){

                        val note: Note?= data.toObject(Note::class.java)
                            notes.add(note!!)


                    }
                    rv.adapter=noteAdabter
                }
               /* result ->
                for (document in result) {
                    Log.d("read", "${document.id} => ${document.data}")
                }*/

            }
            .addOnFailureListener { exception ->
                Log.w("not read", "Error getting documents.", exception)
            }





    /*
       val db = Firebase.firestore

       val subject = hashMapOf(
           "name" to "MCC",
           "num" to "MOPC 3312",
           "hours" to 3
       )

       // Add a new document with a generated ID
       db.collection("subjects")
           .add(subject)
           .addOnSuccessListener { documentReference ->
               Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
           }
           .addOnFailureListener { e ->
               Log.w(TAG, "Error adding document", e)
           }
       db.collection("subjects")
           .get()
           .addOnSuccessListener { result ->
               for (document in result) {
                   Log.d("read", "${document.id} => ${document.data}")
               }
           }
           .addOnFailureListener { exception ->
               Log.w("not read", "Error getting documents.", exception)
           }
*/
    }
}