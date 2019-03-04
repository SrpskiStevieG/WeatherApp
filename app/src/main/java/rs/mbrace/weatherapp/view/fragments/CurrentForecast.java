package rs.mbrace.weatherapp.view.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rs.mbrace.weatherapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentForecast extends Fragment {


    public CurrentForecast() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_forecast, container, false);
    }

}
