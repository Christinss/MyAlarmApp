package com.example.tina.myalarmapp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;
import java.util.Random;

public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        String state = intent.getExtras().getString("extra");

        Integer alarm_sound_choice = intent.getExtras().getInt("alarm_choice");

        NotificationManager notify_manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent_main_activity = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this,
                0, intent_main_activity, 0);


        Notification notification_popup = new Notification.Builder(this)
                .setContentTitle("An alarm is going off!")
                .setContentText("Click me!")
                .setSmallIcon(R.drawable.ic_action_alarm)
                .setContentIntent(pending_intent_main_activity)
                .setAutoCancel(true)
                .build();


        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }


        if (!this.isRunning && startId == 1) {

            this.isRunning = true;
            this.startId = 0;

           notify_manager.notify(0, notification_popup);

            if (alarm_sound_choice == 0) {

                int minimum_number = 1;
                int maximum_number = 13;

                Random random_number = new Random();
                int alarm_number = random_number.nextInt(maximum_number + minimum_number);
                if (alarm_number == 1) {
                    media_song = MediaPlayer.create(this, R.raw.analog_watch_alarm);
                    media_song.start();
                } else if (alarm_number == 2) {
                    media_song = MediaPlayer.create(this, R.raw.bells_tibetan);
                    media_song.start();
                } else if (alarm_number == 3) {
                    media_song = MediaPlayer.create(this, R.raw.whistling_at_girl);
                    media_song.start();
                } else if (alarm_number == 4) {
                    media_song = MediaPlayer.create(this, R.raw.clock_chimes);
                    media_song.start();
                } else if (alarm_number == 5) {
                    media_song = MediaPlayer.create(this, R.raw.glass_ping);
                    media_song.start();
                } else if (alarm_number == 6) {
                    media_song = MediaPlayer.create(this, R.raw.loud_alarm_clock_buzzer);
                    media_song.start();
                } else if (alarm_number == 7) {
                    media_song = MediaPlayer.create(this, R.raw.massive_war_with_alarm);
                    media_song.start();
                } else if (alarm_number == 8) {
                    media_song = MediaPlayer.create(this, R.raw.metal_gong);
                    media_song.start();
                } else if (alarm_number == 9) {
                    media_song = MediaPlayer.create(this, R.raw.old_fashioned_doorbell);
                    media_song.start();
                } else if (alarm_number == 10) {
                    media_song = MediaPlayer.create(this, R.raw.old_fashioned_schoolbell);
                    media_song.start();
                } else if (alarm_number == 11) {
                    media_song = MediaPlayer.create(this, R.raw.service_bell);
                    media_song.start();
                } else if (alarm_number == 12) {
                    media_song = MediaPlayer.create(this, R.raw.sos_morsecode);
                    media_song.start();
                } else if (alarm_number == 13) {
                    media_song = MediaPlayer.create(this, R.raw.tolling_bell);
                    media_song.start();
                } else {
                    media_song = MediaPlayer.create(this, R.raw.analog_watch_alarm);
                    media_song.start();
                }

            } else if (alarm_sound_choice == 1) {
                media_song = MediaPlayer.create(this, R.raw.analog_watch_alarm);
                media_song.start();
            } else if (alarm_sound_choice == 2) {
                media_song = MediaPlayer.create(this, R.raw.bells_tibetan);
                media_song.start();
            } else if (alarm_sound_choice == 3) {
                media_song = MediaPlayer.create(this, R.raw.whistling_at_girl);
                media_song.start();
            } else if (alarm_sound_choice == 4) {
                media_song = MediaPlayer.create(this, R.raw.clock_chimes);
                media_song.start();
            } else if (alarm_sound_choice == 5) {
                media_song = MediaPlayer.create(this, R.raw.glass_ping);
                media_song.start();
            } else if (alarm_sound_choice == 6) {
                media_song = MediaPlayer.create(this, R.raw.loud_alarm_clock_buzzer);
                media_song.start();
            } else if (alarm_sound_choice == 7) {
                media_song = MediaPlayer.create(this, R.raw.massive_war_with_alarm);
                media_song.start();
            } else if (alarm_sound_choice == 8) {
                media_song = MediaPlayer.create(this, R.raw.metal_gong);
                media_song.start();
            } else if (alarm_sound_choice == 9) {
                media_song = MediaPlayer.create(this, R.raw.old_fashioned_doorbell);
                media_song.start();
            } else if (alarm_sound_choice == 10) {
                media_song = MediaPlayer.create(this, R.raw.old_fashioned_schoolbell);
                media_song.start();
            } else if (alarm_sound_choice == 11) {
                media_song = MediaPlayer.create(this, R.raw.service_bell);
                media_song.start();
            } else if (alarm_sound_choice == 12) {
                media_song = MediaPlayer.create(this, R.raw.sos_morsecode);
                media_song.start();
            } else if (alarm_sound_choice == 13) {
                media_song = MediaPlayer.create(this, R.raw.tolling_bell);
                media_song.start();
            } else {
                media_song = MediaPlayer.create(this, R.raw.analog_watch_alarm);
                media_song.start();
            }
        }


        else if (this.isRunning && startId == 0) {
            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            this.startId = 0;
        }

        else if (!this.isRunning && startId == 0) {

            this.isRunning = false;
            this.startId = 0;
        }
        else if (this.isRunning && startId == 1) {

            this.isRunning = true;
            this.startId = 1;
        }
        else {
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.isRunning = false;
    }

}
