package com.grando.pokemonapp.presentation.views.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.grando.pokemonapp.R
import com.grando.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.grando.pokemonapp.domain.Attack
import com.grando.pokemonapp.domain.Pokemon
import com.grando.pokemonapp.domain.TypeEnum
import com.grando.pokemonapp.presentation.viewmodels.PokemonDetailViewModel
import com.grando.pokemonapp.presentation.views.adapters.EvolutionsAdapter
import com.grando.pokemonapp.presentation.views.adapters.GenericAdapter
import kotlinx.android.synthetic.main.card_pokemon.*
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding
    private val viewModel: PokemonDetailViewModel by viewModel()
    private val args: PokemonDetailFragmentArgs by navArgs()

    private lateinit var currentPokemon: Pokemon
    private lateinit var typesAdapter: GenericAdapter<TypeEnum>
    private lateinit var fastAttacksAdapter: GenericAdapter<Attack>
    private lateinit var specialAttacksAdapter: GenericAdapter<Attack>
    private lateinit var evolutionsAdapter: GenericAdapter<Pokemon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(layoutInflater)
        viewModel.getCatched()
        val extraPokemon = args.pokemon
        binding.pokemon = extraPokemon
        currentPokemon = extraPokemon

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        configureListeners()
        configureObservers()
    }

    private fun initViews() {
        configureTypesAdapter()
        configureAttacks()
        configureEvolutionsAdapter()
    }

    private fun configureTypesAdapter() {
        typesAdapter = GenericAdapter(R.layout.item_type)
        typesAdapter.setupItems(currentPokemon.types)
        rvTypes.adapter = typesAdapter
    }

    private fun configureAttacks() {
        configureFastAttacksAdapter()
        configureSpecialAttacksAdapter()
    }

    private fun configureFastAttacksAdapter() {
        fastAttacksAdapter = GenericAdapter(R.layout.item_move)
        fastAttacksAdapter.setupItems(currentPokemon.fast)
        rvFastAttacks.adapter = fastAttacksAdapter
        rvFastAttacks.suppressLayout(true)
    }

    private fun configureSpecialAttacksAdapter() {
        specialAttacksAdapter = GenericAdapter(R.layout.item_move)
        specialAttacksAdapter.setupItems(currentPokemon.special)
        rvSpecialAttacks.adapter = specialAttacksAdapter
        rvSpecialAttacks.suppressLayout(true)
    }

    private fun configureEvolutionsAdapter() {
        if (currentPokemon.evolutions.isEmpty()) {
            clEvolution.visibility = View.GONE
        } else {
            evolutionsAdapter = EvolutionsAdapter(R.layout.item_evolution)
            evolutionsAdapter.setupItems(currentPokemon.evolutions)
            rvEvolutions.adapter = evolutionsAdapter
        }
    }

    private fun configureListeners() {
        btnCatch.setOnClickListener {
            viewModel.catch(currentPokemon)
            viewModel.getCatched()
            Toast.makeText(
                requireContext(),
                getString(R.string.gotcha_label),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun configureObservers() {
        viewModel.apply {
            catched.observe(viewLifecycleOwner) { list ->
                val isCatched = list.find { it.id == currentPokemon.id } != null
                configureCatchedPokemon(isCatched)
            }
        }
    }

    private fun configureCatchedPokemon(isCatched: Boolean) {
        btnCatch.visibility = if (isCatched) View.GONE else View.VISIBLE
        ivCatched.visibility = if (isCatched) View.VISIBLE else View.GONE
    }
}