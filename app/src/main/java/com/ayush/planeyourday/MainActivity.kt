package com.ayush.planeyourday


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                    when (position) {
                        1 -> screenJumpToEventSet()
                        2 -> screenJumpToEventCheck()
                        else -> Toast.makeText(
                            this@MainActivity,
                            "Please select one of the two items",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


        }

    }
   private fun screenJumpToEventSet(){
        val intent = Intent(this ,WeatherInformation::class.java)
        startActivity(intent)
    }

    private fun screenJumpToEventCheck(){
        val intent = Intent(this,EventCheckActivity::class.java)
        startActivity(intent)
    }

}






