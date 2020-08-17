package com.gmail.uia059466.simplemath.forgit.fastlesson.gameend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.uia059466.simplemath.forgit.MainActivity
import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.utils.IViewState
import com.gmail.uia059466.simplemath.forgit.utils.InjectorUtils

class GameEndFragment : Fragment() {
  
  private lateinit var title: TextView
  
  private lateinit var imageView: ImageView
  private lateinit var accTv: TextView
  private lateinit var speedTv: TextView
  private lateinit var errorTv: TextView
  private lateinit var homeImgBtn: ImageButton
  private lateinit var nextImgBtn: ImageButton
  private lateinit var settingImgBtn: ImageButton
  
  private lateinit var viewModel: GameEndViewModel
  
  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
                           ): View? {
    
    val view = inflater.inflate(
            R.layout.game_end_fragment,
            container,
            false
                               )
    title       = view.findViewById<TextView>(R.id.title)
    imageView   = view.findViewById<ImageView>(R.id.imageView)
    accTv       = view.findViewById<TextView>(R.id.acc_tv)
    speedTv     = view.findViewById<TextView>(R.id.speed_tv)
    errorTv     = view.findViewById<TextView>(R.id.error_tv)
    homeImgBtn  = view.findViewById<ImageButton>(R.id.home_btn)
    nextImgBtn  = view.findViewById<ImageButton>(R.id.next_btn)
    settingImgBtn  = view.findViewById<ImageButton>(R.id.setting_btn)
    
    setupViewModel()
    setupObservers()
    return view
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
      is GameEndViewState.ResultState -> {
        (activity as MainActivity).supportActionBar?.hide()
        title.text = resources.getString(state.titleId)
        imageView.setImageResource(state.idImg)
        accTv.text   = state.acc
        speedTv.text = state.speed.toString()+resources.getString(R.string.speed_label)
        errorTv.text = state.error
        
        homeImgBtn.setOnClickListener { viewModel.takeAction(GameEndAction.RunMain) }
        nextImgBtn.setOnClickListener { viewModel.takeAction(GameEndAction.RunComeBack) }
        settingImgBtn.setOnClickListener { viewModel.takeAction(GameEndAction.RunSetting) }
      }
    }
  }
  
  private fun setupViewModel() {
    val application = requireNotNull(this.activity).application
    val viewModelFactory = InjectorUtils.provideViewModelFactory(application)
    viewModel = ViewModelProvider(this, viewModelFactory)[GameEndViewModel::class.java]
  }
}