package com.gmail.uia059466.simplemath.forgit.listskill

import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

sealed class SkillListViewState : IViewState {
  class LessonList(
          val data: List<DataItemSkill>
                  ) : SkillListViewState() {
    override val fragmentType = FragmentType.SKILL_LIST
  }
}