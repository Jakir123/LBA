package com.coderswise.lba.presenter;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.coderswise.lba.model.ModelAlarmList;
import com.coderswise.lba.model.SQL_Helper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class AlarmService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final String TAG = AlarmService.class.getSimpleName();
    private SharedPreferences preferences;
    private SQL_Helper sqlHelper;
    private ArrayList<ModelAlarmList> alarmLists;
    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    private LatLng latLng;
    private int prefsAlarmRange;
    private int prefsGpsUpdate;

    public AlarmService() {
    }

    private void initializations() {
        sqlHelper = new SQL_Helper(this);
        preferences = getSharedPreferences("LBA", MODE_PRIVATE);
        prefsGpsUpdate = preferences.getInt("gps", 2);
        alarmLists = new ArrayList<>();

        buildGoogleApiClient();
        googleApiClient.connect();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializations();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected synchronized void buildGoogleApiClient() {
        Log.d(TAG, "buildGoogleApiClient");
        googleApiClient = new GoogleApiClient.Builder(AlarmService.this)
                .addConnectionCallbacks(AlarmService.this)
                .addOnConnectionFailedListener(AlarmService.this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected");

        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000 * 60 * prefsGpsUpdate); // minute
        locationRequest.setFastestInterval(1000 * 60 * 1); // minute
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latLng = new LatLng(location.getLatitude(), location.getLongitude());

        prefsAlarmRange = preferences.getInt("range", 80);
        double currentLat = location.getLatitude();
        double currentLon = location.getLongitude();
        alarmLists = sqlHelper.getActiveAlarm();
        for (ModelAlarmList reminder : alarmLists) {
            int id = reminder.getAlarmId();
            double trgLat = Double.parseDouble(reminder.getLatitude());
            double trgLon = Double.parseDouble(reminder.getLongitude());
            String titleString = reminder.getLocationTitle();
            String notificationString = reminder.getAlarmNotification();

            long dis = (long) AlarmService.distFrom(trgLat,
                    trgLon, currentLat, currentLon);
            if (dis <= prefsAlarmRange) {

                Intent receiver = new Intent(AlarmService.this, ReminderReceiver.class);
                receiver.putExtra("id", id);
                receiver.putExtra("title", titleString);
                receiver.putExtra("notification", notificationString);
                sendBroadcast(receiver);

            }
        }
    }

    private static double distFrom(double lat1, double lng1, double lat2,
                                   double lng2) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
                * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        int meterConversion = 1609;

        return (dist * meterConversion);
    }
}
