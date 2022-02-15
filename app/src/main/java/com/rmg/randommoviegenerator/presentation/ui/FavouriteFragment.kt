package com.rmg.randommoviegenerator.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.rmg.randommoviegenerator.presentation.adapters.FavouriteMovieAdapter
import com.rmg.randommoviegenerator.databinding.FragmentFavouriteBinding
import com.rmg.randommoviegenerator.presentation.viewmodels.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private val favouriteViewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val adapter = FavouriteMovieAdapter()

        binding.viewModel = favouriteViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
        binding.rvFavouriteMovie.adapter = adapter

        favouriteViewModel.favouriteMovies.observe(viewLifecycleOwner) { movies ->
            adapter.submitList(movies)
        }

        return binding.root
    }
}