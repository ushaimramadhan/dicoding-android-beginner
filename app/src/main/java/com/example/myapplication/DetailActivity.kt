package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LANGUAGE = "extra_language"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgPhoto: ImageView = findViewById(R.id.img_detail_photo)
        val tvName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetail: TextView = findViewById(R.id.tv_detail_description)

        val language = if(Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_LANGUAGE, Language::class.java)
        } else {
            @Suppress("DECRECATION")
            intent.getParcelableExtra(EXTRA_LANGUAGE)
        }

        if (language != null) {
            tvName.text = language.name
            tvDetail.text = language.detail
            imgPhoto.setImageResource(language.photo)
        }
    }
}