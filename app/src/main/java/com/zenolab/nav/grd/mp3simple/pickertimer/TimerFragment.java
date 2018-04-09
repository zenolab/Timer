package com.zenolab.nav.grd.mp3simple.pickertimer;

import android.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.zenolab.nav.grd.mp3simple.pickertimer.event_bus.Events;
import com.zenolab.nav.grd.mp3simple.pickertimer.event_bus.GlobalBus;


public class TimerFragment extends Fragment {

    TextView mTextView;
    private static final String TAG = "TimerFragment";


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

        //App.displayTimer = (TextView) view.findViewById(R.id.txt_timer);
        //mTextView = (TextView) view.findViewById(R.id.txt_timer);


        return view;
    }


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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // unregister the registered event.
        GlobalBus.getBus().unregister(this);
    }




}
