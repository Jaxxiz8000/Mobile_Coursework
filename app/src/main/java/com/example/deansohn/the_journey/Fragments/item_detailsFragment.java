package com.example.deansohn.the_journey.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deansohn.the_journey.DB.holiday_data_AO;
import com.example.deansohn.the_journey.Model.Home_holiday.Holiday;
import com.example.deansohn.the_journey.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link item_detailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link item_detailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class item_detailsFragment extends Fragment implements DatePickerFragment.datePickerListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Item";


    // TODO: Rename and change type parameters
    //private DummyContent.DummyItem item;
    private Holiday item;
    private holiday_data_AO holidayDAO;
    private Holiday editedHoliday;

    private EditText holidayNameEditText;
    private EditText holidayDetailsEditText;


    TextView detailsField;
    TextView titleField;
    Button holStartDate;
    Button holEndDate;
    Boolean dateStartText;
    Boolean dateEndText;

    item_detailsFragment thisFragm = this;

    private OnFragmentInteractionListener mListener;

    public item_detailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        holidayDAO = new holiday_data_AO(getContext());
        if (getArguments() != null) {
            item = (Holiday) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        View view = inflater.inflate(R.layout.fragment_edit_item_details, container, false);

        titleField = view.findViewById(R.id.titleView);
        titleField.setText(item.getHoliday_name());

        detailsField = view.findViewById(R.id.detailsView);
        detailsField.setText(item.getHoliday_description());

        TextView startDateTextMessage = view.findViewById(R.id.startDateText);
        startDateTextMessage.setText(R.string.startDateText);
        holStartDate = view.findViewById(R.id.startDate);
        holStartDate.setText(item.getHolStartDate());

        TextView endDateTextMessage = view.findViewById(R.id.endDateText);
        endDateTextMessage.setText(R.string.endDateText);
        holEndDate = view.findViewById(R.id.endDate);
        holEndDate.setText(item.getHolEndDate());

        final int id = item.getId();
        Log.d("Holiday ID: ", " " + id);

        Button deleteButton = view.findViewById(R.id.delete_holiday_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, " " + id , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                holidayDAO.deleteHolidayByID(id);

            }
        });

        Button update_holiday_info =  view.findViewById(R.id.update_holiday_button);
        update_holiday_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Holiday Updated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                final String newholidayName;
                newholidayName = titleField.getText().toString();

                final String holidayDetails;
                holidayDetails = detailsField.getText().toString();

                final String holidayStartDate;
                holidayStartDate = holStartDate.getText().toString();

                final String holidayEndDate;
                holidayEndDate = holEndDate.getText().toString();

                final int id = item.getId();

                //Log.d("Clicked item ID: ", " " + id);
                Log.d("Clicked item Name: ", " " + newholidayName);
                Log.d("Clicked item desc: ", " " + holidayDetails);
                Log.d("Clicked item stDate: ", " " + holidayStartDate);
                Log.d("Clicked item enDate: ", " " + holidayEndDate);

                holidayDAO.updateHoliday(setHoliday(newholidayName,
                        holidayDetails, holidayStartDate, holidayEndDate), id);
            }
        });

        holStartDate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                FragmentManager fm = getChildFragmentManager();
                DialogFragment picker = new DatePickerFragment();
                picker.setTargetFragment(thisFragm, 0);
                picker.show(fm, "datePicker");
                dateStartText = true;
                dateEndText = false;

            }
        });

//        holEndDate.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                //FragmentManager fm = getChildFragmentManager();
//                DialogFragment picker = new DatePickerFragment();
//                picker.show(getActivity().getSupportFragmentManager(), "datePicker");
//                dateStartText = false;
//                dateEndText = true;
//
//            }
//        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(item);
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
        getActivity().setTitle(item.getHoliday_name());
    }

    @Override
    public void returnDate(String date) {
        if (dateStartText)
            holStartDate.setText(date);
        else if (dateEndText)
            holEndDate.setText(date);
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
        void onFragmentInteraction(Holiday item);
    }

    private Holiday setHoliday(String holName, String description, String startDate, String endDate) {
        Holiday newHoliday = new Holiday();
        newHoliday.setHoliday_name(holName);
        newHoliday.setHoliday_description(description);
        newHoliday.setHolStartDate(startDate);
        newHoliday.setHolEndDate(endDate);

        return newHoliday;
    }
}
