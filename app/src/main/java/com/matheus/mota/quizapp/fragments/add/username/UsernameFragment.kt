package com.matheus.mota.quizapp.fragments.add.username


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.matheus.mota.quizapp.R
import com.matheus.mota.quizapp.databinding.FragmentUsernameBinding




open class UsernameFragment : Fragment() {

    private lateinit var _binding: FragmentUsernameBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsernameBinding.inflate(inflater, container, false)
        val view = binding.root
        // Implementations

        startQuizzGame()

        return view
    }
        private fun startQuizzGame() {
            binding.run{
            startGameButton.setOnClickListener {
                if (binding.usernameEditText.text.isNotEmpty()) {
                    // save username
                    findNavController().navigate(R.id.action_usernameFragment_to_quizzGameFragment)
                    Toast.makeText(requireContext(), "Good Luck!", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(requireContext(), "Enter your username, please!", Toast.LENGTH_SHORT).show()
                     }
                }
            }
        }
    }



