package rs.mbrace.weatherapp.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.mbrace.weatherapp.R;
import rs.mbrace.weatherapp.model.json.TodayWeather;
import rs.mbrace.weatherapp.model.network.RetrofitApi;
import rs.mbrace.weatherapp.viewmodel.TodayViewModel;

public class TodayFragment extends Fragment {

    public static String TITLE = "Current";
    private long cityID;
    private String cityName;

    public TodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_weather, container, false);

        final TextView textView = view.findViewById(R.id.textview);

        if(getArguments() != null) {
            if (getArguments().getString("cityName") != null) {
                cityName = getArguments().getString("cityName");
            }

            if(getArguments().getLong("cityID") != 0L){
                cityID = getArguments().getLong("cityID");
            }
        }


        TodayViewModel viewModel = ViewModelProviders.of(this)
                .get(TodayViewModel.class);

        //  Get cityID from db if cityID != 0,
        //  else make a call to server using the city name
        if(cityID != 0L){
            // Retrofit call using cityID
            Log.i("url", viewModel.getTodayWeather(cityID, RetrofitApi.JSON_MODE_PATH, RetrofitApi.API_PATH).request().url().toString());
            viewModel.getTodayWeather(cityID, RetrofitApi.JSON_MODE_PATH, RetrofitApi.API_PATH).enqueue(new Callback<TodayWeather>() {
                @Override
                public void onResponse(Call<TodayWeather> call, Response<TodayWeather> response) {
                    if(response.isSuccessful()){
                        Log.i("currentForecast", response.body().getName());
                        textView.setText(response.body().getName());
                    }else{
                        textView.setText("No forecast found for that city");
                    }
                }

                @Override
                public void onFailure(Call<TodayWeather> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            //  Retrofit call using city name
            viewModel.getTodayWeather(cityName, RetrofitApi.JSON_MODE_PATH, RetrofitApi.API_PATH).enqueue(new Callback<TodayWeather>() {
                @Override
                public void onResponse(Call<TodayWeather> call, Response<TodayWeather> response) {
                    Log.i("currentForecast", call.request().url().toString());
                    if(response.isSuccessful()) {
                        textView.setText(response.body().getName());
                    }else{
                        textView.setText("No forecast found for that city");
                    }
                }

                @Override
                public void onFailure(Call<TodayWeather> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }

}
