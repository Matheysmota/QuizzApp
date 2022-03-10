package com.matheus.mota.quizapp.fragments.gamewin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matheus.mota.quizapp.R
import com.matheus.mota.quizapp.databinding.FragmentGameWinBinding


class GameWinFragment : Fragment() {
    private lateinit var _binding: FragmentGameWinBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameWinBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

}