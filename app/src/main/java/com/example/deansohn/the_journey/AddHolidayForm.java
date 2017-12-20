package com.example.deansohn.the_journey;

import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deansohn.the_journey.DB.holiday_data_AO;
import com.example.deansohn.the_journey.Fragments.DatePickerFragment;
import com.example.deansohn.the_journey.Model.Holiday;

import java.util.Date;

public class AddHolidayForm extends AppCompatActivity implements DatePickerFragment.datePickerListener {

    Button goToListActivity;
    TextView textViewDateStart;
    TextView textViewDateEnd;
    Boolean dateStartText;
    Boolean dateEndText;

    String holiday_name;
    String holiday_details;
    String hol_start_date;
    String hol_end_date;
    Holiday holiday = null;
    private holiday_data_AO holidayDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_holiday_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        holidayDAO = new holiday_data_AO(this);

        goToListActivity = findViewById(R.id.ListPageBtn);

        // Pick date variables.
        final Button pickDateStart = findViewById(R.id.pick_date_start);
        textViewDateStart = findViewById(R.id.pick_date_start);

        final Button pickDateEnd = findViewById(R.id.pick_date_end);
        textViewDateEnd = findViewById(R.id.pick_date_end);

        goToListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToListPage();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                final EditText holidayName = findViewById(R.id.holidayNameInput);
                holiday_name = holidayName.getText().toString();

                final EditText holidayDetails = findViewById(R.id.holidayDetailsInput);
                holiday_details = holidayDetails.getText().toString();

                final Button holidayStartDate = findViewById(R.id.pick_date_start);
                hol_start_date = holidayStartDate.getText().toString();

                final Button holidayEndDate = findViewById(R.id.pick_date_end);
                hol_end_date = holidayEndDate.getText().toString();

                holidayDAO.addHoliday(setHoliday(holiday_name, holiday_details, hol_start_date, hol_end_date));
                //HolidayContent.addHolidayItem(holiday_name, holiday_details, hol_start_date, hol_end_date);

                holidayName.setText("");
                holidayDetails.setText("");

            }
        });

        pickDateStart.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                DialogFragment picker = new DatePickerFragment();
                picker.show(getSupportFragmentManager(), "datePicker");
                dateStartText = true;
                dateEndText = false;

            }
        });

        pickDateEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                DialogFragment picker = new DatePickerFragment();
                picker.show(getSupportFragmentManager(), "datePicker");
                dateStartText = false;
                dateEndText = true;

            }
        });

    }

    private void goToListPage() {

        Intent intent = new Intent(this, Main_Activity_Page.class);
        startActivity(intent);

    }

    private Holiday setHoliday(String holName, String startDate, String endDate, String description) {
        Holiday newHoliday = new Holiday();
        newHoliday.setHoliday_name(holName);
        newHoliday.setHolStartDate(startDate);
        newHoliday.setHolEndDate(endDate);
        newHoliday.setHoliday_description(description);

        return newHoliday;
    }

    @Override
    public void returnDate(String date) {
        if (dateStartText)
            textViewDateStart.setText(date);
        else if (dateEndText)
            textViewDateEnd.setText(date);
    }
}
