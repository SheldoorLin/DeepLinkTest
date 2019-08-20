package com.sheldon.deeplinktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.sheldon.deeplinktest.databinding.ActivityMainBinding
import android.content.Intent
import android.net.Uri
import android.os.Build


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.button.setOnClickListener {
            val intent = Intent()
            intent.data = Uri.parse("app://open.my.app")
            val bundle = Bundle()
            bundle.putString("id", "201807201824")
            intent.putExtras(bundle)
            startActivity(intent)
        }


        binding.button2.setOnClickListener {
            val intent = Intent()
            intent.data = Uri.parse("app://open.my.app")
            val bundle = Bundle()
            bundle.putString("id", "201807202140")
            intent.putExtras(bundle)
            startActivity(intent)
        }

        binding.btnLoadData.setOnClickListener {
            val uri = Uri.parse("content://contact")
            val cursorReceiver = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                contentResolver.query(uri, null, null, null)
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            val TAG = "Sheldon"

            cursorReceiver?.moveToFirst()
            cursorReceiver?.getString(1)
            Log.d(TAG, "cursor_receiver_title_first ${cursorReceiver?.getString(2).toString()}")

            cursorReceiver?.move(1)
            cursorReceiver?.getString(1)
            Log.d(TAG, "cursor_receiver_title_second ${cursorReceiver?.getString(2).toString()}")

            cursorReceiver?.moveToLast()
            cursorReceiver?.getString(1)
            Log.d(TAG, "cursor_receiver_title_third ${cursorReceiver?.getString(2).toString()}")

        }
    }

}
