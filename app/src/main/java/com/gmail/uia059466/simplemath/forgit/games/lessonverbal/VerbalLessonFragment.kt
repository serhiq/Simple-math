package com.gmail.uia059466.simplemath.forgit.games.lessonverbal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.uia059466.simplemath.forgit.MainActivity
import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.fastlesson.ruler.RulerAdapter
import com.gmail.uia059466.simplemath.forgit.fastlesson.ruler.RulerViewState
import com.gmail.uia059466.simplemath.forgit.games.HelpGameDialog
import com.gmail.uia059466.simplemath.forgit.games.HelpMessage
import com.gmail.uia059466.simplemath.forgit.utils.InjectorUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import com.yuyakaido.android.cardstackview.SwipeableMethod

class VerbalLessonFragment : Fragment(),
                             CardStackListener {
  
  private lateinit var homeFab: FloatingActionButton
  private lateinit var helpFab: FloatingActionButton
  
  private lateinit var viewModel: VerbalLessonViewModel
  private lateinit var adapter: CardStackAdapter
  private lateinit var manager: CardStackLayoutManager
  private lateinit var cardStackView: CardStackView
  private lateinit var noFab: FloatingActionButton
  private lateinit var yesFab: FloatingActionButton
  
  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
                           ): View? {
    val view = inflater.inflate(
        
            R.layout.verbal_lesson_fragment,
            container,
            false
                               )
    homeFab = view.findViewById<FloatingActionButton>(R.id.home_fab)
    helpFab = view.findViewById<FloatingActionButton>(R.id.help_fab)
  
    cardStackView = view.findViewById(R.id.card_stack_view)
    
    yesFab = view.findViewById(R.id.yes_fab)
    noFab = view.findViewById(R.id.no_fab)
    manager = CardStackLayoutManager(context, this)
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
  
  private fun render(state: VerbalLessonViewState) {
    (activity as MainActivity).supportActionBar?.hide()
    configureCardStackView(state.game.data)
    setupButton()
    if (state.isUseHelpButton)  displayHelp()
    if (state.isUseHomeButton)  displayHome()
  
    val ruler=state.ruler
    if (ruler!=null) displayRuler(ruler)
  
    setupOnBackPressed()
  }
  
  private fun displayRuler(state: RulerViewState) {
    
    val rulerFab = view?.findViewById<FloatingActionButton>(R.id.ruler_fab)
    rulerFab?.visibility=View.VISIBLE
    val rv = view?.findViewById<RecyclerView>(R.id.ruler_rv)
    val adapter = RulerAdapter(state.data)
    rv?.adapter = adapter
    
    val manager= LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    rv?.layoutManager = manager
    
    rulerFab?.setOnClickListener {
      when(rv?.visibility){
        View.VISIBLE->rv.visibility=View.INVISIBLE
        View.INVISIBLE   ->{rv.visibility=View.VISIBLE;
          manager.scrollToPositionWithOffset(state.scrollTo, 0);
        }
      }
    }
  }
  
  private fun displayHome() {
    homeFab.visibility = View.VISIBLE
    homeFab.setOnClickListener {
      viewModel.takeAction(VerbalLessonAction.Back)
    }
  }
  
  private fun displayHelp() {
    helpFab.visibility = View.VISIBLE
    helpFab.setOnClickListener {
      val state=viewModel.state.value
      when(state){
        is VerbalLessonViewState ->showHelp(state.help)
      }
    }
  }
  
  private fun showHelp(help: HelpMessage) {
    val dialog = HelpGameDialog.newInstance(help.text.getString(requireContext()))
    val fragmentManager = activity?.supportFragmentManager
    fragmentManager?.let { dialog.show(it, HelpGameDialog.TAG) }
  }
  
  private fun setupViewModel() {
    val application = requireNotNull(this.activity).application
    val viewModelFactory = InjectorUtils.provideViewModelFactory(application)
    viewModel = ViewModelProvider(this, viewModelFactory)[VerbalLessonViewModel::class.java]
    viewModel.init()
  }
  
  private fun configureCardStackView(list: List<String>) {
    adapter =
            CardStackAdapter(list)
    initCardView(manager, adapter, cardStackView)
    manager.setSwipeableMethod(SwipeableMethod.Automatic)
  }
  
  private fun setupButton() {
    noFab.visibility = View.VISIBLE
    noFab.setOnClickListener {
      
      viewModel.takeAction(VerbalLessonAction.NextCard(error = adapter.words[manager.topPosition]))
      
      val setting = SwipeAnimationSetting.Builder().setDirection(Direction.Left)
              .setDuration(Duration.Normal.duration).setInterpolator(AccelerateInterpolator())
              .build()
      manager.setSwipeAnimationSetting(setting)
      cardStackView.swipe()
    }
    
    yesFab.visibility = View.VISIBLE
    yesFab.setOnClickListener {
      viewModel.takeAction(VerbalLessonAction.NextCard(right = adapter.words[manager.topPosition]))
      
      val setting = SwipeAnimationSetting.Builder().setDirection(Direction.Right)
              .setDuration(Duration.Normal.duration).setInterpolator(AccelerateInterpolator())
              .build()
      manager.setSwipeAnimationSetting(setting)
      cardStackView.swipe()
    }
  }
  
  private fun initCardView(
          manager: CardStackLayoutManager, adapter: CardStackAdapter, cardView: CardStackView
                          ) {
    manager.setStackFrom(StackFrom.None)
    manager.setVisibleCount(3)
    manager.setTranslationInterval(8.0f)
    manager.setScaleInterval(0.95f)
    manager.setSwipeThreshold(0.3f)
    manager.setMaxDegree(20.0f)
    manager.setDirections(Direction.HORIZONTAL)
    manager.setCanScrollHorizontal(true)
    manager.setCanScrollVertical(true)
    manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
    manager.setOverlayInterpolator(LinearInterpolator())
    cardView.layoutManager = manager
    cardView.adapter = adapter
    cardView.itemAnimator.apply {
      if (this is DefaultItemAnimator) {
        supportsChangeAnimations = false
      }
    }
  }
  
  override fun onCardDisappeared(view: View?, position: Int) {
  }
  
  override fun onCardDragging(direction: Direction?, ratio: Float) {
  }
  
  override fun onCardSwiped(direction: Direction?) {
  }
  
  override fun onCardCanceled() {
  }
  
  override fun onCardAppeared(view: View?, position: Int) {
  }
  
  override fun onCardRewound() {
  }
  
  private fun setupOnBackPressed() {
    requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
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
    viewModel.takeAction(VerbalLessonAction.Back)
  }
}