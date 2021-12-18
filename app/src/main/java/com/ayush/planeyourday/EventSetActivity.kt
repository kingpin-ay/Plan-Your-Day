package com.ayush.planeyourday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayush.planeyourday.databinding.ActivityEventSetBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EventSetActivity : AppCompatActivity() {
    private lateinit var dataBase : DatabaseReference

    private lateinit var binding : ActivityEventSetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventSetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val destinationPlace = binding.userAnswerDesPlace.text.toString()
        val currentPlace = binding.userAnswerCurPlace.text.toString()
        val date = binding.userAnswerDate.text.toString()
        val time = binding.userAnswerTime.text.toString()
        val description = binding.userAnswer.text.toString()

        binding.make.setOnClickListener {
            dataBase = FirebaseDatabase.getInstance(
                "https://plane-your-day-789a8-default-rtdb.asia-southeast1.firebasedatabase.app").getReference(
                "ToDo")

            val data = FireBaseDataSet(currentPlace,destinationPlace,date,time,description)
            dataBase.child(date).setValue(data).addOnSuccessListener {
                binding.userAnswer.text.clear()
                binding.userAnswerTime.text.clear()
                binding.userAnswerDate.text.clear()
                binding.userAnswerCurPlace.text.clear()
                binding.userAnswerDesPlace.text.clear()
            }
            val intent = Intent(this@EventSetActivity,WeatherInformation::class.java)
            intent.putExtra(WeatherInformation.PRESENT_PLACE,currentPlace)
            intent.putExtra(WeatherInformation.DESTINATION_PLACE,destinationPlace)
            startActivity(intent)
        }
    }
}