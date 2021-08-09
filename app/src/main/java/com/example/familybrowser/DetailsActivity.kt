package com.example.familybrowser

import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.TextView
import com.example.familybrowser.models.TypeDetails
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val typeDetails = intent.getParcelableExtra<TypeDetails>("type") as TypeDetails

        val imageView2d = findViewById<ImageView>(R.id.image2d)
        val url = "https://s3.eu-central-1.amazonaws.com/revit.family.browser/Images/E/x480_2D/v2.0.0/${typeDetails.typeId}.png"
        Picasso.get().load(url).into(imageView2d)

        val imageView3d = findViewById<ImageView>(R.id.image3d)
        val url3d = "https://s3.eu-central-1.amazonaws.com/revit.family.browser/Images/E/x480_3D/v2.0.0/${typeDetails.typeId}.png"
        Picasso.get().load(url3d).into(imageView3d)

        val textTypeName = findViewById<TextView>(R.id.typeDetailsName)
        textTypeName.text = typeDetails.typeName

        val textFamilyName = findViewById<TextView>(R.id.typeDetailsFamily)
        textFamilyName.text = typeDetails.familyName

        val textDescription = findViewById<TextView>(R.id.typeDetailsDescription)
        textDescription.text = typeDetails.description

        configureTabLayout()
    }

    private fun configureTabLayout(){
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout) as TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Product"))
        tabLayout.addTab(tabLayout.newTab().setText("Size"))
        tabLayout.addTab(tabLayout.newTab().setText("IFC"))
        tabLayout.addTab(tabLayout.newTab().setText("Price"))
        tabLayout.addTab(tabLayout.newTab().setText("Spec"))

        val adapter = TabPagerAdapter(supportFragmentManager, tabLayout.tabCount)
        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = adapter

        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

        })
    }
}