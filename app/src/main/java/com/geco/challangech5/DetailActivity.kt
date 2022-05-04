package com.geco.challangech5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.geco.challangech5.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val bundle: Bundle = getArguments()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
//        val bundle = arguments
//        binding.detailItemTitle.text = bundle?.getString("title")
//        binding.detailItemReleaseDate.text = bundle?.getString("release")
//        binding.detailItemOverview.text = bundle?.getString("overview")
//        Glide.with(this)
//            .load(IMAGE_BASE + bundle?.getString("poster"))
//            .into(binding.detailItemMovieImage)



    }
}