package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        supportActionBar?.title = "Top 10 Language"

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

        listLanguageAdapter.setOnItemClickCallback(object : ListLanguageAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Language) {
                showSelectedLanguage(data)
            }
        })
    }

    private fun showSelectedLanguage(language: Language) {
        val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithObjectIntent.putExtra(DetailActivity.EXTRA_LANGUAGE, language)
        startActivity(moveWithObjectIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}