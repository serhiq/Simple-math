package com.gmail.uia059466.simplemath.forgit.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.uia059466.simplemath.forgit.MainActivity
import com.gmail.uia059466.simplemath.forgit.R
import com.gmail.uia059466.simplemath.forgit.utils.InjectorUtils

class MainFragment : Fragment() {
  
  private lateinit var viewModel: MainViewModel
  
  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
                           ): View? {
    
    val view = inflater.inflate(
            R.layout.main_fragment,
            container,
            false)
    
    val fastLessonCv=view.findViewById<View>(R.id.fastgame_cv)
    fastLessonCv.setOnClickListener { viewModel.takeAction(MainAction.RunFastLesson) }
  
    val gameSelectCv=view.findViewById<View>(R.id.gameselect_cv)
    gameSelectCv.setOnClickListener { viewModel.takeAction(MainAction.RunListSkill) }
   
    val settingCv=view.findViewById<View>(R.id.setting_cv)
    settingCv.setOnClickListener { viewModel.takeAction(MainAction.RunSetting) }
  
    setupAppBar()
    setupViewModel()
    setupObservers()
    setupOnBackPressed()
    setHasOptionsMenu(true)
    return view
  }
  
  private fun setupAppBar() {
    val mainActivity = activity as MainActivity
    mainActivity.setTitleAppBar(getString(R.string.main_title_app_bar_label), false)
  }
  
  private fun setupObservers() {
    viewModel.runFragment.observe(viewLifecycleOwner, Observer {
      it?.let {
        (activity as MainActivity).runFragment(it)
      }
    })
  }
  
  private fun setupViewModel() {
    val application = requireNotNull(this.activity).application
    val viewModelFactory = InjectorUtils.provideViewModelFactory(application)
    viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
  }
  
  private fun setupOnBackPressed() {
    requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner,
                         object : OnBackPressedCallback(true) {
                           override fun handleOnBackPressed() {
                             activity?.finish()
                           }
                         }
                        )
  }
}