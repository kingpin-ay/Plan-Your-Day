package com.ayush.planeyourday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayush.planeyourday.databinding.ActivityEventCheckBinding
import com.google.firebase.database.*


class EventCheckActivity : AppCompatActivity() {

    private lateinit var dRef : DatabaseReference
    private lateinit var binding: ActivityEventCheckBinding
//    private lateinit var todoList : ArrayList<Todo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val todoList = mutableListOf(
            Todo("chakdaha","krishnanagar","12/7/2002"),
            Todo("chakdaha","krishnanagar","12/7/2002"),
            Todo("chakdaha","krishnanagar","12/7/2002"),
            Todo("chakdaha","krishnanagar","12/7/2002"),
            Todo("chakdaha","krishnanagar","12/7/2002"),
            Todo("chakdaha","krishnanagar","12/7/2002")
        )
        binding.todoList.adapter = TodoAdapter(todoList)
        binding.todoList.layoutManager = LinearLayoutManager(this)
//        getData()
    }


//    private fun getData(){
//        binding = ActivityEventCheckBinding.inflate(layoutInflater)
//        dRef = FirebaseDatabase.getInstance().getReference("location")
//        dRef.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(p0: DataSnapshot) {
//                if(p0.exists()){
//                    for(locationP0 in p0.children){
//                        val location =   locationP0.getValue(Todo::class.java)
//                        if (location != null) {
//                            todoList.add(location)
//                        }
//                    }
//                    binding.todoList.adapter = TodoAdapter(todoList)
//                }
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
//
//        })
//
//    }
}