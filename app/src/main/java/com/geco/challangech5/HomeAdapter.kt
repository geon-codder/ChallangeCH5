package com.geco.challangech5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geco.challangech5.databinding.ItemContentBinding
import com.geco.challangech5.model.Movie
import com.geco.challangech5.model.MovieResponse

class HomeAdapter(private val movies: List<Movie>,private val onItemClick: OnClickListener
) : RecyclerView.Adapter<HomeAdapter.MovieViewHolder>(){

    inner class MovieViewHolder(private val binding: ItemContentBinding):RecyclerView.ViewHolder(binding.root){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie : Movie){
            binding.singleItemMovieTitle.text = movie.title
            binding.singleItemMovieReleaseDate.text = movie.release
            binding.singleItemMovieOverview.text = movie.overview
            Glide.with(itemView)
                .load(IMAGE_BASE + movie.poster)
                .into(binding.singleItemMovieImage)
            binding.root.setOnClickListener {
                onItemClick.onClickItem(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return MovieViewHolder(ItemContentBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies[position])
    }

    interface OnClickListener {
        fun onClickItem(data: Movie)
    }

}
