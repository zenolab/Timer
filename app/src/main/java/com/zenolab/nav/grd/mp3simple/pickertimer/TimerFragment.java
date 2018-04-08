package com.zenolab.nav.grd.mp3simple.pickertimer;

import android.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TimerFragment extends Fragment {

    TextView mTextView;
    private static final String TAG = "TimerFragment";


	/*
	/-------------life cycle fragment--------------

	 !!! Добавление фрагментов через транзакции асинхронно - значит не получится просто достучатся сразу в
	 onCreate из Activity к компоновлке фрагмента !

	onAttach(Activity)
Вызывается, когда фрагмент связывается с активностью. С этого момента мы можем получить ссылку на активность через метод getActivity()

onCreate()
В этом методе можно сделать работу, не связанную с интерфейсом UI. Например, подготовить адаптер.

onCreateView(LayoutInflater, ViewGroup, Bundle)
Вызывается для создания компонентов внутри фрагмента

onActivityCreated(Bundle)
Вызывается, когда отработает метод активности onCreate(), а значит фрагмент может обратиться к компонентам активности

onDestroyView()
Вызывается, когда набор компонентов удаляется из фрагмента

onDetach()
Вызывается, когда фрагмент отвязывается от активности
Одноимённые с методами активности методы фрагментов выполняют аналогичные функции. К примеру, метод onResume() вызывается, когда фрагмент вновь становится видимым.

Метод onStart() вызывается, когда фрагмент становится видимым после запуска такого же метода в родительской активности.

//-----------------------


Фрагмент всегда связан с активностью. Отдельно фрагмент от активности существовать не может.

Если активность останавливается, то её фрагменты также останавливаются.
Если активность уничтожается, то её фрагменты также уничтожаются.

Метод onCreateView() вызывается один раз, когда фрагмент должен загрузить на экран свой интерфейс.
В этом методе вы можете "надуть" (inflate) разметку фрагмента через метод inflate() объекта Inflater,
который задан в параметре метода. В фрагментах без интерфейса вы можете пропустить надувание.

Метод onActivityCreated() вызывается после метода onCreateView(), когда создаётся активность-хозяйка для фрагмента.
Здесь можно объявить объекты, необходимые для Context.

Фрагменты не являются подклассами Context, вам следует использовать метод getActivity(), чтобы получить родительскую активность.
	*/



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.i(TAG, " > ---- onCreateView() ");

        View view = inflater.inflate(R.layout.fragment_timer,
                container, false);

        App.displayTimer = (TextView) view.findViewById(R.id.txt_timer);
        mTextView = (TextView) view.findViewById(R.id.txt_timer);


        return view;
    }

    public void setTextViewText(String value){
        mTextView.setText(value);
    }


}
