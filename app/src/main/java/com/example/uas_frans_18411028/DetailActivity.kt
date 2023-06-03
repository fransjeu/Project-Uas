package com.example.uas_frans_18411028

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uas_frans_18411028.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intss = intent
        val imageTitle = intss.getStringExtra("IMAGETITLE")
        val imageSrc = intss.getStringExtra("IMAGESRC")
        val imageDesc = intss.getStringExtra("IMAGEDESC")

        binding.ImageTitle.text = imageTitle
        binding.ImageDetail.loadImage(imageSrc)
        binding.ImageDesc.text = imageDesc

    }
}