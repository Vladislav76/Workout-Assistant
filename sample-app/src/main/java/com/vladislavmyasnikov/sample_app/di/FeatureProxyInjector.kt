package com.vladislavmyasnikov.sample_app.di

import com.vladislavmyasnikov.feature_diary_impl.di.DaggerDiaryFeatureDependenciesComponent
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi
import com.vladislavmyasnikov.feature_exercise_library_impl.di.DaggerFeatureDependenciesComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.di.FeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.di.DaggerWorkoutLibraryFeatureDependenciesComponent
import com.vladislavmyasnikov.feature_workout_library_impl.di.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.features_api.diary.DiaryFeatureApi
import com.vladislavmyasnikov.features_api.workout_library.WorkoutLibraryFeatureApi
import com.vladislavmyasnikov.sample_app.presentation.Controller

class FeatureProxyInjector {

    companion object {

        fun getDiaryFeature(): DiaryFeatureApi {
            return DiaryFeatureComponent.initAndGet(
                    DaggerDiaryFeatureDependenciesComponent.builder()
                            .screenTitleController(Controller)
                            .contextHolder(Controller)
                            .build()
            )
        }

        fun getExerciseLibraryFeature(): ExerciseLibraryFeatureApi {
            return FeatureComponent.initAndGet(
                    DaggerFeatureDependenciesComponent.builder()
                            .contextHolder(Controller)
                            .build()
            )
        }

        fun getWorkoutLibraryFeature(): WorkoutLibraryFeatureApi {
            return WorkoutLibraryFeatureComponent.initAndGet(
                    DaggerWorkoutLibraryFeatureDependenciesComponent.builder()
                            .screenTitleController(Controller)
                            .contextHolder(Controller)
                            .exerciseLibraryFeatureApi(getExerciseLibraryFeature())
                            .build()
            )
        }
    }
}