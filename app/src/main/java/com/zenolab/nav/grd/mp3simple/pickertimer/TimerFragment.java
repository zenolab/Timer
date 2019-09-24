package com.zenolab.nav.grd.mp3simple.pickertimer;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TimerFragment extends Fragment {

    private static final String TAG = "TimerFragment";
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer,
                container, false);

        App.displayTimer = (TextView) view.findViewById(R.id.txt_timer);
        textView = (TextView) view.findViewById(R.id.txt_timer);
        return view;
    }

    public void setTextViewText(String value){
        textView.setText(value);
    }

}
