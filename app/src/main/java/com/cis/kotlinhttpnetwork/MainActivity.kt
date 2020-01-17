package com.cis.kotlinhttpnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            val thread = NetworkThread()
            thread.start()
        }
    }

    inner class NetworkThread: Thread() {
        override fun run() {
//            val site = "http://www.google.com"
            val site = "http://10.211.55.4:8080/HttpNetwork/string.jsp"
            val url = URL(site)
            val conn = url.openConnection()

            val input = conn.getInputStream()
            val isr = InputStreamReader(input, "UTF-8")
            val br = BufferedReader(isr)

            var str: String? = null
            val buf = StringBuffer()

            do{
                str = br.readLine()
                if (str != null){
                    buf.append(str)
                }
            } while (str != null)

            val data = buf.toString()

            runOnUiThread{
                tv.text = data
            }
        }
    }
}
