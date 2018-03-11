package com.example.deansohn.the_journey.Fragments;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deansohn.the_journey.Fragments.itemListFragment.OnListFragmentInteractionListener;
import com.example.deansohn.the_journey.Model.Home_holiday.Holiday;
import com.example.deansohn.the_journey.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<Holiday> mValues;
    private OnListFragmentInteractionListener mListener;

    MyItemRecyclerViewAdapter(List<Holiday> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    void replaceList(List<Holiday> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String id = String.valueOf(mValues.get(position).getId());
        String desc = mValues.get(position).getHoliday_description();
        String startDate = mValues.get(position).getHolStartDate();
        String endDate = mValues.get(position).getHolEndDate();

        holder.mIdView.setText(id);
        holder.mContentView.setText(mValues.get(position).getHoliday_name());
        Log.d("Value of mIdView: ", " " + id);

        Log.d("item inBindV desc: ", " " + desc);
        Log.d("item inBindV stDate: ", " " + startDate);
        Log.d("item inBinV enDate: ", " " + endDate);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    Holiday item = holder.mItem;

                    mListener.onListFragmentInteraction(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        Holiday mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
