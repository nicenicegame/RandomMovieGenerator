package com.rmg.randommoviegenerator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rmg.randommoviegenerator.data.models.DatabaseMovie
import com.rmg.randommoviegenerator.databinding.ListItemMovieBinding
import com.rmg.randommoviegenerator.presentation.ui.FavouriteFragmentDirections

class FavouriteMovieAdapter :
    ListAdapter<DatabaseMovie, FavouriteMovieAdapter.FavouriteMovieViewHolder>(
        FavouriteMovieDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteMovieViewHolder {
        return FavouriteMovieViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouriteMovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    class FavouriteMovieViewHolder(
        private val binding: ListItemMovieBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.movie?.let { movie ->
                    navigateToMovieDetail(view, movie)
                }
            }
        }

        private fun navigateToMovieDetail(view: View, movie: DatabaseMovie) {
            val direction =
                FavouriteFragmentDirections
                    .actionFavouriteFragmentToMovieDetailFragment(movie.id)
            view.findNavController().navigate(direction)
        }

        fun bind(item: DatabaseMovie) {
            binding.movie = item
            binding.executePendingBindings()
        }
    }
}

class FavouriteMovieDiffCallback : DiffUtil.ItemCallback<DatabaseMovie>() {
    override fun areItemsTheSame(oldItem: DatabaseMovie, newItem: DatabaseMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DatabaseMovie, newItem: DatabaseMovie): Boolean {
        return oldItem == newItem
    }
}
