package com.zenolab.nav.grd.mp3simple.pickertimer;

import android.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.zenolab.nav.grd.mp3simple.pickertimer.event_bus.Events;
import com.zenolab.nav.grd.mp3simple.pickertimer.event_bus.GlobalBus;
import com.zenolab.nav.grd.mp3simple.pickertimer.rxbus.RxBusActivity;
import com.zenolab.nav.grd.mp3simple.pickertimer.rxbus_to_fragment.RxEvents;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TimerFragment extends Fragment {

    //TextView  textView;
    //private static final String TAG = "TimerFragment";

    public static final String TAG = RxBusActivity.class.getSimpleName();
    TextView textView,mTextView,tvDisplay;
    Button button;
    //rx
    private final CompositeDisposable disposables = new CompositeDisposable();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // register the event to listen.
        GlobalBus.getBus().register(this);


        Log.i(TAG, " > ---- onCreateView() ");

        View view = inflater.inflate(R.layout.fragment_timer,
                container, false);

        //MyApplication.displayTimer = (TextView) view.findViewById(R.id.txt_timer);
        //mTextView = (TextView) view.findViewById(R.id.txt_timer);
        tvDisplay = (TextView) view.findViewById(R.id.txt_timer);



        //textView = (TextView) getView().findViewById(R.id.txt_timer);
        //button = (Button) findViewById(R.id.button);


        //rx одноразовые
        /*
        disposables.add(((MyApplication) getApplication())
                .bus()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (object instanceof com.zenolab.nav.grd.mp3simple.pickertimer.rxbus.RxEvents.AutoEvent) {
                            textView.setText("Auto Event Received");
                        } else if (object instanceof com.zenolab.nav.grd.mp3simple.pickertimer.rxbus.RxEvents.TapEvent) {
                            textView.setText("Tap Event Received");
                        }
                    }
                }));
          */

        //---------rxBusModel

        ((MyApplication)getActivity().getApplication()).rxBusController().getStringObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                /*
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        //String msg = ((RxEvents.Message) object).getMessage();
                        //String msg = ((String) object).getMessage();
                        String msg = "RxBUS";
                        tvDisplay.setText(msg + "\n\n" + tvDisplay.getText().toString());
                    }
                });
        */
                //Consumer - A functional interface (callback) that accepts a single value.
         .subscribe(new Consumer<Object>() {
             /**
              * Consume the given value.
              *
              * @param s the value
              * @throws Exception on error
              */
             @Override
             public void accept(Object s) throws Exception {
                 String msg = ((RxEvents.Message) s).getMessage();
                 //String msg = s.getMessage();
                 //String msg = "RxBUS";
                 //tvDisplay.setText(msg + "\n\n" + tvDisplay.getText().toString());
                // tvDisplay.setText(msg  + tvDisplay.getText().toString());
                 tvDisplay.setText(msg  );
                 //int i=0;i++;tvDisplay.setText("plus "+i);
             }

             /*
             @Override
            public void accept(RxEvents.Message string) throws Exception {
                //String msg = ((RxEvents.Message) object).getMessage();
                //String msg = ((String) object).getMessage();
                //String msg = "RxBUS";
                String msg = ((RxEvents.Message) string).getMessage();
                //String msg = string.getMessage();
                tvDisplay.setText(msg + "\n\n" + tvDisplay.getText().toString());
            }
            */
        });







        return view;
    }


   // /*
    //EventBus
    @Subscribe
    public void getMessage(Events.ActivityToFragmentMessage activityFragmentMessage) {

        TextView messageView = (TextView) getView().findViewById(R.id.txt_timer);
        messageView.setText(
                getString(R.string.message_received) +
                        " " + activityFragmentMessage.getMessage());

        Toast.makeText(getActivity(),
                getString(R.string.message_fragment) +
                        " " + activityFragmentMessage.getMessage(),
                Toast.LENGTH_SHORT).show();

    }
   // */

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // unregister the registered event.
        GlobalBus.getBus().unregister(this);
        //rx
        //disposables.clear(); // do not send event after activity has been destroyed
    }




}
