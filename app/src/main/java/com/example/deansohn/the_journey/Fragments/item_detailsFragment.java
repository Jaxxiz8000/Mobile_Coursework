package com.example.deansohn.the_journey.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deansohn.the_journey.DB.holiday_data_AO;
import com.example.deansohn.the_journey.HolidayContent;
import com.example.deansohn.the_journey.Model.Holiday;
import com.example.deansohn.the_journey.R;
import com.example.deansohn.the_journey.dummy.DummyContent;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link item_detailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link item_detailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class item_detailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Item";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change type parameters
    //private DummyContent.DummyItem item;
    private HolidayContent.HolidayItem item;
    private holiday_data_AO holidayDAO;

    // TODO: Rename and change types of parameters
    private String mParam1;
   // private String mParam2;

    private OnFragmentInteractionListener mListener;

    public item_detailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment item_detailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static item_detailsFragment newInstance(String param1, String param2) {
        item_detailsFragment fragment = new item_detailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        holidayDAO = new holiday_data_AO(getContext());
        if (getArguments() != null) {
            item = (HolidayContent.HolidayItem) getArguments().getSerializable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        TextView titleField = view.findViewById(R.id.titleView);
        titleField.setText(item.holiday_Name);
        TextView detailsField = view.findViewById(R.id.detailsView);
        detailsField.setText(item.holiday_Details);
        TextView holStartDate = view.findViewById(R.id.startDate);
        holStartDate.setText(item.holiday_start_date);
        TextView holEndDate = view.findViewById(R.id.endDate);
        holEndDate.setText(item.holiday_end_date);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        //String title = getActivity().getResources().getString(R.string.itemTitle);
        getActivity().setTitle(item.holiday_Name);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
