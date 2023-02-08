package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.GridLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.models.Question
import com.example.quizapp.models.Quiz
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: QuizAdapter
    lateinit var toggle:ActionBarDrawerToggle
    private var quizList= mutableListOf<Quiz>()
  lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firestore= FirebaseFirestore.getInstance()

        setUpViews()

    }


    private fun setUpViews() {
        setUpFirestore()
        setUpDrawerLayout()
        setUpRecylerView()
        setUpdatePicker()

    }

    private fun setUpdatePicker() {
        var btnDatePicker=findViewById<FloatingActionButton>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            val datePicker=MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"datepicker")
            datePicker.addOnPositiveButtonClickListener {


                val dateFormatter = SimpleDateFormat("dd-11-yyyy")
                val date = dateFormatter.format(Date(it))

                val intent:Intent= Intent(this,QuestionActivity::class.java)
                Log.d("DatePicker", date)
                intent.putExtra("DATE",date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DatePicker",datePicker.headerText)
            }
            datePicker.addOnCancelListener{
                Log.d("DatePicker","cancel ho gya")
            }

        }
    }

    private fun setUpFirestore() {
        Log.d("hello","pehla hum")
        val collectionReference=firestore.collection("quizzes")
        collectionReference.addSnapshotListener{
                value,error->
            if(value==null||error!=null){
                Toast.makeText(this,"no quiz is there wait for update",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
             //data is in json--> we have to convert to string
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))

            adapter.notifyDataSetChanged()
        }



    }

    private fun setUpRecylerView() {
         var recylerview:RecyclerView=findViewById(R.id.quizRecylerView)

        adapter= QuizAdapter(this,quizList)
        recylerview.layoutManager=GridLayoutManager(this,2)
        recylerview.adapter=adapter
    }

    private fun setUpDrawerLayout() {
//        dont forget to add dependency for material design and also change to this "Theme.MaterialComponents.Light.DarkActionBar.Bridge"
//             in style.xml and go to manifest and see what is written
        var appbar=findViewById<MaterialToolbar>(R.id.appBar)
        setSupportActionBar(appbar)
        var navDrawer=findViewById<DrawerLayout>(R.id.navDrawer)
        var navView=findViewById<NavigationView>(R.id.navView)
        //    lateinit var toggle:ActionBarDrawerToggle  make global
        toggle= ActionBarDrawerToggle(this,navDrawer,R.string.app_name,R.string.app_name)

        toggle.syncState()

        navView.setNavigationItemSelectedListener {
         val intent=Intent(this,ProfileActivity::class.java)
         startActivity(intent)
            navDrawer.closeDrawers()
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
