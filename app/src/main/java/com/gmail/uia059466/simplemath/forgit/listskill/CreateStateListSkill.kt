package com.gmail.uia059466.simplemath.forgit.listskill

import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.data.getSkillDescription
import com.gmail.uia059466.simplemath.forgit.fastlesson.Gateway
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

class CreateStateListSkill : ISuspendAction {
 
  override suspend fun createState(gateway: Gateway): List<IViewState> {
  
    val list= mutableListOf<DataItemSkill>()
    for (skill in Skill.values()){
      val numComplected=gateway.getNumComplected(skill)
      val number=gateway.getNumber(skill)
      val dataSkill = DataItemSkill(
              name         = getSkillDescription(skill),
              count        = "$numComplected / $number",
              isComplected = numComplected==number,
              skill        = skill
                                   )
      list.add(dataSkill)
    }
      return listOf(SkillListViewState.LessonList(data = list))
  }
}