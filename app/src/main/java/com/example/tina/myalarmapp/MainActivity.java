package com.example.tina.myalarmapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    TimePicker alarm_timePicker;
    Context context;
    PendingIntent pending_intent;
    int choose_alarm_sound;
    AlarmManager alarm_manager;
    Button alarm_off;
    int hour, minute;
    String hour_string, minute_string;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;



        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm_timePicker = findViewById(R.id.timePicker);


        final Calendar calendar = Calendar.getInstance();

        final Intent my_intent = new Intent(this, Alarm_Receiver.class);


        Spinner spinner = findViewById(R.id.choice_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alarm_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        Button alarm_on = findViewById(R.id.bAlarmOn);

        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alarm_off.setVisibility(View.VISIBLE);


                calendar.set(Calendar.HOUR_OF_DAY, alarm_timePicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timePicker.getMinute());
                calendar.set(Calendar.SECOND, 0);


                hour = alarm_timePicker.getHour();
                minute = alarm_timePicker.getMinute();


                hour_string = String.valueOf(hour);
                minute_string = String.valueOf(minute);


                //convert 24-hour time to 12-hour time
                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12);
                }

                if (minute < 10) {
                    minute_string = "0" + String.valueOf(minute);
                }


                my_intent.putExtra("extra", "alarm on");
                my_intent.putExtra("alarm_choice", choose_alarm_sound);


                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0,
                        my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                // comparing required time to actual time value, if less than zero (past),
                // adding one day to it
                long timeInMillis = calendar.getTimeInMillis();
                if (timeInMillis - System.currentTimeMillis() < 0) {
                    timeInMillis += 86400000;
                    alarm_manager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pending_intent);

                } else //if (timeInMillis - System.currentTimeMillis() >= 0) {
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
                //}


                Toast.makeText(context, "Alarm is on", Toast.LENGTH_SHORT).show();
            }
        });

        alarm_off = findViewById(R.id.bAlarmOff);

        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alarm_manager.cancel(pending_intent);


                my_intent.putExtra("extra", "alarm off");
                my_intent.putExtra("alarm_choice", choose_alarm_sound);
                sendBroadcast(my_intent);

                Toast.makeText(context, "Alarm is off", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_AmPm) {
            if (item.isChecked()) item.setChecked(false);
            else item.setChecked(true);
            alarm_timePicker.setIs24HourView(false);

            Toast.makeText(context, "AM/PM", Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.action_24h) {
            if (item.isChecked()) item.setChecked(false);
            else item.setChecked(true);
            alarm_timePicker.setIs24HourView(true);

            Toast.makeText(context, "24 hours", Toast.LENGTH_SHORT).show();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choose_alarm_sound = (int) id;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
