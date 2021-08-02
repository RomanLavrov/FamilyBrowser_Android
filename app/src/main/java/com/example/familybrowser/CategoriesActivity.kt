package com.example.familybrowser

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.motion.Debug
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class CategoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val queue = Volley.newRequestQueue(this)
        val textView = findViewById<TextView>(R.id.textView)
        val systemId = intent.getStringExtra("id")
        val systemName = intent.getStringExtra("systemName")
        textView.text = systemName + systemId

        val scrollView = ScrollView(this)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL

        val url = "https://api.webcatalog.building360.ch/api/Category"
        val jsonArray = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->

                textView.text = response.toString()
                for (category in 0 until response.length()) {
                    val jsonObject = response.getJSONObject(category)
                    if (jsonObject.getString("FamilySystem_ID") == systemId) {
                        val row = LinearLayout(this)
                        row.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )

                        val categoryName = jsonObject.getString("CategoryName")
                        val button = Button(this)
                        button.text = categoryName
                        button.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        button.setOnClickListener(View.OnClickListener {
                            val intent = Intent(this, TypesActivity::class.java)
                            intent.putExtra("systemName", systemName)
                            intent.putExtra("categoryName", categoryName)
                            startActivity(intent)
                        })

                        row.addView(button)
                        linearLayout.addView(row);
                    }
                }
            },
            { error -> textView.text = error.message })

        scrollView.addView(linearLayout)
        setContentView(scrollView)
        queue.add(jsonArray)
    }
}

