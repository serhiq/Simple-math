package com.gmail.uia059466.simplemath.forgit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gmail.uia059466.simplemath.forgit.main.CreateStateMain
import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.InjectorUtils.provideStateProvider
import com.gmail.uia059466.simplemath.forgit.utils.OrientationUtils
import com.gmail.uia059466.simplemath.forgit.utils.Router
import timber.log.Timber

class MainActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setupOrientation()
    setupTimber()
    setupToolbar()
    runMainFragment()
  }
  
  private fun setupOrientation() {
    OrientationUtils.lockOrientationLandscape(this)
  }
  
  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
  
  private fun setupToolbar() {
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
  }
  
  fun setTitleAppBar(
          title: String, isDisplayBackButton: Boolean
                    ) {
    supportActionBar?.show()
    supportActionBar?.title = title
    supportActionBar?.setDisplayHomeAsUpEnabled(isDisplayBackButton)
  }
  
  fun runFragment(type: FragmentType) {
    val f = Router.getNextFragment(type)
    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, f).commit()
  }
  
  private fun runMainFragment() {
    val stateProvider = provideStateProvider(application)
    stateProvider.cleanQueue()
    val type = stateProvider.createNextState(CreateStateMain())
    runFragment(type)
  }
}