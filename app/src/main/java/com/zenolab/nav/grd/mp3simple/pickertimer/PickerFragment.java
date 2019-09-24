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

import static com.zenolab.nav.grd.mp3simple.pickertimer.App.valueDec;
import static com.zenolab.nav.grd.mp3simple.pickertimer.App.valueHour;
import static com.zenolab.nav.grd.mp3simple.pickertimer.App.valueMin;
import static com.zenolab.nav.grd.mp3simple.pickertimer.App.valueSec;
import static com.zenolab.nav.grd.mp3simple.pickertimer.App.displayTimer; //data transfer


public class PickerFragment extends Fragment {

    private static final String TAG = "PickerFragment";

    private TextView textFieldHour, textFieldMin, textFieldSec, textFieldDec;
    private NumberPicker numberPickerHour;
    private NumberPicker numberPickerMin;
    private NumberPicker numberPickerSec;
    private NumberPicker numberPickerDec;

    int millsDec = 10;
    int millsSec = 15;
    int millsMin = 20;
    int millsHour = 30;

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

        textFieldHour = (TextView) view.findViewById(R.id.textViewHeader);
        textFieldMin = (TextView) view.findViewById(R.id.textView2);
        textFieldSec = (TextView) view.findViewById(R.id.textView3);
        textFieldDec = (TextView) view.findViewById(R.id.textView4);

        numberPickerHour = view.findViewById(R.id.numberPicker);
        numberPickerHour.setMinValue(0);
        numberPickerHour.setMaxValue(23);

        numberPickerMin = view.findViewById(R.id.numberPicker2);
        numberPickerMin.setMinValue(0);
        numberPickerMin.setMaxValue(59);

        numberPickerSec = view.findViewById(R.id.numberPicker3);
        numberPickerSec.setMinValue(0);
        numberPickerSec.setMaxValue(5);

        numberPickerDec = view.findViewById(R.id.numberPicker4);
        numberPickerDec.setMinValue(0);
        numberPickerDec.setMaxValue(9);

        numberPickerHour.setOnValueChangedListener(onValueChangeListenerHour);
        numberPickerMin.setOnValueChangedListener(onValueChangeListenerMin);
        numberPickerSec.setOnValueChangedListener(onValueChangeListenerSec);
        numberPickerDec.setOnValueChangedListener(onValueChangeListenerDec);

        return view;
    }

    NumberPicker.OnValueChangeListener onValueChangeListenerHour =
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    valueHour = numberPicker.getValue();


                    Log.i(TAG, "PickerHour" + valueHour);
                    textFieldHour.setText("" + valueHour);

                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(millsHour);

                }

            };

    NumberPicker.OnValueChangeListener onValueChangeListenerMin =
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    valueMin = numberPicker.getValue();

                    Log.i(TAG, "PickerMin" + valueMin);
                    textFieldMin.setText("" + valueMin);

                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(millsMin);
                }
            };
    NumberPicker.OnValueChangeListener onValueChangeListenerSec =
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    valueSec = numberPicker.getValue();

                    Log.i(TAG, "PickerSec05" + valueSec);
                    textFieldSec.setText("" + valueSec);

                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(millsSec);
                }
            };
    NumberPicker.OnValueChangeListener onValueChangeListenerDec =
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    valueDec = numberPicker.getValue();

                    Log.i(TAG, "Picker0_9" + valueDec);
                    textFieldDec.setText("" + valueDec);

                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(millsDec);
                }
            };

}
