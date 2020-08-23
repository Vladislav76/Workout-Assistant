package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import javax.inject.Inject

class ExerciseImagePagerAdapter @Inject constructor(
        private val context: Context
) : PagerAdapter() {

    var imagesIDs: List<String> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val resName = imagesIDs[position]
        val resID = context.resources.getIdentifier(resName, "drawable", context.packageName)
        val image = ContextCompat.getDrawable(context, resID)

        val imageView = ImageView(context).apply { setImageDrawable(image) }
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun getCount(): Int = imagesIDs.size

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getPageTitle(position: Int): CharSequence? {
        return (position + 1).toString()
    }
}