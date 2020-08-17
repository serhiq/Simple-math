package com.gmail.uia059466.simplemath.forgit.listskill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.uia059466.simplemath.forgit.MainActivity
import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.utils.InjectorUtils

class SkillListFragment : Fragment(), SkillListAdapter.LessonListListener {
  
  private lateinit var viewModel: SkillListViewModel
  
  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
                           ): View? {
    val view =
            inflater.inflate(R.layout.list_fragment, container, false) as ViewGroup
    view.clipChildren = false
    
    setupViewModel()
    setupObservers()
    setupOnBackPressed()
    setHasOptionsMenu(true)
    return view
  }
  
  private fun setupObservers() {
    viewModel.runFragment.observe(viewLifecycleOwner, Observer {
      it?.let {
        (activity as MainActivity).runFragment(it)
      }
    })
    
    viewModel.state.observe(viewLifecycleOwner, Observer {
      it?.let {
        render(it)
      }
    })
  }
  
  private fun render(state: SkillListViewState) {
    when (state) {
      is SkillListViewState.LessonList -> {
        val testList = view?.findViewById<RecyclerView>(R.id.lesson_list_rv)
        val adapter = SkillListAdapter(state.data, this)
        testList?.layoutManager = LinearLayoutManager(activity)
        testList?.adapter = adapter
        
        testList?.addItemDecoration(
                DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL))
        val title=getString(R.string.skill_list_title)
        (activity as MainActivity).setTitleAppBar(title, true)
      }
    }
  }
  
  private fun setupViewModel() {
    val application = requireNotNull(this.activity).application
    val viewModelFactory = InjectorUtils.provideViewModelFactory(application)
    viewModel = ViewModelProvider(this, viewModelFactory)[SkillListViewModel::class.java]
  }
  
  private fun setupOnBackPressed() {
    requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
              override fun handleOnBackPressed() {
                goBack()
              }
            })
  }
  
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        goBack()
        return true
      }
    }
    return true
  }
  
  private fun goBack() {
    viewModel.takeAction(SkillListAction.RunMain)
  }
  
  override fun onSkillClicked(item: Skill) {
    viewModel.takeAction(SkillListAction.RunWorkoutList(item))
  }
}