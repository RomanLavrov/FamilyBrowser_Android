package com.example.familybrowser

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class TypesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_types)
        val queue = Volley.newRequestQueue(this)

        val systemName = intent.getStringExtra("systemName")
        val categoryName = intent.getStringExtra("categoryName")

        val textView = findViewById<TextView>(R.id.textView3)
        textView.text = "${systemName} ${categoryName}"

        val url = " https://api.webcatalog.building360.ch/api/FamilyTypeData/system/${systemName}/version/2.0.0/category/${categoryName}"

        val jsonArray = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                textView.text = response.toString()

            },
            {error ->textView.text  = error.message})

        queue.add(jsonArray)
    }
}