package com.singularitycoder.flukefriends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.singularitycoder.flukefriends.databinding.FragmentFlukesBinding
import dagger.hilt.android.AndroidEntryPoint

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

    private var skillLevelParam: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skillLevelParam = arguments?.getString(ARG_PARAM_SKILL_LEVEL)
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
        etSearch.hint = "Search ${skillLevelParam?.lowercase()} skills"
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