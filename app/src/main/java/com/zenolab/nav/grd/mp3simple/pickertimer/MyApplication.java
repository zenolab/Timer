package com.zenolab.nav.grd.mp3simple.pickertimer;

import android.app.Application;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;


import com.zenolab.nav.grd.mp3simple.pickertimer.rxbus.Events;
import com.zenolab.nav.grd.mp3simple.pickertimer.rxbus.RxBus;
//----------------------------------------------
import com.zenolab.nav.grd.mp3simple.pickertimer.rxbus_to_fragment.RxEvents;
import com.zenolab.nav.grd.mp3simple.pickertimer.rxbus_to_fragment.RxBusController;
//----------------------------------------------

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by grd on 2/3/18.
 */

public class MyApplication extends Application {

    static TextView displayTimer;
    static long myRemainTime;

    //create enum
    static int value09 = 0;
    static int valueSec = 0;
    static int valueMin = 0;
    static int valueHour = 0;

    //-----------------RxBus singleton-------------------

    public static final String TAG = "MyApplication";
    private RxBus bus;

    //To fragment
    private RxBusController _rxBusController;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new RxBus();
        //---------to fragment----------
        _rxBusController = new RxBusController();
        //-------------------
    }

    public RxBus bus() {
        return bus;
    }

    //will send message auto after 2 second if not press button yet
    public void sendAutoEvent() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        bus.send(new Events.AutoEvent());
                    }
                });
    }
    //-------------------------------------------

    public RxBusController rxBusController() {
        return _rxBusController;
    }
}
