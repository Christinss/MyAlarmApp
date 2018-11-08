package com.example.tina.myalarmapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Alarm_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        String get_your_string = intent.getExtras().getString("extra");

        Integer get_your_alarm_choice = intent.getExtras().getInt("alarm_choice");

        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        service_intent.putExtra("extra", get_your_string);

        service_intent.putExtra("alarm_choice", get_your_alarm_choice);

        context.startService(service_intent);

    }
}
