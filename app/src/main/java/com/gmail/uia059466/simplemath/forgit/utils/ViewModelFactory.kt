package com.gmail.uia059466.simplemath.forgit.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.uia059466.simplemath.forgit.fastlesson.gameend.GameEndViewModel
import com.gmail.uia059466.simplemath.forgit.games.lessonverbal.VerbalLessonViewModel
import com.gmail.uia059466.simplemath.forgit.listskill.SkillListViewModel
import com.gmail.uia059466.simplemath.forgit.listworkout.WorkoutListViewModel
import com.gmail.uia059466.simplemath.forgit.main.MainViewModel
import com.gmail.uia059466.simplemath.forgit.setting.about.SettingAboutViewModel
import com.gmail.uia059466.simplemath.forgit.setting.fastgame.SettingFastGameViewModel
import com.gmail.uia059466.simplemath.forgit.setting.general.SettingGeneralViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
        private val a: Application
                                  ) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel> create(modelClass: Class<T>) =
    with(modelClass) {
      when {
        isAssignableFrom(MainViewModel::class.java)            ->
          MainViewModel(a, InjectorUtils.provideStateProvider(a))
        isAssignableFrom(VerbalLessonViewModel::class.java)            ->
          VerbalLessonViewModel(a, InjectorUtils.provideStateProvider(a),
                                repository = InjectorUtils.provideLessonRepository(a),
                                resultRepository = InjectorUtils.provideResultRepository(a))
  
        isAssignableFrom(GameEndViewModel::class.java)            ->
          GameEndViewModel(a, InjectorUtils.provideStateProvider(a))
  
        isAssignableFrom(SkillListViewModel::class.java)            ->
          SkillListViewModel(a, InjectorUtils.provideStateProvider(a))
        isAssignableFrom(WorkoutListViewModel::class.java)            ->
         WorkoutListViewModel(a, InjectorUtils.provideStateProvider(a))
        isAssignableFrom(SettingGeneralViewModel::class.java)            ->
          SettingGeneralViewModel(a, InjectorUtils.provideStateProvider(a))
        isAssignableFrom(SettingFastGameViewModel::class.java)            ->
          SettingFastGameViewModel(a, InjectorUtils.provideStateProvider(a))
        isAssignableFrom(SettingAboutViewModel::class.java)            ->
          SettingAboutViewModel(a, InjectorUtils.provideStateProvider(a))
  
        else                                              ->
          throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
      }
    } as T
}