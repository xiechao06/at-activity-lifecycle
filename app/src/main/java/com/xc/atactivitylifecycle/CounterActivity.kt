package com.xc.atactivitylifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
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
            try {
                while (!Thread.currentThread().isInterrupted) {
                    val counter = (textViewCounter.text.toString() as String).toLong() + 1
                    handler.post {
                        textViewCounter.text = counter.toString()
                    }
                    Thread.sleep(1000)
                }

            } catch (e: InterruptedException) {
                Log.v("CounterActivity", "thread stopped")
            }



        })
        thread.start()
    }

    override fun onPause() {
        Log.v("CounterActivity", "on pause")
        super.onPause()
        thread.interrupt()
    }

    override fun onStop() {
        Log.v("CounterActivity", "on stop")
        super.onStop()
    }

    // it will only called when system destroys the activity due to system constraints, since
    // it would be polite if the activity will be recreated if user navigate back to it
    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        Log.v("CounterActivity", "on save instance state")
        super.onSaveInstanceState(outState, outPersistentState)
    }
}
