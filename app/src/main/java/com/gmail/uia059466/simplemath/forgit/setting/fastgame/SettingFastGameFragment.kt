package com.gmail.uia059466.simplemath.forgit.setting.fastgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.uia059466.simplemath.forgit.MainActivity
import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.utils.IViewState
import com.gmail.uia059466.simplemath.forgit.utils.InjectorUtils

class SettingFastGameFragment : Fragment() {

  private lateinit var viewModel: SettingFastGameViewModel
  
  private lateinit var isUseSayNext             :Switch
  private lateinit var isUseAdditionVerbal      :Switch

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupViewModel()
  }

  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
                           ): View? {
    val view = inflater.inflate(
            R.layout.setting_fast_game_fragment,
            container,
            false
                               )
    isUseSayNext             = view.findViewById(R.id.is_use_say_next_switch)
    isUseAdditionVerbal      = view.findViewById(R.id.is_use_addition_verbal_switch)
    
    setupObservers()
    setupOnBackPressed()
    setHasOptionsMenu(true)
    return view
  }

  private fun setupOnBackPressed() {
    requireActivity().onBackPressedDispatcher
      .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
          viewModel.takeAction(SettingFastGameAction.RunGeneralSetting)
        }
      })
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        viewModel.takeAction(SettingFastGameAction.RunGeneralSetting)
        return true
      }
    }
    return true
  }

  private fun setupObservers() {
    viewModel.state.observe(viewLifecycleOwner, Observer {
      it?.let {
        render(it)
      }
    })
  
    viewModel.runFragment.observe(viewLifecycleOwner, Observer {
      it?.let {
        (activity as MainActivity).runFragment(it)
      }
    })
  }

  private fun render(state: IViewState) {
    when (state) {
      is SettingFastGameViewState.SettingFastGame -> {

        val mainActivity = activity as MainActivity
        mainActivity.setTitleAppBar(getString(R.string.setting_fast_game_title), true)

        isUseSayNext.isChecked = state.isUseSayNext
        isUseSayNext.setOnCheckedChangeListener { _
                                                  , isChecked ->
        viewModel.takeAction(SettingFastGameAction.IncludeInComplexGame(Skill.SAY_NEXT, (isChecked))) }
  
        isUseAdditionVerbal.isChecked = state.isUseAdditionVerbal
        isUseAdditionVerbal.setOnCheckedChangeListener { _
                                                         , isChecked ->
        viewModel.takeAction(SettingFastGameAction.IncludeInComplexGame(Skill.ADDITION_VERBAL, (isChecked))) }
      }
    }
  }

  private fun setupViewModel() {
    val application = requireNotNull(this.activity).application
    val viewModelFactory = InjectorUtils.provideViewModelFactory(application)
    viewModel = ViewModelProvider(this, viewModelFactory)[SettingFastGameViewModel::class.java]
  }
}