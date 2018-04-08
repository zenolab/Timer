package com.zenolab.nav.grd.mp3simple.pickertimer;

import android.app.FragmentTransaction;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import java.math.BigDecimal;


import static com.zenolab.nav.grd.mp3simple.pickertimer.App.value09;
import static com.zenolab.nav.grd.mp3simple.pickertimer.App.valueHour;
import static com.zenolab.nav.grd.mp3simple.pickertimer.App.valueMin;
import static com.zenolab.nav.grd.mp3simple.pickertimer.App.valueSec;
import static com.zenolab.nav.grd.mp3simple.pickertimer.App.displayTimer;//transfer to fragment



//condition - состояниие
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";

    private static final int NOTIFY_ID = 101;
    private static final String CHANNEL_ID = "my_channel_01";

    private TextView textView;
    private TextView textViewBottom;

    CountDownTimer timer;
    Context context = this;
    MediaPlayer mp;

    TimerFragment myFragment;

    private boolean flipOn = false;

    //Declare a variable to hold count down timer's paused status
    private  boolean isPaused = false;
    //Declare a variable to hold count down timer's paused status
    private boolean isCanceled = false;
    //Declare a variable to hold CountDownTimer remaining time
    private long timeRemaining = 0;

    long countDownInterval = 1000; //1 second
    long millisInFuture;


    long[] pattern = { 500, 300, 400, 200,500, 300, 400, 200,500, 300, 400, 200,500, 300, 400, 200 };


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {






        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


             isPaused = false;
             isCanceled = false;

            switch (item.getItemId()) {
                case R.id.navigation_pause:

                    textViewBottom.setText(R.string.title_pause);

                    isPaused = true;
                    Log.i(TAG, "pause ----------isPAUSE = "+isPaused);
                    return true;

                case R.id.navigation_invalidate:

                    isCanceled = true;
                    value09 = 0;
                    valueSec = 0;
                    valueMin = 0;
                    valueHour =0;
                    timeRemaining=0;
                    textView.setText("Timer Canceled.");
                    textViewBottom.setText("Countdown");
                    textViewBottom.setTextColor(Color.parseColor("#FFFFFF"));
                    timer=null;
                    stopSound();
                    flipCardSetTime();

                    return true;
                case R.id.navigation_start_resume:
                    textViewBottom.setText(R.string.title_resumed);
                    textViewBottom.setText("Countdown");

                    displayTimer.setTextColor(Color.parseColor("#8b8b8b"));


                    if(timeRemaining==0 ){

                        millisInFuture  = getTimePickerValue();
                        textView.setText("Set Timer "+remainTimeFormat(millisInFuture/1000));
                                flipOn=true;

                    }else{
                         millisInFuture = timeRemaining;
                        flipOn=false;

                    }

                    timer = new CountDownTimer(millisInFuture, countDownInterval) {
                            public void onTick(long millisUntilFinished) {

                                if (isPaused || isCanceled) {
                                    Log.i(TAG, "START ----------isPAUSE = " + isPaused);
                                    cancel();
                                    stopSound();
                                } else {
                                    updateViewFrag(millisUntilFinished/countDownInterval);
                                    //Put count down timer remaining time in a variable
                                    timeRemaining = millisUntilFinished;
                                    mySetColor();
                                    Log.i(TAG, " -REMAIN--timeRemaining ----------" + timeRemaining);
                                }
                            }

                            public void onFinish() {
                                done();
                                showNotif();
                                timeRemaining=0;
                                textView.setText("Set Timer "+remainTimeFormat(getTimePickerValue()/countDownInterval));
                            }
                        }.start();

                        flipCardDisplayTime();

                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        textView = (TextView) findViewById(R.id.textViewHeader);
        displayTimer = (TextView) findViewById(R.id.countDown_timer_bottom);
        textViewBottom = (TextView) findViewById(R.id.countDown_timer_bottom);


        displayTimer.setTextColor(Color.parseColor("#FFFFFF"));
        textViewBottom.setTextColor(Color.parseColor("#FFFFFF"));


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        addFragment(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.frgmCont, new PickerFragment())
                    .commit();
        }

        //-------------------not work in android 8 api26 needns implements Notification Cannel-----------------------
        /*
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = this.getResources();

        // до версии Android 8.0 API 26
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentIntent(contentIntent)
                // обязательные настройки
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle("Напоминание")
                //.setContentText(res.getString(R.string.notifytext))
                .setContentText("Пора покормить кота") // Текст уведомления
                // необязательные настройки
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_home_black_24dp)) // большая
                // картинка
                //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
                .setTicker("Последнее китайское предупреждение!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true); // автоматически закрыть уведомление после нажатия

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Альтернативный вариант
        // NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFY_ID, builder.build());
        */
//-----------------------------

//-----------------------------


    } // End-----  onCreate


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    private void flipCardDisplayTime() {
        Log.i(TAG, "flipCard()----------ShowTime = "+flipOn);

                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.animator.card_flip_right_enter,
                                R.animator.card_flip_right_exit,
                                R.animator.card_flip_left_enter,
                                R.animator.card_flip_left_exit)
                        .replace(R.id.frgmCont, new
                                TimerFragment())
                        .addToBackStack(null)
                        .commit();
    }
    private void flipCardSetTime() {
        Log.i(TAG, "flipCard()----------SetTime = "+flipOn);

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_enter,
                        R.animator.card_flip_right_exit,
                        R.animator.card_flip_left_enter,
                        R.animator.card_flip_left_exit)
                .replace(R.id.frgmCont, new
                        PickerFragment())
                .addToBackStack(null)
                .commit();
    }



    private void done(){
        textViewBottom.setText("Done!");
        displayTimer.setText("seconds remaining: " + App.myRemainTime);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, -1);
        mp = MediaPlayer.create(context, R.raw.timer_sound1);
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(context, R.raw.timer_sound1);
            } mp.start();
        } catch(Exception e) { e.printStackTrace(); }
    }

    private void stopSound(){
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
        } catch(Exception e) { e.printStackTrace(); }
    }

    private void addFragment(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            myFragment = new TimerFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.frgmCont, myFragment);
            ft.hide(myFragment);
            ft.commit();

        }

    }

    private void updateViewFrag(long millisUntilFinished){

        Log.i(TAG, " ----ATTEMPT SET TEXT to Fragment");
        if (displayTimer != null) {
            displayTimer.setText("seconds remaining: " + millisUntilFinished);
            displayTimer.setText(""+millisUntilFinished);
            displayTimer.setText(""+remainTimeFormat(millisUntilFinished));

        } else {
            displayTimer.setText("Don't set value");
        }
    }

    private void mySetColor(){
        //yellow-green
        textViewBottom.setTextColor(Color.parseColor("#c1e63c"));
    }

    private long getTimePickerValue(){
        return (((valueSec * 10) + value09) * 1000) + (valueMin * 60_000)+(valueHour*3_600_000);
    }

    public static int[] splitToComponentTimes(BigDecimal biggy)
    {
        long longVal = biggy.longValue();
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        int[] ints = {hours , mins , secs};
        return ints;
    }

   private String remainTimeFormat(long millisUntilFinished){
        int[] myArr =  splitToComponentTimes(BigDecimal.valueOf(millisUntilFinished));
        int hours = myArr[0];
        int minutes = myArr[1];
        int seconds = myArr[2];
        return  String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void showNotif(){

        int NOTIFICATION_ID = 234;

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {


            String CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
           // mChannel.setSound(null,null);
           // mChannel.enableVibration(true);
           // mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);

            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setContentTitle("Timer")
                .setContentText("Time Over !");


        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(resultPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

}
