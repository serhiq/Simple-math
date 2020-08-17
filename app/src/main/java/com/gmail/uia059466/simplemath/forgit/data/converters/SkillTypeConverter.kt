package com.gmail.uia059466.simplemath.forgit.data.converters

import androidx.room.TypeConverter
import com.gmail.uia059466.simplemath.forgit.data.Skill

class SkillTypeConverter {
  @TypeConverter
  fun fromString(skillString: String): Skill {
    for (type in Skill.values()) {
      if (type.code == skillString) {
        return type
      }
    }
    throw IllegalArgumentException("Unknown skill type")
  }
  
  @TypeConverter
  fun toString(skill: Skill): String {
    return skill.code
  }
}