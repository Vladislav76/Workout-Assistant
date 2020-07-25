package com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry_list.content

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Event
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.interfaces.OnBackPressedListener
import com.vladislavmyasnikov.common.presentation.view.components.VMToolbarFragment
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.domain.model.ShortDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodel.DiaryEntryListVM
import javax.inject.Inject

class DiaryEntryListToolbarContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<List<ShortDiaryEntry>>(), OnBackPressedListener {

    override val viewModel: DiaryEntryListVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(DiaryEntryListVM::class.java)
    }

    private val onActionClickCallback = Toolbar.OnMenuItemClickListener { item: MenuItem ->
        when (item.itemId) {
            R.id.add_diary_entry_action -> {
                bus.sendPacket(Message.NewItemMessage())
                true
            }
            R.id.delete_diary_entries_action -> {
                viewModel.isSelectMode = true
                true
            }
            R.id.delete_diary_entries_forever_action -> {
                viewModel.delete()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addMenuListener(onActionClickCallback)
        viewModel.trigger(Event.SelectionModeChangeEvent::class)
        viewModel.trigger(Event.SelectedItemAmountChangeEvent::class)
    }

    override fun onReceiveEvent(event: Event) {
        when (event) {
            is Event.SelectedItemAmountChangeEvent -> {
                if (event.amount < 0) setTitle(R.string.diary_entry_list_title) else setTitle(event.amount.toString())
            }
            is Event.SelectionModeChangeEvent -> {
                val menu = if (event.isSelected) R.menu.menu_delete_entries_action else R.menu.menu_diary_entry_list
                replaceMenu(menu)
            }
        }
    }

    override fun onBackPressed(): Boolean {
        if (viewModel.isSelectMode) {
            viewModel.isSelectMode = false
            return true
        }
        return false
    }
}