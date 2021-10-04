package com.grando.pokemonapp.presentation.views.fragments

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.grando.pokemonapp.R
import com.grando.pokemonapp.databinding.FragmentSplashBinding
import kotlinx.android.synthetic.main.fragment_splash.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSplashBinding.inflate(layoutInflater).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lavPikachu.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                val extras = FragmentNavigatorExtras(
                    ivLogo to getString(R.string.image_logo)
                )
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToPokemonListFragment(),
                    extras
                )
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}
        })
    }
}