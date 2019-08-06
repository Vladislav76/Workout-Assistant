package com.vladislavmyasnikov.sample_app.di

import android.content.Context
import com.vladislavmyasnikov.core_components.interfaces.ContextHolder
import com.vladislavmyasnikov.diary_feature_api.DiaryFeatureApi
import com.vladislavmyasnikov.feature_diary_impl.di.DaggerDiaryFeatureDependenciesComponent
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_book_api.ExerciseBookFeatureApi
import com.vladislavmyasnikov.feature_exercise_book_impl.di.DaggerExerciseBookFeatureDependenciesComponent
import com.vladislavmyasnikov.feature_exercise_book_impl.di.ExerciseBookFeatureComponent
import com.vladislavmyasnikov.sample_app.presentation.Controller
import com.vladislavmyasnikov.sample_app.presentation.SampleApp

class FeatureProxyInjector {

    companion object {

        fun getDiaryFeature(): DiaryFeatureApi {
            return DiaryFeatureComponent.initAndGet(
                    DaggerDiaryFeatureDependenciesComponent.builder()
                            .screenTitleController(Controller)
                            .fragmentController(Controller)
                            .contextHolder(
                                    object : ContextHolder {
                                        override fun getContext(): Context = SampleApp.appContext!!
                                    }
                            )
                            .build()
            )
        }

        fun getExerciseBookFeature(): ExerciseBookFeatureApi {
            return ExerciseBookFeatureComponent.initAndGet(
                    DaggerExerciseBookFeatureDependenciesComponent.builder()
                            .screenTitleController(Controller)
                            .fragmentController(Controller)
                            .contextHolder(
                                    object : ContextHolder {
                                        override fun getContext(): Context = SampleApp.appContext!!
                                    }
                            )
                            .build()
            )
        }
    }
}