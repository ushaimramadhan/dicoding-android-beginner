package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvLanguages: RecyclerView
    private val list = ArrayList<Language>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvLanguages = findViewById(R.id.rv_languages)
        rvLanguages.setHasFixedSize(true)

        list.addAll(getListLanguages())
        showRecyclerList()
    }

    private fun getListLanguages(): ArrayList<Language> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.name_description)
        val dataDetail = resources.getStringArray(R.array.name_detail)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listLanguage = ArrayList<Language>()

        for (i in dataName.indices) {
            val language = Language(
                dataName[i],
                dataDescription[i],
                dataDetail[i],
                dataPhoto.getResourceId(i, -1)
            )
            listLanguage.add(language)
        }
        dataPhoto.recycle()
        return listLanguage
    }

    private fun showRecyclerList() {
        rvLanguages.layoutManager = LinearLayoutManager(this)

        val listLanguageAdapter = ListLanguageAdapter(list)

        rvLanguages.adapter = listLanguageAdapter
    }
}