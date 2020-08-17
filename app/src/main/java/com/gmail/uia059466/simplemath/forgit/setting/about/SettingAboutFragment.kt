package com.gmail.uia059466.simplemath.forgit.setting.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.uia059466.simplemath.forgit.MainActivity
import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.utils.InjectorUtils

class SettingAboutFragment : Fragment() {
  
  private lateinit var viewModel: SettingAboutViewModel
  
  private lateinit var rvWebsite    :RelativeLayout
  private lateinit var rvTermsOfUse :RelativeLayout
  private lateinit var rvPrivacy    :RelativeLayout
  
  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
                           ): View? {
    
    val view = inflater.inflate(
            R.layout.setting_about_fragment,
            container,
            false
               )
   
    rvWebsite         = view.findViewById(R.id.website_rv)
    rvTermsOfUse      = view.findViewById(R.id.terms_of_use_rv)
    rvPrivacy         = view.findViewById(R.id.privacy_rv)
    
    setupAppBar()
    setupViewModel()
    setupObservers()
    setupOnBackPressed()
    setHasOptionsMenu(true)
    return view
  }
  
  private fun setupAppBar() {
    val mainActivity = activity as MainActivity
    mainActivity.setTitleAppBar(getString(R.string.setting_about_app_bar_label), true)
  }
  
  private fun setupObservers() {
    viewModel.runFragment.observe(viewLifecycleOwner, Observer {
      it?.let {
        (activity as MainActivity).runFragment(it)
      }
    })
    viewModel.link.observe(viewLifecycleOwner, Observer {
      it?.let {
        showWebPage(it)
      }
    })
    
  
    viewModel.state.observe(viewLifecycleOwner, Observer {
      it?.let {
        render(it)
      }
    })
  }
  
  private fun render(state: SettingAboutViewState) {
    when (state) {
      is SettingAboutViewState.SettingMainState -> {
        rvWebsite.setOnClickListener {
          viewModel.takeAction(SettingAboutAction.DisplayOfficialWebsite)
        }
        rvTermsOfUse.setOnClickListener{
          viewModel.takeAction(SettingAboutAction.DisplayTermOfUse)
        }
        rvPrivacy.setOnClickListener{
          viewModel.takeAction(SettingAboutAction.DisplayPrivacy)
        }
      }
    }
  }
  
  private fun showWebPage(url: String) {
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
  }
  
  private fun setupViewModel() {
    val application = requireNotNull(this.activity).application
    val viewModelFactory = InjectorUtils.provideViewModelFactory(application)
    viewModel = ViewModelProvider(this, viewModelFactory)[SettingAboutViewModel::class.java]
  }
  
  private fun setupOnBackPressed() {
    requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
              override fun handleOnBackPressed() {
                viewModel.takeAction(SettingAboutAction.RunMainSetting)
              }
            })
  }
  
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        viewModel.takeAction(SettingAboutAction.RunMainSetting)
        return true
      }
    }
    return true
  }
}