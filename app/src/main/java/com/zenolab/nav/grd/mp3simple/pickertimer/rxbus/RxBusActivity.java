package com.zenolab.nav.grd.mp3simple.pickertimer.rxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zenolab.nav.grd.mp3simple.pickertimer.MyApplication;
import com.zenolab.nav.grd.mp3simple.pickertimer.R;

import io.reactivex.android.schedulers.AndroidSchedulers; //RxAndroid
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by grd on 4/9/18.
 */

public class RxBusActivity extends AppCompatActivity {

    public static final String TAG = RxBusActivity.class.getSimpleName();
    TextView textView;
    Button button;
    
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); 
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

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

        //onClick() xml (MainActiivty)
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

