package com.geco.challangech5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geco.challangech5.databinding.ItemContentBinding
import com.geco.challangech5.model.Result

class HomeAdapter(private val onItemClick: OnClickListener) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){


    private val diffCallback = object: DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ):Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallback)


    fun submitData(value: List<Result>?) = differ.submitList(value)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemContentBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val data = differ.currentList[position]
        data.let{holder.bind(data)}
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemContentBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: Result){
            binding.apply{
//                Glide.with(this)
//                    .load(data.posterPath)
//                    .centerCrop()
//                    .into(singleItemMovieImage)
                singleItemMovieTitle.text = data.title
                singleItemMovieReleaseDate.text = data.releaseDate
                singleItemMovieOverview.text = data.overview
                root.setOnClickListener {
                    onItemClick.onClickItem(data)
                }
            }
        }
    }
    interface OnClickListener {
        fun onClickItem(data: Result)
    }


}
