package com.vladislav.workoutassistant.ui.workouts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback
import com.vladislav.workoutassistant.data.models.WorkoutGroup
import androidx.recyclerview.widget.RecyclerView

class WorkoutGroupAdapter(private val mWorkoutGroupCallback: OnItemClickCallback, private val mWorkoutCallback: OnItemClickCallback) : RecyclerView.Adapter<WorkoutGroupAdapter.WorkoutsCardViewHolder>() {

    private var mWorkoutGroups: List<WorkoutGroup>? = null
    private val mViewPool: RecyclerView.RecycledViewPool //for the same view types

    init {
        mViewPool = RecyclerView.RecycledViewPool()
    }

    fun setList(groups: List<WorkoutGroup>) {
        mWorkoutGroups = groups   //TODO: add DiffUtil
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_workouts, parent, false)
        return WorkoutsCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutsCardViewHolder, position: Int) {
        holder.bind(mWorkoutGroups!![position])
    }

    override fun getItemCount(): Int {
        return if (mWorkoutGroups == null) 0 else mWorkoutGroups!!.size
    }

    //    @Override
    //    public void onViewAttachedToWindow(@NonNull WorkoutsCardViewHolder holder) {
    //        holder.restoreRecyclerViewPosition();
    //        System.out.println("Attached");
    //        super.onViewAttachedToWindow(holder);
    //        System.out.println(holder.getItemId());
    //    }
    //
    //    @Override
    //    public void onViewDetachedFromWindow(@NonNull WorkoutsCardViewHolder holder) {
    //        holder.saveRecyclerViewPosition();
    //        System.out.println("Detached");
    //        super.onViewDetachedFromWindow(holder);
    //    }

    //    @Override
    //    public void onViewRecycled(@NonNull WorkoutsCardViewHolder holder) {
    //        System.out.println(holder.getAdapterPosition());
    //    }

    internal inner class WorkoutsCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mTitleView: TextView
        private val mRecyclerView: RecyclerView
        private val mAdapter: WorkoutAdapter
        private var mWorkoutGroup: WorkoutGroup? = null

        init {
            mTitleView = view.findViewById(R.id.card_title)
            mRecyclerView = view.findViewById(R.id.recycler_view)
            mRecyclerView.setRecycledViewPool(mViewPool)
            mAdapter = WorkoutAdapter(mWorkoutCallback)
            mRecyclerView.adapter = mAdapter
            view.findViewById<View>(R.id.card_header).setOnClickListener { mWorkoutGroupCallback.onClick(mWorkoutGroup!!.groupId, mWorkoutGroup!!.name) }
        }

        fun bind(group: WorkoutGroup) {
            mWorkoutGroup = group
            mTitleView.text = group.name
            mAdapter.setList(group.workouts)
            mAdapter.notifyDataSetChanged()
            mRecyclerView.scrollToPosition(0)
        }

        //        void saveRecyclerViewPosition() {
        //            if (mCardsInfo.contains(mCardInfo)) {
        //                System.out.println("State saved");
        ////                WorkoutsCardInfo cardInfo = getInfo(mCard);
        //                mCardInfo.setState(mRecyclerView.getLayoutManager().onSaveInstanceState());
        //            }
        //        }
        //
        //        void restoreRecyclerViewPosition() {
        //            if (mCardsInfo.contains(mCardInfo)) {
        ////                WorkoutsCardInfo cardInfo = getInfo(mCard);
        //                mRecyclerView.getLayoutManager().onRestoreInstanceState(mCardInfo.getState());
        //            }
        //        }
    }

    //    class WorkoutsCardInfo {
    //        private WorkoutGroupListViewModel mCard;
    //        private Parcelable mState;
    //        private boolean mIsValid;
    //
    //        WorkoutsCardInfo(WorkoutGroupListViewModel card) {
    //            mCard = card;
    //        }
    //
    //        WorkoutGroupListViewModel getCard() {
    //            return mCard;
    //        }
    //
    //        Parcelable getState() {
    //            return mState;
    //        }
    //
    //        boolean isValid() {
    //            return mIsValid;
    //        }
    //
    //        void setCard(WorkoutGroupListViewModel card) {
    //            mCard = card;
    //        }
    //
    //        void setState(Parcelable state) {
    //            mState = state;
    //        }
    //
    //        void setValid(boolean isValid) {
    //            mIsValid = isValid;
    //        }
    //    }
}