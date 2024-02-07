package com.team.fragment_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.team.fragment_navigation.databinding.FragmentABinding


class FragmentA : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding: FragmentABinding
        get() = _binding ?: throw RuntimeException("FragmentABinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickGoFragmentB()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClickGoFragmentB() {
        binding.buttonToFragmentB.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, FragmentB.newInstance())
                .addToBackStack(FragmentB.TAG_FRAGMENT_D)
                .commit()
        }
    }

    companion object {

        const val TAG_FRAGMENT_A = "fragment_a"

        fun newInstance(): FragmentA {
            return FragmentA()
        }
    }
}