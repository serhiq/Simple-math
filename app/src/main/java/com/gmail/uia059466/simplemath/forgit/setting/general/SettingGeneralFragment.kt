package com.gmail.uia059466.simplemath.forgit.setting.general

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Switch
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.uia059466.simplemath.forgit.MainActivity
import com.gmail.uia059466.simplemath.forgit.R
import com.gmail.uia059466.simplemath.forgit.utils.InjectorUtils

class SettingGeneralFragment : Fragment() {
  
  private lateinit var viewModel: SettingGeneralViewModel
  
  private lateinit var rvPartFastGame:RelativeLayout
  
  private lateinit var llReadTrainer:LinearLayout
  
  private lateinit var isUseFocusMode      :Switch
  private lateinit var isShowRuler         :Switch
  
  private lateinit var rvAboutUs          :RelativeLayout
  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
                           ): View? {
    
    val view = inflater.inflate(
            R.layout.setting_general_fragment,
            container,
            false
               )
    llReadTrainer       = view.findViewById(R.id.read_trainer_ll)
   
    rvPartFastGame      = view.findViewById(R.id.part_fast_game_rv)
    isUseFocusMode      = view.findViewById(R.id.is_use_focus_mode_switch)
    isShowRuler         = view.findViewById(R.id.is_use_ruler_switch)
    
    rvAboutUs         = view.findViewById(R.id.about_us_rv)
    
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
  
  private fun render(state: SettingGeneralViewState) {
    when (state) {
      is SettingGeneralViewState.SettingMainState -> {
        val title=getString(R.string.setting_general_title)
        val mainActivity = activity as MainActivity
        mainActivity.setTitleAppBar(title, true)
  

        isUseFocusMode.isChecked = state.isUseFocusMode
        isUseFocusMode.setOnCheckedChangeListener { _
                                                         , isChecked ->
          viewModel.takeAction(SettingGeneralAction.IsUseFocusMode(isChecked))
        }
  
        isShowRuler.isChecked = state.isUseRuler
        isShowRuler.setOnCheckedChangeListener { _
                                                                        , isChecked ->
          viewModel.takeAction(SettingGeneralAction.IsUseRuler(isChecked))
        }
  
        rvPartFastGame.setOnClickListener {
          viewModel.takeAction(SettingGeneralAction.RunForPartsOfFastGame)
        }

        llReadTrainer.setOnClickListener {
          showDownloadReadTrainer()
        }
  
        rvAboutUs.setOnClickListener {
          viewModel.takeAction(SettingGeneralAction.RunAbout)
        }
      }
    }
  }
  
  private fun showDownloadReadTrainer() {
      try {
        startActivity(
                Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + "com.gmail.uia059466.readtrainer")
                      )
                     )
      } catch (e: ActivityNotFoundException) {
        startActivity(
                Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + "com.gmail.uia059466.readtrainer")
                      )
                     )
      }
  
  }
  
  private fun setupViewModel() {
    val application = requireNotNull(this.activity).application
    val viewModelFactory = InjectorUtils.provideViewModelFactory(application)
    viewModel = ViewModelProvider(this, viewModelFactory)[SettingGeneralViewModel::class.java]
  }
  
  private fun setupOnBackPressed() {
    requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
              override fun handleOnBackPressed() {
                viewModel.takeAction(SettingGeneralAction.RunMainFragment)
              }
            })
  }
  
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        viewModel.takeAction(SettingGeneralAction.RunMainFragment)
        return true
      }
    }
    return true
  }
}