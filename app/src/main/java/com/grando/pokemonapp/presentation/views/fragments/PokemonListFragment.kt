package com.grando.pokemonapp.presentation.views.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grando.pokemonapp.R
import com.grando.pokemonapp.databinding.FragmentPokemonListBinding
import com.grando.pokemonapp.framework.utils.State
import com.grando.pokemonapp.presentation.utils.extensions.hideKeyboard
import com.grando.pokemonapp.presentation.viewmodels.PokemonListViewModel
import com.grando.pokemonapp.presentation.views.adapters.PokemonAdapter
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonListFragment : Fragment() {

    private val viewModel: PokemonListViewModel by viewModel()
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPokemonListBinding.inflate(layoutInflater).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        configureListeners()
        fetchPokemons()
    }

    private fun initViews() {
        configurePokemonsAdapter()
    }

    private fun configurePokemonsAdapter() {
        adapter = PokemonAdapter { pokemon, view ->
            view.transitionName = getString(R.string.image_transition_name)
            val extras = FragmentNavigatorExtras(
                view to getString(R.string.image_transition_name)
            )
            findNavController().navigate(
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(
                    pokemon
                ), extras
            )
        }
        rvPokemons.adapter = adapter
    }

    private fun configureListeners() {
        etSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                activity?.hideKeyboard()
                val query = v.text.toString()
                if (query.isEmpty())
                    fetchPokemons()
                else
                    searchPokemon(query)
                true
            } else {
                false
            }
        }
        rvPokemons.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    (recyclerView.layoutManager as? GridLayoutManager)?.let {
                        val visibleThreshold = 2
                        val lastItem = it.findLastCompletelyVisibleItemPosition()
                        val currentTotalCount = it.itemCount

                        if (currentTotalCount <= lastItem + visibleThreshold) {
                            fetchPokemons(nextPage = true)
                        }
                    }
                }
            }
        })
    }

    private fun fetchPokemons(nextPage: Boolean = false) {
        if (!nextPage) resetViews()
        viewModel.getAllPokemons().observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.LoadingState -> isLoading(true, nextPage)
                is State.ErrorState -> isLoading(false)
                is State.DataState -> {
                    viewModel.currentPage += 10
                    val pokemons = state.data

                    if (pokemons.isEmpty()) {
                        configureEmptyList()
                    } else {
                        pokemons.forEach { pkm -> viewModel.updateIfCatched(pkm) }
                        adapter.submitList(pokemons)
                    }

                    isLoading(false)
                }
            }
        }
    }

    private fun searchPokemon(name: String) {
        resetViews()
        viewModel.searchPokemonByName(name).observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.LoadingState -> isLoading(true)
                is State.ErrorState -> isLoading(false)
                is State.DataState -> {
                    val pokemon = state.data

                    if (pokemon.name.isEmpty()) {
                        configureEmptyList()
                    } else {
                        viewModel.updateIfCatched(pokemon)
                        adapter.submitList(listOf(pokemon))
                    }

                    isLoading(false)
                }
            }
        }
    }

    private fun isLoading(status: Boolean, nextPage: Boolean = false) {
        lavLoading.visibility = if (status && !nextPage) View.VISIBLE else View.GONE
        lavHorizontalLoading.visibility = if (status && nextPage) View.VISIBLE else View.GONE
    }

    private fun resetViews() {
        viewModel.currentPage = 10
        adapter.resetAdapter()
        rvPokemons.visibility = View.VISIBLE
        tvEmpty.visibility = View.GONE
        lavEmpty.visibility = View.GONE
    }

    private fun configureEmptyList() {
        rvPokemons.visibility = View.GONE
        tvEmpty.visibility = View.VISIBLE
        lavEmpty.visibility = View.VISIBLE
        lavEmpty.playAnimation()
    }

}