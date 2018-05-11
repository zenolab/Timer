package com.zenolab.nav.grd.mp3simple.pickertimer.rxbus_to_fragment;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBusController {


    private static RxBusController instance;

    private PublishSubject<Object> subjectBus = PublishSubject.create();

    //от какого класса произошел объект CUSTOM!
    public static RxBusController instanceOf(){
        if(instance == null){
            instance = new RxBusController();
        }
        return instance;
    }

    /**
     * Pass a String down to event listeners.
     * @param string
     */
    public void sendSetString(Object string) {
        subjectBus.onNext(string);
    }

    /**
     * Subscribe to this Observable. On event, do something e.g. replace a fragment
     */
    public Observable<Object> getStringObservable() {
        return subjectBus;
    }

}
