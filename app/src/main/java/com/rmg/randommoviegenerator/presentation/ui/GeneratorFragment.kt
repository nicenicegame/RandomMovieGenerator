package com.rmg.randommoviegenerator.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.rmg.randommoviegenerator.databinding.FragmentGeneratorBinding
import com.rmg.randommoviegenerator.presentation.viewmodels.GeneratorViewModel
import com.google.android.material.snackbar.Snackbar
import com.rmg.randommoviegenerator.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneratorFragment : Fragment() {

    private val generatorViewModel: GeneratorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGeneratorBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = generatorViewModel

        binding.toolbar.apply {
            setOnMenuItemClickListener { menuItem ->
                findNavController().navigate(menuItem.itemId)
                true
            }
        }

        subscribeUi(binding)

        return binding.root
    }

    private fun subscribeUi(binding: FragmentGeneratorBinding) {
        generatorViewModel.genres.observe(viewLifecycleOwner) { genres ->
            ArrayAdapter(requireContext(), R.layout.list_item_genre, genres).also { adapter ->
                binding.acGenres.setAdapter(adapter)
                binding.acGenres.setOnItemClickListener { _, _, position, _ ->
                    val selectedGenre = adapter.getItem(position)
                    selectedGenre?.let {
                        generatorViewModel.setSelectedGenre(selectedGenre)
                    }
                }
            }
        }
        generatorViewModel.snackBarMessage.observe(viewLifecycleOwner) { message ->
            if (message != null) {
                Snackbar
                    .make(binding.root, message, Snackbar.LENGTH_SHORT)
                    .setAction("Undo") { generatorViewModel.onUndoClick() }
                    .show()
                generatorViewModel.closeSnackBar()
            }
        }
    }
}