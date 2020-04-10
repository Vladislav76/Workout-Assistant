package com.vladislavmyasnikov.sample_app.di

import com.vladislavmyasnikov.common.utils.ClassLabelLibrary
import com.vladislavmyasnikov.feature_diary_impl.di.component.DaggerDiaryFeatureDependenciesComponent
import com.vladislavmyasnikov.feature_diary_impl.di.component.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.di.component.DaggerExerciseLibraryFeatureDependenciesComponent
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi
import com.vladislavmyasnikov.feature_exercise_library_impl.di.component.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.di.component.DaggerWorkoutLibraryFeatureDependenciesComponent
import com.vladislavmyasnikov.feature_workout_library_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.features_api.diary.DiaryFeatureApi
import com.vladislavmyasnikov.features_api.workout_library.WorkoutLibraryFeatureApi
import com.vladislavmyasnikov.sample_app.presentation.Controller

class FeatureProxyInjector {

    companion object {

        fun prepareFeatures() {
            // supply dependencies
            val diaryApi = DiaryFeatureComponent.initAndGet(
                    DaggerDiaryFeatureDependenciesComponent.builder()
                            .contextHolder(Controller)
                            .build()
            )
            val exerciseLibraryApi = ExerciseLibraryFeatureComponent.initAndGet(
                    DaggerExerciseLibraryFeatureDependenciesComponent.builder()
                            .contextHolder(Controller)
                            .build()
            )
            val workoutLibraryApi = WorkoutLibraryFeatureComponent.initAndGet(
                    DaggerWorkoutLibraryFeatureDependenciesComponent.builder()
                            .contextHolder(Controller)
                            .exerciseLibraryFeatureApi(getExerciseLibraryFeature())
                            .build()
            )

            // get features' labels for debug
            ClassLabelLibrary.addLabels(exerciseLibraryApi.labelLibraryHolder().labels)
            ClassLabelLibrary.addLabels(workoutLibraryApi.labelLibraryHolder().labels)
            ClassLabelLibrary.addLabels(diaryApi.labelLibraryHolder().labels)
        }

        fun getDiaryFeature(): DiaryFeatureApi {
            return DiaryFeatureComponent.get()
        }

        fun getExerciseLibraryFeature(): ExerciseLibraryFeatureApi {
            return ExerciseLibraryFeatureComponent.get()
        }

        fun getWorkoutLibraryFeature(): WorkoutLibraryFeatureApi {
            return WorkoutLibraryFeatureComponent.get()
        }
    }
}