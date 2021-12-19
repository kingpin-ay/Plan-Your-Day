package com.ayush.planeyourday

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayush.planeyourday.databinding.ActivityEventCheckBinding
import com.google.firebase.database.*


class EventCheckActivity : AppCompatActivity() {

    private lateinit var dRef : DatabaseReference
    private lateinit var binding: ActivityEventCheckBinding
    private lateinit var todoList : ArrayList<Todo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val todoList = mutableListOf(
//            Todo("chakdaha","krishnanagar","12/7/2002","2.30 PM",
//                " It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"),
//            Todo("chakdaha","krishnanagar","12/7/2002","2.30 PM",
//                " It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"),
//            Todo("chakdaha","krishnanagar","12/7/2002","2.30 PM",
//                " It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"),
//            Todo("chakdaha","krishnanagar","12/7/2002","2.30 PM",
//                " It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"),
//            Todo("chakdaha","krishnanagar","12/7/2002","2.30 PM",
//                " It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"),
//            Todo("chakdaha","krishnanagar","12/7/2002","2.30 PM",
//                " It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum")
//        )


//        binding.todoList.adapter = TodoAdapter(todoList)
        binding.todoList.layoutManager = LinearLayoutManager(this)
        todoList = arrayListOf<Todo>()
        getData()
    }


    private fun getData(){

        binding = ActivityEventCheckBinding.inflate(layoutInflater)
        dRef = FirebaseDatabase.getInstance(
            "https://plane-your-day-789a8-default-rtdb.asia-southeast1.firebasedatabase.app").getReference(
            "ToDo")
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {

                if(p0.exists()){
                    for(p0Snapshot in p0.children){
                        val user = p0Snapshot.getValue(Todo::class.java)
                        todoList.add(user!!)
                        Toast.makeText(this@EventCheckActivity, "$todoList", Toast.LENGTH_SHORT).show()
                        binding.todoList.adapter  = TodoAdapter(todoList)
                    }
//                    val firstLocation = p0.child("presentLocation").value as String
//                    val secondLocation = p0.child("destinationLocation").value as String
//                    val  date = p0.child("date").value as String
//                    val time  = p0.child("time").value as String
//                    val description = p0.child("description").value as String
//                    val data = Todo(firstLocation,secondLocation,date,time,description)
//
//                    Toast.makeText(this@EventCheckActivity, "$data", Toast.LENGTH_SHORT).show()
////                    val location =   locationP0.getValue(Todo::class.java)
////                    todoList.add(data)
////                    binding.todoList.adapter = TodoAdapter(todoList)

                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })

    }
}

