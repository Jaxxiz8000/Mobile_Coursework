package com.example.deansohn.the_journey.activities.Home_holiday;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.deansohn.the_journey.DB.holiday_data_AO;
import com.example.deansohn.the_journey.Fragments.DatePickerFragment;
import com.example.deansohn.the_journey.Fragments.PlacesListFragment;
import com.example.deansohn.the_journey.Fragments.dummy.DummyContent;
import com.example.deansohn.the_journey.Model.Home_holiday.Holiday;
import com.example.deansohn.the_journey.R;

public class Holiday_Edit_details_activity extends AppCompatActivity implements DatePickerFragment.datePickerListener, PlacesListFragment.OnListFragmentInteractionListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Item";

    private Holiday item;
    private holiday_data_AO holidayDAO;
    TextView detailsField;
    TextView titleField;
    Button holStartDate;
    Button holEndDate;
    Boolean dateStartText;
    Boolean dateEndText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday__edit_details_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        holidayDAO = new holiday_data_AO(this);
        Intent b = getIntent();
        if (b != null) {
            item = (Holiday)b.getSerializableExtra(ARG_PARAM1);
        }


        titleField = findViewById(R.id.titleView);
        titleField.setText(item.getHoliday_name());

        detailsField = findViewById(R.id.detailsView);
        detailsField.setText(item.getHoliday_description());

        TextView startDateTextMessage = findViewById(R.id.startDateText);
        startDateTextMessage.setText(R.string.startDateText);
        holStartDate = findViewById(R.id.startDate);
        holStartDate.setText(item.getHolStartDate());

        TextView endDateTextMessage = findViewById(R.id.endDateText);
        endDateTextMessage.setText(R.string.endDateText);
        holEndDate = findViewById(R.id.endDate);
        holEndDate.setText(item.getHolEndDate());

        final int id = item.getId();
        Log.d("Holiday ID: ", " " + id);

        Button deleteButton = findViewById(R.id.delete_holiday_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, " " + id, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                holidayDAO.deleteHolidayByID(id);
                goToListPage();
                finish();

            }
        });

        Button update_holiday_info =  findViewById(R.id.update_holiday_button);
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
                DialogFragment picker = new DatePickerFragment();
                picker.show(getSupportFragmentManager(), "datePicker");
                dateStartText = true;
                dateEndText = false;

            }
        });

        holEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DialogFragment picker = new DatePickerFragment();
                picker.show(getSupportFragmentManager(), "datePicker");
                dateStartText = false;
                dateEndText = true;

            }
        });

        // Create a new Fragment to be placed in the activity layout
        PlacesListFragment firstFragment = new PlacesListFragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        firstFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();
    }

    private Holiday setHoliday(String holName, String description, String startDate, String endDate) {
        Holiday newHoliday = new Holiday();
        newHoliday.setHoliday_name(holName);
        newHoliday.setHoliday_description(description);
        newHoliday.setHolStartDate(startDate);
        newHoliday.setHolEndDate(endDate);

        return newHoliday;
    }

    private void goToListPage() {

        Intent intent = new Intent(this, Main_Activity_Page.class);
        startActivity(intent);

    }

    @Override
    public void returnDate(String date) {
        if (dateStartText)
            holStartDate.setText(date);
        else if (dateEndText)
            holEndDate.setText(date);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
