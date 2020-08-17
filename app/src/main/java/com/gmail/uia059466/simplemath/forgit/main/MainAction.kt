package com.gmail.uia059466.simplemath.forgit.main

import com.gmail.uia059466.simplemath.forgit.utils.MviAction

sealed class MainAction : MviAction{
  
  object RunFastLesson   : MainAction()
  object RunListSkill    : MainAction()
  object RunSetting      : MainAction()
}
