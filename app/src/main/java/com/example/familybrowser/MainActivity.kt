package com.example.familybrowser

import android.app.AlertDialog
import android.app.DownloadManager
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
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView2)
        val layout = findViewById<ConstraintLayout>(R.id.layoutSystem)


// Instantiate the RequestQueue.

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.webcatalog.building360.ch/api/FamilySystem"

// Request a string response from the provided URL.
        val linearlayout = findViewById<ConstraintLayout>(R.id.layoutButtons) as LinearLayout
        linearlayout.orientation = LinearLayout.VERTICAL

        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                for ( system in 0 until response.length()  ){
                    val jsonObject = response.getJSONObject(system)
                    val data=jsonObject.getString("SystemName");

                    val row = LinearLayout(this)
                    row.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )


                    val button = Button (this)
                    button.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.MATCH_PARENT                       )

                    button.setOnClickListener(View.OnClickListener { val alertDialogBuilder = AlertDialog.Builder(this)
                    alertDialogBuilder.setMessage(data)})
                    button.text = data
                    row.addView(button)
                    linearlayout.addView(row);
                }
                //setContentView(linearlayout)
                textView.text = "Response: %s".format(response.toString())
            },
            { error ->
                textView.text = "Error" + error.message
            }


        )

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }


}