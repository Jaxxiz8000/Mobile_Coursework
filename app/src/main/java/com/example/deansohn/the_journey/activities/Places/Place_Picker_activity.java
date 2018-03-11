package com.example.deansohn.the_journey.activities.Places;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deansohn.the_journey.DB.DBHelper;
import com.example.deansohn.the_journey.DB.Places_data_AO;
import com.example.deansohn.the_journey.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Random;
import java.util.concurrent.TimeoutException;

public class Place_Picker_activity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;
    private TextView tvPlaceDetails;
    private TextView placeName;
    private TextView placeAddress;
//    private TextView placeLat;
//    private TextView placeLong;
    private EditText holiday_name;
    private EditText placeHolidayAssociation;
    private FloatingActionButton fabPickPlace;
    private FloatingActionButton addPlaceBtn;

    private DBHelper mHelper;
    private Places_data_AO places_data_ao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place__picker_activity);

        mHelper = new DBHelper(this);

        initViews();

        places_data_ao = new Places_data_AO(this);
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        fabPickPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(Place_Picker_activity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
            }
        });

        addPlaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeName = findViewById(R.id.placeName);
                String places_Name = placeName.getText().toString();

                placeAddress = findViewById(R.id.placeAddress);
                String places_address = placeAddress.getText().toString();

                holiday_name = findViewById(R.id.placeHolidayAssociation);
                String place_holiday_association = holiday_name.getText().toString();

                String create_key = createRandomKey();
                places_data_ao.addPlaces(places_Name, places_address);
                //places_data_ao.addAssociation(create_key, place_holiday_association);

                placeName.setText("");
                placeAddress.setText("");
                // holiday_name.setText("");

            }
        });


    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fabPickPlace = (FloatingActionButton) findViewById(R.id.fab);
        addPlaceBtn = (FloatingActionButton) findViewById(R.id.placeAddBtn);
        tvPlaceDetails = (TextView) findViewById(R.id.placeDetails);
        placeName = findViewById(R.id.placeName);
        placeAddress = findViewById(R.id.placeAddress);
        placeHolidayAssociation = findViewById(R.id.placeHolidayAssociation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Snackbar.make(fabPickPlace, connectionResult.getErrorMessage() + "", Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
//              StringBuilder stBuilder = new StringBuilder();

                placeName.setText(String.format("%s", place.getName()));
                placeAddress.setText(String.format("%s", place.getAddress()));

//                String placename = String.format("%s", place.getName());
//                String latitude = String.valueOf(place.getLatLng().latitude);
//                String longitude = String.valueOf(place.getLatLng().longitude);
//                String address = String.format("%s", place.getAddress());
//                stBuilder.append("Name: ");
//                stBuilder.append(placename);
//                stBuilder.append("\n");
//                stBuilder.append("Latitude: ");
//                stBuilder.append(latitude);
//                stBuilder.append("\n");
//                stBuilder.append("Logitude: ");
//                stBuilder.append(longitude);
//                stBuilder.append("\n");
//                stBuilder.append("Address: ");
//                stBuilder.append(address);
//                tvPlaceDetails.setText(stBuilder.toString());
            }
        }
    }

    protected String createRandomKey() {
        String val = "";

        for (int i = 0; i < 6; ) {
            int ranAny = 48 + (new Random()).nextInt(90 - 65);

            if (!(57 < ranAny && ranAny <= 65)) {
                char c = (char) ranAny;
                val += c;
                i++;
            }
        }
        return val;
    }
}

