package com.gmail.uia059466.simplemath.forgit.utils

import android.app.Application
import com.gmail.uia059466.simplemath.forgit.data.db.AppDatabase
import com.gmail.uia059466.simplemath.forgit.fastlesson.Gateway
import com.gmail.uia059466.simplemath.forgit.fastlesson.LessonRepositoryImpl
import com.gmail.uia059466.simplemath.forgit.fastlesson.gameend.ResultRepository
import com.gmail.uia059466.simplemath.forgit.fastlesson.gameend.Prefs

/**
 * Static methods used to inject classes needed for various Fragments.
 */
object InjectorUtils {
  
  fun provideViewModelFactory(application:Application): ViewModelFactory {
    return ViewModelFactory(application)
  }
  
  fun provideStateProvider(a: Application): StateProvider {
    return DefaultStateProvider.getInstance(repository = provideLessonRepository(a))
  }
  
  fun provideLessonRepository(a: Application): Gateway {
    return LessonRepositoryImpl(db = provideDatabase(a),prefs = Prefs(a))
  }
  
  private fun provideDatabase(a: Application): AppDatabase {
    return AppDatabase.getInstance(a)
  }
  
  fun provideResultRepository(a: Application): ResultRepository {
    return Prefs(a)
  }
}