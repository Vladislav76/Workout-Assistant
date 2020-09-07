package com.vladislavmyasnikov.workout_library_and_player_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.adapter.WorkoutExerciseCycleAdapter
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_list.adapter.WorkoutExerciseAdapter2
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_set_list.adapter.WorkoutSetAdapter
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.adapter.WorkoutExerciseAdapter
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.adapter.WorkoutAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides @PerScreen
    fun provide1() = WorkoutAdapter()

    @Provides @PerScreen
    fun provide2() = WorkoutExerciseAdapter()

    @Provides @PerScreen
    fun provide3() = WorkoutSetAdapter()

    @Provides @PerScreen
    fun provide4() = WorkoutExerciseAdapter2()

    @Provides @PerScreen
    fun provide5() = WorkoutExerciseCycleAdapter()
}