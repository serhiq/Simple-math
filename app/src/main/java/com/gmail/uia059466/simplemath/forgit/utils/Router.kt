package com.gmail.uia059466.simplemath.forgit.utils

import androidx.fragment.app.Fragment
import com.gmail.uia059466.simplemath.forgit.fastlesson.gameend.GameEndFragment
import com.gmail.uia059466.simplemath.forgit.games.lessonverbal.VerbalLessonFragment
import com.gmail.uia059466.simplemath.forgit.listskill.SkillListFragment
import com.gmail.uia059466.simplemath.forgit.listworkout.WorkoutListFragment
import com.gmail.uia059466.simplemath.forgit.main.MainFragment
import com.gmail.uia059466.simplemath.forgit.setting.about.SettingAboutFragment
import com.gmail.uia059466.simplemath.forgit.setting.fastgame.SettingFastGameFragment
import com.gmail.uia059466.simplemath.forgit.setting.general.SettingGeneralFragment

object Router {
  
  fun getNextFragment(type: FragmentType): Fragment {
    return when (type) {
      FragmentType.MAIN                  -> MainFragment()
      FragmentType.TEST_LESSON           -> VerbalLessonFragment()
      FragmentType.RESULT                -> GameEndFragment()
      FragmentType.SKILL_LIST            -> SkillListFragment()
      FragmentType.WORKOUT_LIST          -> WorkoutListFragment()
      FragmentType.SETTING_GENERAL       -> SettingGeneralFragment()
      FragmentType.SETTING_FAST_GAME     -> SettingFastGameFragment()
      FragmentType.SETTING_ABOUT         -> SettingAboutFragment()
    }
  }
}