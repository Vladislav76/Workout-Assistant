package com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.content

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.presentation.view.components.VMToolbarFragment
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.viewmodel.DiaryEntryListVM
import javax.inject.Inject

class DiaryEntryListToolbarContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<List<ShortDiaryEntry>>() {

    override val viewModel by lazy { injectViewModel<DiaryEntryListVM>(viewModelFactory) }

    private val onActionClickCallback = Toolbar.OnMenuItemClickListener { item: MenuItem ->
        when (item.itemId) {
            R.id.filter_action -> {
                // TODO: show filter dialog
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.diary_entry_list_title)
        addMenu(R.menu.menu_diary_entry_list)
        addMenuListener(onActionClickCallback)
    }
}