package com.matheus.mota.quizapp.fragments.gameover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.matheus.mota.quizapp.R
import com.matheus.mota.quizapp.databinding.FragmentGameOverBinding
import com.matheus.mota.quizapp.databinding.FragmentQuizzGameBinding
import com.matheus.mota.quizapp.databinding.FragmentUsernameBinding
import com.matheus.mota.quizapp.fragments.add.username.UsernameFragment
import kotlinx.coroutines.launch


class GameOverFragment : Fragment() {

    private lateinit var _binding: FragmentGameOverBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameOverBinding.inflate(inflater, container, false)

        val view = binding.root
        // implementation
        tryAgain()
        return view

    }
    private fun tryAgain(){
        binding.gameOverButton.setOnClickListener{
            findNavController().navigate(R.id.action_gameOverFragment_to_quizzGameFragment)
        }
    }
}
