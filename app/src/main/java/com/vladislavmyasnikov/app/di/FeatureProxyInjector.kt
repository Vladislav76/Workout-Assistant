package com.vladislavmyasnikov.app.di

import com.vladislavmyasnikov.common.utils.ClassLabelLibrary
import com.vladislavmyasnikov.workout_diary_api.DiaryFeatureApi
import com.vladislavmyasnikov.workout_diary_impl.di.component.DaggerDiaryFeatureDependenciesComponent
import com.vladislavmyasnikov.workout_diary_impl.di.component.DiaryFeatureComponent
import com.vladislavmyasnikov.exercise_library_impl.di.component.DaggerExerciseLibraryFeatureDependenciesComponent
import com.vladislavmyasnikov.exercise_library_api.ExerciseLibraryFeatureApi
import com.vladislavmyasnikov.exercise_library_impl.di.component.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutLibraryFeatureApi
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.DaggerWorkoutLibraryFeatureDependenciesComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutFeatureComponent
import com.vladislavmyasnikov.app.presentation.Controller

class FeatureProxyInjector {

    companion object {

        fun prepareFeatures() {
            // supply dependencies
            val exerciseLibraryApi = ExerciseLibraryFeatureComponent.initAndGet(
                    DaggerExerciseLibraryFeatureDependenciesComponent.builder()
                            .contextHolder(Controller)
                            .build()
            )
            val workoutLibraryApi = WorkoutFeatureComponent.initAndGet(
                    DaggerWorkoutLibraryFeatureDependenciesComponent.builder()
                            .contextHolder(Controller)
                            .exerciseLibraryFeatureApi(getExerciseLibraryFeature())
                            .build()
            )
            val diaryApi = DiaryFeatureComponent.initAndGet(
                    DaggerDiaryFeatureDependenciesComponent.builder()
                            .contextHolder(Controller)
                            .workoutLibraryFeatureApi(workoutLibraryApi)
                            .build()
            )

            // get features' labels for debug
            ClassLabelLibrary.addLabels(exerciseLibraryApi.labelLibraryHolder().labels)
            ClassLabelLibrary.addLabels(workoutLibraryApi.labelLibraryHolder().labels)
            ClassLabelLibrary.addLabels(diaryApi.labelLibraryHolder().labels)
        }

        fun getExerciseLibraryFeature(): ExerciseLibraryFeatureApi {
            return ExerciseLibraryFeatureComponent.get()
        }

        fun getWorkoutLibraryFeature(): WorkoutLibraryFeatureApi {
            return WorkoutFeatureComponent.get()
        }

        fun getDiaryFeature(): DiaryFeatureApi {
            return DiaryFeatureComponent.get()
        }
    }
}