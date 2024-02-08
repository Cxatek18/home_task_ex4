package com.team.fragment_navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.team.fragment_navigation.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding: FragmentBBinding
        get() = _binding ?: throw RuntimeException("FragmentBBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickBackFragment()
        onClickGoToFragmentC()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClickBackFragment() {
        binding.buttonToBackFragment.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, FragmentA.newInstance())
                .commit()
        }
    }

    private fun onClickGoToFragmentC() {
        binding.buttonToFragmentC.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.main_fragment_container, FragmentC.newInstance(
                        FragmentC.TEXT_FRAGMENT
                    )
                )
                .addToBackStack(FragmentC.TAG_FRAGMENT_C)
                .commit()
        }
    }

    companion object {

        const val TAG_FRAGMENT_D = "fragment_b"

        fun newInstance(): FragmentB {
            return FragmentB()
        }
    }
}