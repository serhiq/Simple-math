package com.gmail.uia059466.simplemath.forgit.listskill

import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.utils.MviAction

sealed class SkillListAction : MviAction {
  class  RunWorkoutList(val skill: Skill) : SkillListAction()
  object RunMain                          : SkillListAction()
}