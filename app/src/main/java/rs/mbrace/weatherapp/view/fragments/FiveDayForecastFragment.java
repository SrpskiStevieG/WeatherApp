package rs.mbrace.weatherapp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import rs.mbrace.weatherapp.R;

public class FiveDayForecastFragment extends Fragment {

    public static String TITLE = "5 day";

    public FiveDayForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_five_day_forecast, container, false);
    }

}
