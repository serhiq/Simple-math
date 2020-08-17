package com.gmail.uia059466.simplemath.forgit.fastlesson.gameend

import android.content.Context
import android.content.SharedPreferences
import com.gmail.uia059466.simplemath.forgit.fastlesson.ResultGame

class Prefs(context: Context) : ResultRepository {
  
  private val PREFS_FILENAME = "supersimple"
  
  private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
  
  private val PREF_CORRECT_PERCENT  = "correct_percent"
  private val PREF_AVG_SPEED        = "avg_speed"
  
  private val NUM_ERROR             = "num_error"
  
  private val PREF_TARGET_PERCENT   = "correct_percent"
  
  private var targetPercent: Int
    get() = prefs.getInt(PREF_TARGET_PERCENT, 100)
    set(value) = prefs.edit().putInt(PREF_TARGET_PERCENT, value).apply()
  
  private var correct: Int
    get() = prefs.getInt(PREF_CORRECT_PERCENT, 0)
    set(value) = prefs.edit().putInt(PREF_CORRECT_PERCENT, value).apply()
  
  private var speed: Int
    get() = prefs.getInt(PREF_AVG_SPEED, 0)
    set(value) = prefs.edit().putInt(PREF_AVG_SPEED, value).apply()
  
  private var numberError: Int
    get() = prefs.getInt(NUM_ERROR, 0)
    set(value) = prefs.edit().putInt(NUM_ERROR, value).apply()
  
  override fun getCurrentResult(): ResultGame {
    return ResultGame(correctPercent = correct, speed = speed, error = numberError)
  }

  override fun saveResult(result: ResultGame) {
    correct = result.correctPercent
    speed = result.speed?:0
    numberError = result.error
  }
  
  override fun cleanResult() {
    correct      = 0
    speed        = 0
    numberError  = 0
  }
  
  override fun getUserTarget(): Int {
   return targetPercent }
  
  
 
  private val IS_USE_SAY_NEXT  = "is_use_say_next"
  
  var isUseSayNext: Boolean
    get() = prefs.getBoolean(IS_USE_SAY_NEXT, true)
    set(value) = prefs.edit().putBoolean(IS_USE_SAY_NEXT, value).apply()
  
   
  private val IS_USE_ADDITION_VERBAL  = "is_use_addition_verbal"
  
  var isUseAdditionVerbal: Boolean
    get() = prefs.getBoolean(IS_USE_ADDITION_VERBAL, true)
    set(value) = prefs.edit().putBoolean(IS_USE_ADDITION_VERBAL, value).apply()
  
  private val PREFS_IS_USE_FOCUS = "is_use_focus"
  var isUseFocus: Boolean
    get() = prefs.getBoolean(PREFS_IS_USE_FOCUS, false)
    set(value) = prefs.edit().putBoolean(PREFS_IS_USE_FOCUS, value).apply()
  
  private val PREFS_IS_USE_RULER = "is_use_ruler"
  var isUserRuler: Boolean
    get() = prefs.getBoolean(PREFS_IS_USE_RULER, true)
    set(value) = prefs.edit().putBoolean(PREFS_IS_USE_RULER, value).apply()
}