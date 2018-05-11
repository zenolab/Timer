package com.zenolab.nav.grd.mp3simple.pickertimer;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import static com.zenolab.nav.grd.mp3simple.pickertimer.MyApplication.value09;
import static com.zenolab.nav.grd.mp3simple.pickertimer.MyApplication.valueHour;
import static com.zenolab.nav.grd.mp3simple.pickertimer.MyApplication.valueMin;
import static com.zenolab.nav.grd.mp3simple.pickertimer.MyApplication.valueSec;
import static com.zenolab.nav.grd.mp3simple.pickertimer.MyApplication.displayTimer; //data transfer


public class PickerFragment extends Fragment {


    private static final String TAG = "PickerFragment";

    private TextView textField, textField2, textField3, textField4;
    private NumberPicker numberPicker,numberPicker2,numberPicker3,numberPicker4;

    int mills = 10;
    int mills2 = 15;
    int mills3 = 20;
    int mills4 = 30;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_picker,
                container, false);

        displayTimer = (TextView) view.findViewById(R.id.countDown_Timer_Frag);

        textField = (TextView) view.findViewById(R.id.textViewHeader);
        textField2 = (TextView) view.findViewById(R.id.textView2);
        textField3 = (TextView) view.findViewById(R.id.textView3);
        textField4 = (TextView) view.findViewById(R.id.textView4);

        numberPicker = view.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(23);

        numberPicker2 = view.findViewById(R.id.numberPicker2);
        numberPicker2.setMinValue(0);
        numberPicker2.setMaxValue(59);

        numberPicker3 = view.findViewById(R.id.numberPicker3);
        numberPicker3.setMinValue(0);
        numberPicker3.setMaxValue(5);

        numberPicker4 = view.findViewById(R.id.numberPicker4);
        numberPicker4.setMinValue(0);
        numberPicker4.setMaxValue(9);

        numberPicker.setOnValueChangedListener(onValueChangeListener);
        numberPicker2.setOnValueChangedListener(onValueChangeListener2);
        numberPicker3.setOnValueChangedListener(onValueChangeListener3);
        numberPicker4.setOnValueChangedListener(onValueChangeListener4);

        return view;

    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    valueHour = numberPicker.getValue();

                    Log.i(TAG, "PickerHour"+valueHour);
                    textField.setText(""+valueHour);

                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(mills4);

                }

            };

    NumberPicker.OnValueChangeListener onValueChangeListener2 =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    valueMin = numberPicker.getValue();

                    Log.i(TAG, "PickerMin"+valueMin);
                    textField2.setText(""+valueMin);

                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(mills3);
                }
            };
    NumberPicker.OnValueChangeListener onValueChangeListener3 =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    valueSec = numberPicker.getValue();

                    Log.i(TAG, "PickerSec05"+valueSec);
                    textField3.setText(""+valueSec);

                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(mills2);
                }
            };
    NumberPicker.OnValueChangeListener onValueChangeListener4 =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    value09 = numberPicker.getValue();

                    Log.i(TAG, "PickerSec09"+value09);
                    textField4.setText(""+value09);

                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(mills);
                }
            };






}
