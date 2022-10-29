package com.singularitycoder.flukefriends

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.singularitycoder.flukefriends.databinding.FragmentFlukesBinding
import dagger.hilt.android.AndroidEntryPoint

// If possible, peer to peer else Google login, NodeJS server
// Basic idea is to match the meaning of the people who search for the same thing within the same durations - maybe a min, 15 min, 1 hr etc. That is something the user sets
// So, add your search query, set the duration it must expire. Wait for other peopel who search the same thing within that duration. If multiple users search for the same thing, if the meaning is the same, then create a chat group or a fluke with all of them. Thats it.
// https://github.com/huggingface/tflite-android-transformers for matching the meaning of the sentence
// https://github.com/huggingface
// https://github.com/monologg/transformers-android-demo

@AndroidEntryPoint
class FlukesFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(skillLevel: String) = FlukesFragment().apply {
            arguments = Bundle().apply { putString(ARG_PARAM_SKILL_LEVEL, skillLevel) }
        }
    }

    private lateinit var binding: FragmentFlukesBinding

    private val flukesAdapter = FlukesAdapter()
    private val duplicateFlukeList = mutableListOf<Fluke>()

    private var tabTitleParam: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabTitleParam = arguments?.getString(ARG_PARAM_SKILL_LEVEL)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFlukesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setupUI()
        binding.setupUserActionListeners()
        observeForData()
    }

    private fun FragmentFlukesBinding.setupUI() {
        etSearch.hint = "Search ${tabTitleParam?.lowercase()}"
        rvFlukes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = flukesAdapter
        }
        val dummyFlukesList = listOf<Fluke>(
            Fluke(
                id = 1,
                title = "How to solve this problem x + y = z",
                expiry = timeNow,
                count = 9
            ),
            Fluke(
                id = 1,
                title = "How to solve this problem x + y = z",
                expiry = timeNow,
                count = 9
            ),
            Fluke(
                id = 1,
                title = "How to solve this problem x + y = z",
                expiry = timeNow,
                count = 9
            )
        )
        flukesAdapter.flukeList = dummyFlukesList
        flukesAdapter.notifyDataSetChanged()
    }

    private fun FragmentFlukesBinding.setupUserActionListeners() {
        etSearch.doAfterTextChanged { keyWord: Editable? ->
            ibClearSearch.isVisible = keyWord.isNullOrBlank().not()
            if (keyWord.isNullOrBlank()) {
                flukesAdapter.flukeList = duplicateFlukeList
            } else {
                flukesAdapter.flukeList = flukesAdapter.flukeList.filter { it: Fluke -> it.title.contains(keyWord) }
            }
            flukesAdapter.notifyDataSetChanged()
        }
        ibClearSearch.setOnClickListener {
            etSearch.setText("")
        }
    }

    private fun observeForData() {

    }
}

const val ARG_PARAM_SKILL_LEVEL = "ARG_PARAM_SKILL_LEVEL"