package com.example.deansohn.the_journey;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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
import com.example.deansohn.the_journey.Model.Holiday;

import java.io.Serializable;

public class Main_Activity_Page extends AppCompatActivity
        implements itemListFragment.OnListFragmentInteractionListener,
        item_detailsFragment.OnFragmentInteractionListener {

    private Button launchAddholiday;
    private DBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main___page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHelper = new DBHelper(this);

        launchAddholiday = findViewById(R.id.add_holiday_btn_text);

        launchAddholiday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                launchActivityAddHoliday();
            }

        });

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //Create new Fragment to be placed in activity layout
        itemListFragment firstFragment = new itemListFragment();

        // In case the activity was started with special instructions
        // Intent, pass the intents extras to the fragment arguments
        firstFragment.setArguments(getIntent().getExtras());

        // Add fragment to the fragment container frame layout
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
    }

    private void launchActivityAddHoliday() {
        Intent intent = new Intent(this, AddHolidayForm.class);
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
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(Holiday item) {
        Toast.makeText(this, "You clicked " + item.toString(),
                Toast.LENGTH_LONG).show();

        // create new fragment,
        item_detailsFragment newFragment = new item_detailsFragment();

        // add argument specifying the item it should show
        Bundle args = new Bundle();
        args.putSerializable("Item", (Serializable) item);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in fragment container view with this fragment,
        // and add transaction to the back stack so the user can navigate back

        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // commit transaction
        transaction.commit();
    }
}
