package com.team.fragment_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.team.fragment_navigation.databinding.FragmentCBinding
import com.team.fragment_navigation.databinding.FragmentDBinding

class FragmentD : Fragment() {

    private var _binding: FragmentDBinding? = null
    private val binding: FragmentDBinding
        get() = _binding ?: throw RuntimeException("FragmentDBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickGoToFragmentB()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClickGoToFragmentB() {
        binding.buttonToFragmentB.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, FragmentB.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {

        const val TAG_FRAGMENT_D = "fragment_d"

        fun newInstance(): FragmentD {
            return FragmentD()
        }
    }
}