package com.vladislavmyasnikov.workout_library_and_player_impl.presentation

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch.navigation.NavigationComponent
import com.vladislavmyasnikov.common.arch.navigation.NavigationComponentHolder
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.dialog.WorkoutExerciseCycleDialog
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.host.WorkoutExerciseCycleListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_list.host.WorkoutExerciseListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_set_list.host.WorkoutSetListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.host.WorkoutScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.host.WorkoutListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.host.WorkoutPlayerScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_result.host.WorkoutResultScreenHost
import kotlin.reflect.KClass

sealed class Screens : NavigationComponent {

    object WorkoutListScreen : Screens()
    object WorkoutSetListScreen : Screens()
    class WorkoutExerciseListScreen(val id: Long) : Screens()
    class WorkoutExerciseCycleListScreen(val id: Long) : Screens()
    object WorkoutResultScreen : Screens()
    class WorkoutDetailsScreen(val id: Long) : Screens()
    class WorkoutPlayerScreen(val id: Long) : Screens()
}

sealed class Dialogs : NavigationComponent {

    class WorkoutExerciseCycleDetailsDialog(val id: Long) : Dialogs()
}

object NavigationComponentStore : NavigationComponentHolder() {

    override val fragmentFactory = WorkoutLibraryFeatureComponent.get().fragmentFactory

    override val fragments = mapOf<KClass<out NavigationComponent>, Class<out Fragment>>(
            Screens.WorkoutListScreen::class to WorkoutListScreenHost::class.java,
            Screens.WorkoutSetListScreen::class to WorkoutSetListScreenHost::class.java,
            Screens.WorkoutExerciseListScreen::class to WorkoutExerciseListScreenHost::class.java,
            Screens.WorkoutExerciseCycleListScreen::class to WorkoutExerciseCycleListScreenHost::class.java,
            Screens.WorkoutResultScreen::class to WorkoutResultScreenHost::class.java,
            Screens.WorkoutDetailsScreen::class to WorkoutScreenHost::class.java,
            Screens.WorkoutPlayerScreen::class to WorkoutPlayerScreenHost::class.java,
    )

    override val dialogFragments = mapOf<KClass<out NavigationComponent>, Class<out DialogFragment>>(
            Dialogs.WorkoutExerciseCycleDetailsDialog::class to WorkoutExerciseCycleDialog::class.java,
    )

    override val screenInitLambdas = mapOf<KClass<out NavigationComponent>, (Fragment, NavigationComponent) -> Unit>(
            Screens.WorkoutExerciseListScreen::class to { fragment, screen -> (fragment as WorkoutExerciseListScreenHost).putArguments((screen as Screens.WorkoutExerciseListScreen).id) },
            Screens.WorkoutExerciseCycleListScreen::class to { fragment, screen -> (fragment as WorkoutExerciseCycleListScreenHost).putArguments((screen as Screens.WorkoutExerciseCycleListScreen).id) },
            Screens.WorkoutDetailsScreen::class to { fragment, screen -> (fragment as WorkoutScreenHost).putArguments((screen as Screens.WorkoutDetailsScreen).id) },
            Screens.WorkoutPlayerScreen::class to { fragment, screen -> (fragment as WorkoutPlayerScreenHost).putArguments((screen as Screens.WorkoutPlayerScreen).id) },
    )

    override val dialogInitLambdas = mapOf<KClass<out NavigationComponent>, (DialogFragment, NavigationComponent) -> Unit>(
            Dialogs.WorkoutExerciseCycleDetailsDialog::class to { fragment, dialog -> (fragment as WorkoutExerciseCycleDialog).putArguments((dialog as Dialogs.WorkoutExerciseCycleDetailsDialog).id) }
    )
}