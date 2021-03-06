package com.example.deansohn.the_journey.activities.Home_holiday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.deansohn.the_journey.DB.DBHelper;
import com.example.deansohn.the_journey.Fragments.itemListFragment;
import com.example.deansohn.the_journey.Fragments.item_detailsFragment;
import com.example.deansohn.the_journey.Model.Home_holiday.Holiday;
import com.example.deansohn.the_journey.R;
import com.example.deansohn.the_journey.activities.Gallery.Photo_gallery_activity;
import com.example.deansohn.the_journey.activities.Places.Place_Picker_activity;
import com.example.deansohn.the_journey.activities.Places.Places_list_activity;

public class Main_Activity_Page extends AppCompatActivity
        implements itemListFragment.OnListFragmentInteractionListener,
        item_detailsFragment.OnFragmentInteractionListener {

    private DBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new DBHelper(this);
        createAndRestartCode();

    }

    private void launchActivityAddHoliday() {
        Intent intent = new Intent(this, AddHolidayForm.class);
        startActivity(intent);
    }

    private void launchActivityPlacePicker() {
        Intent intent = new Intent(this, Place_Picker_activity.class);
        startActivity(intent);
    }

    private void launchActivityPlaceList() {
        Intent intent = new Intent(this, Places_list_activity.class);
        startActivity(intent);
    }

    private void launchGalleryActivity() {
        Intent intent = new Intent(this, Photo_gallery_activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main__activity__page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onListFragmentInteraction(Holiday item) {
        Toast.makeText(this, "You clicked " + item.getHoliday_name(),
                Toast.LENGTH_LONG).show();
        String id = String.valueOf(item.getId());
        String name = item.getHoliday_name();
        String desc = item.getHoliday_description();
        String startDate = item.getHolStartDate();
        String endDate = item.getHolEndDate();
        // create new fragment,
        //item_detailsFragment newFragment = new item_detailsFragment();
        Intent detailsIntent = new Intent(this, Holiday_Edit_details_activity.class);

        // add argument specifying the item it should show
        detailsIntent.putExtra("Item", item);
        startActivity(detailsIntent);
    }

    @Override
    public void onFragmentInteraction(Holiday item) {

    }

    public void onRestart() {
        super.onRestart();
        createAndRestartCode();
    }

    public void createAndRestartCode() {
        setContentView(R.layout.activity_main___page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button launchAddholiday = findViewById(R.id.add_holiday_btn_text);

        final Button launchPlacePicker = findViewById(R.id.place_Picker_btn);
        final Button launchPlaceList = findViewById(R.id.place_list_launch_btn);
        final Button launchGallery = findViewById(R.id.gallery_launch_btn);

        launchPlacePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                launchActivityPlacePicker();
            }
        });

        launchAddholiday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                launchActivityAddHoliday();
            }

        });

        launchPlaceList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                launchActivityPlaceList();
            }

        });

        launchGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                launchGalleryActivity();
            }
        });

        //Create new Fragment to be placed in activity layout
        itemListFragment firstFragment = new itemListFragment();

        // In case the activity was started with special instructions
        // Intent, pass the intents extras to the fragment arguments
        firstFragment.setArguments(getIntent().getExtras());

        // Add fragment to the fragment container frame layout
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
    }
}
