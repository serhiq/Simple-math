package com.gmail.uia059466.simplemath.forgit.data

import com.gmail.uia059466.simplemath.forgit.R


fun getSkillDescription(skill: Skill): Int {
  return when (skill) {
    Skill.SAY_NEXT ->           R.string.skill_say_next
    Skill.ADDITION_VERBAL ->    R.string.skill_add_verbal
  }
}