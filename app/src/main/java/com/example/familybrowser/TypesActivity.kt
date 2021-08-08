package com.example.familybrowser

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.familybrowser.models.TypeDetails

class TypesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_types)
        val queue = Volley.newRequestQueue(this)

        val systemName = intent.getStringExtra("systemName")
        val categoryName = intent.getStringExtra("categoryName")

        val scrollView = ScrollView(this)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL

        val textView = findViewById<TextView>(R.id.textView3)
        textView.text = "${systemName} ${categoryName}"

        val url = " https://api.webcatalog.building360.ch/api/FamilyTypeData/system/${systemName}/version/2.0.0/category/${categoryName}"

        val jsonArray = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                textView.text = response.toString()
                for (type in 0 until response.length()) {
                    val jsonObject = response.getJSONObject(type)
                    val row = LinearLayout(this)
                    row.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    val typeName = jsonObject.getString("TypeName")
                    val button = Button(this)
                    button.text = typeName
                    button.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    button.setOnClickListener(View.OnClickListener {
                        val intent = Intent (this, DetailsActivity::class.java)
                        val typeName = jsonObject.getString("TypeName")
                        val familyName = jsonObject.getString("FamilyName")
                        val description = jsonObject.getString("Description")
                        val typeId = jsonObject.getString("TypeId")
                        val typeDetails = TypeDetails(typeName, familyName, description, typeId)
                        intent.putExtra("type", typeDetails)
                        startActivity(intent)
                    })

                    row.addView(button)
                    linearLayout.addView(row);
                }
            },
            {error ->textView.text  = error.message})

        scrollView.addView(linearLayout)
        setContentView(scrollView)
        queue.add(jsonArray)
    }
}