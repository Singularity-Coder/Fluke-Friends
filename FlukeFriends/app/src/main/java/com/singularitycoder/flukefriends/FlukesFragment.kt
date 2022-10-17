package com.singularitycoder.flukefriends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

//    private val skillAdapter = SkillAdapter()
//    private val duplicateSkillList = mutableListOf<Skill>()

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
//            adapter = skillAdapter
        }
    }

    private fun FragmentFlukesBinding.setupUserActionListeners() {
//        etSearch.doAfterTextChanged { keyWord: Editable? ->
//            ibClearSearch.isVisible = keyWord.isNullOrBlank().not()
//            if (keyWord.isNullOrBlank()) {
//                skillAdapter.skillList = duplicateSkillList
//            } else {
//                skillAdapter.skillList = skillAdapter.skillList.filter { it: Skill -> it.name.contains(keyWord) }
//            }
//            skillAdapter.notifyDataSetChanged()
//        }
        ibClearSearch.setOnClickListener {
            etSearch.setText("")
        }
    }

    private fun observeForData() {

    }
}

const val ARG_PARAM_SKILL_LEVEL = "ARG_PARAM_SKILL_LEVEL"