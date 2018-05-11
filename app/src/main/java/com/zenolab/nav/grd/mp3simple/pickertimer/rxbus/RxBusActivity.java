package com.zenolab.nav.grd.mp3simple.pickertimer.rxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


//import com.rxjava2.android.samples.MyApplication;
//import com.rxjava2.android.samples.R;
//import com.rxjava2.android.samples.model.RxEvents;

import com.zenolab.nav.grd.mp3simple.pickertimer.MyApplication;
import com.zenolab.nav.grd.mp3simple.pickertimer.R;

import io.reactivex.android.schedulers.AndroidSchedulers; //RxAndroid
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by grd on 4/9/18.
 */

//public class RxBusActivity{}
// /*
public class RxBusActivity extends AppCompatActivity {

    public static final String TAG = RxBusActivity.class.getSimpleName();
    TextView textView;
    Button button;
    //for many syscribers ( implements observer)
    /*
    По аналогии с составной подпиской из RxJava есть и составной Disposable:
    вы можете подписаться на несколько источников, взять возвращаемые Disposable,
    добавить их в CompositeDisposable и одновременно отписаться от всех потоков.
     */
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // do not send event after activity has been destroyed
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);


       // disposables about - https://habr.com/company/badoo/blog/328434/
        //одноразовые
        /* Колбэк onSubscribe вызывается сразу же, как только вы начинаете прослушивать Observable или Flowable,
         и он передаст вам объект одного из двух типов: Disposable или Subscription


        Применительно к Observable тип Disposable позволяет вызывать метод dispose,
        означающий «Я закончил работать с этим ресурсом, мне больше не нужны данные».
        Если у вас есть сетевой запрос, то он может быть отменён.
        Если вы прослушивали бесконечный поток нажатий кнопок, то это будет означать, что вы больше не хотите получать эти события,
         в таком случае можно удалить OnClickListener у View.


        Всё это справедливо и для интерфейса Subscription. Хоть он и называется иначе,
         но используется точно так же: у него есть метод cancel(), аналогичный dispose().

         */
        disposables.add(((MyApplication) getApplication())
                .bus()
                //own method
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // Consumer -  A functional interface (callback) that accepts a single value.
                //public interface Consumer<T>
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        //оператор instanceof, можно узнать, от какого класса обьект
                        if (object instanceof Events.AutoEvent) {
                            textView.setText("Auto Event Received");
                        } else if (object instanceof Events.TapEvent) {
                            textView.setText("Tap Event Received");
                        }
                    }
                }));

        //link on onClick() xml - MainActiivty
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApplication) getApplication())
                        .bus()
                        .send(new Events.TapEvent());
            }
        });
    }



}

// */
