package com.zenolab.nav.grd.mp3simple.pickertimer.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by grd on 4/9/18.
 */

// https://blog.mindorks.com/implementing-eventbus-with-rxjava-rxbus-e6c940a94bd8
public class RxBus {

    public RxBus() {
    }

    //Subject — это своего рода мост или прокси, доступный в некоторых реализациях ReactiveX, который действует как наблюдатель(Observer) и наблюдаемый(Observable).
    //Publish Subject
    //Излучает(emit) все последующие элементы наблюдаемого источника в момент подписки.
    private PublishSubject<Object> bus = PublishSubject.create();

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return bus;
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
}
