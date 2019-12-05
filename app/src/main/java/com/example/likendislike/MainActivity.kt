package com.example.likendislike

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //module level variables
    //var countLike:Int = 0
    lateinit var counterViewModel: CounterViewModel
    lateinit var sharedPreference:SharedPreferences
    //var countDislike:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate")
        //Initialise the Counter ViewModel
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //Initialise the shared preferences
        //sharedPreference = getSharedPreferences("natokong", Context.MODE_PRIVATE)
        sharedPreference = getPreferences(Context.MODE_PRIVATE)

        imageViewLike.setOnClickListener{
            counterViewModel.countLike++
            textViewLikes.text = counterViewModel.countLike.toString()
        }
        imageViewDislike.setOnClickListener{
            counterViewModel.countDislike++
            textViewDislike.text = counterViewModel.countDislike.toString()
        }
    }


    override fun onStart() {
        Log.d("MainActivity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity", "onResume")
        //Retreive Shared Preference value(s)
        counterViewModel.countLike= sharedPreference.getInt(getString(R.string.like), 0)
        counterViewModel.countDislike= sharedPreference.getInt(getString(R.string.dislike), 0)
        textViewLikes.text = counterViewModel.countLike.toString()
        textViewDislike.text = counterViewModel.countDislike.toString()
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity", "onPause")
        with(sharedPreference.edit()){
            putInt(getString(R.string.like), counterViewModel.countLike)
            putInt(getString(R.string.dislike), counterViewModel.countDislike)
            commit()
        }
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity", "onStop")


        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity", "onDestroy")
        super.onDestroy()
    }
}
