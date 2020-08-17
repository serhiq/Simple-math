package com.gmail.uia059466.simplemath.forgit.data.workout

import androidx.room.TypeConverter
import com.gmail.uia059466.simplemath.forgit.data.WorkoutType

class WorkoutTypeConverter {
  @TypeConverter
  fun fromString(code: String): WorkoutType {
    for (type in WorkoutType.values()) {
      if (type.code == code) {
        return type
      }
    }
    throw IllegalArgumentException("Unknown workout type")
  }
  
  @TypeConverter
  fun toString(gameType: WorkoutType): String {
    return gameType.code
  }
}