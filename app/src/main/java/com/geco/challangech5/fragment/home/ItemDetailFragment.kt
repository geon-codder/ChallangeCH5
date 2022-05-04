package com.geco.challangech5.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.geco.challangech5.databinding.FragmentItemDetailBinding


class ItemDetailFragment : Fragment() {
    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentItemDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailItemTitle.text = requireArguments().getString("title")
        binding.detailItemReleaseDate.text = requireArguments().getString("release")
        binding.detailItemOverview.text = requireArguments().getString("overview")
        Glide.with(this)
            .load(IMAGE_BASE + requireArguments().getString("poster"))
            .into(binding.detailItemMovieImage)
    }

}