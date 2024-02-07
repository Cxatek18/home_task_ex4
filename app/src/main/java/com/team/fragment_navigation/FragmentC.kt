package com.team.fragment_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.team.fragment_navigation.databinding.FragmentCBinding

class FragmentC : Fragment() {

    private var _binding: FragmentCBinding? = null
    private val binding: FragmentCBinding
        get() = _binding ?: throw RuntimeException("FragmentCBinding is null")

    private var textToFragmentC: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseArgs()
        onClickGoBackFragment()
        onClickGoToFragmentD()
        setTextToFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClickGoToFragmentD() {
        binding.buttonToFragmentD.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, FragmentD.newInstance())
                .addToBackStack(FragmentD.TAG_FRAGMENT_D)
                .commit()
        }
    }

    private fun onClickGoBackFragment() {
        binding.buttonToFragmentA.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, FragmentA.newInstance())
                .commit()
        }
    }

    private fun setTextToFragment() {
        binding.textFragmentC.text = textToFragmentC
    }

    private fun parseArgs() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_TEXT_FRAGMENT)) {
            throw RuntimeException("No args - fragment_c")
        }
        textToFragmentC = args.getString(EXTRA_TEXT_FRAGMENT)
    }

    companion object {

        const val TEXT_FRAGMENT = "Это Fragment 3"
        const val TAG_FRAGMENT_C = "fragment_c"
        private const val EXTRA_TEXT_FRAGMENT = "fragment_c"

        fun newInstance(textFragment: String): FragmentC {
            return FragmentC().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_TEXT_FRAGMENT, textFragment)
                }
            }
        }
    }
}