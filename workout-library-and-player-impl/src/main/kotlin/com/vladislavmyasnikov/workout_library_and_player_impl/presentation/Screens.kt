package com.vladislavmyasnikov.workout_library_and_player_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch.navigation.Screen
import com.vladislavmyasnikov.common.arch.navigation.ScreenHolder
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.host.WorkoutExerciseCycleListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_list.host.WorkoutExerciseListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_set_list.host.WorkoutSetListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.host.WorkoutScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.host.WorkoutListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.host.WorkoutPlayerScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_result.host.WorkoutResultScreenHost
import kotlin.reflect.KClass

sealed class Screens : Screen {

    object WorkoutListScreen : Screens()
    object WorkoutSetListScreen : Screens()
    object WorkoutExerciseListScreen : Screens()
    object WorkoutExerciseCycleListScreen : Screens()
    object WorkoutResultScreen : Screens()
    class WorkoutDetailsScreen(val id: Long) : Screens()
    class WorkoutPlayerScreen(val id: Long) : Screens()
}

object ScreenStore : ScreenHolder() {

    override val fragmentFactory = WorkoutLibraryFeatureComponent.get().fragmentFactory

    override val fragments = mapOf<KClass<out Screen>, Class<out Fragment>>(
            Screens.WorkoutListScreen::class to WorkoutListScreenHost::class.java,
            Screens.WorkoutSetListScreen::class to WorkoutSetListScreenHost::class.java,
            Screens.WorkoutExerciseListScreen::class to WorkoutExerciseListScreenHost::class.java,
            Screens.WorkoutExerciseCycleListScreen::class to WorkoutExerciseCycleListScreenHost::class.java,
            Screens.WorkoutResultScreen::class to WorkoutResultScreenHost::class.java,
            Screens.WorkoutDetailsScreen::class to WorkoutScreenHost::class.java,
            Screens.WorkoutPlayerScreen::class to WorkoutPlayerScreenHost::class.java,
    )

    override val initLambdas = mapOf<KClass<out Screen>, (Fragment, Screen) -> Unit>(
            Screens.WorkoutDetailsScreen::class to { fragment, screen -> (fragment as WorkoutScreenHost).putArguments((screen as Screens.WorkoutDetailsScreen).id) },
            Screens.WorkoutPlayerScreen::class to { fragment, screen -> (fragment as WorkoutPlayerScreenHost).putArguments((screen as Screens.WorkoutPlayerScreen).id) },
    )
}