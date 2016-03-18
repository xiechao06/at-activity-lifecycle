package com.xc.atactivitylifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    lateinit private var thread: Thread

    override fun onResume() {
        super.onResume()
        val handler = Handler();
        thread = Thread(Runnable {
            while (true) {
                val counter = (textViewCounter.text.toString() as String).toLong() + 1
                handler.post {
                    textViewCounter.text = counter.toString()
                }
                Thread.sleep(1000)
            }
        })
        thread.start()
    }
}
