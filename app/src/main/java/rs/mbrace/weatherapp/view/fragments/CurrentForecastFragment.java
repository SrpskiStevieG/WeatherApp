package rs.mbrace.weatherapp.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.mbrace.weatherapp.R;
import rs.mbrace.weatherapp.model.json.CurrentForecast;
import rs.mbrace.weatherapp.model.network.RetrofitApi;
import rs.mbrace.weatherapp.viewmodel.ActivityViewModel;
import rs.mbrace.weatherapp.viewmodel.CurrentForecastViewModel;

public class CurrentForecastFragment extends Fragment {

    public static String TITLE = "Current";
    private long cityID;
    private String cityName;

    public CurrentForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_forecast, container, false);

        final TextView textView = view.findViewById(R.id.textview);

        if(getArguments() != null) {
            if (getArguments().getString("cityName") != null) {
                cityName = getArguments().getString("cityName");
            }

            if(getArguments().getLong("cityID") != 0L){
                cityID = getArguments().getLong("cityID");
            }
        }


        CurrentForecastViewModel viewModel = ViewModelProviders.of(this)
                .get(CurrentForecastViewModel.class);

        //  Get cityID from db if cityID != 0,
        //  else make a call to server using the city name
        if(cityID != 0L){
            // Retrofit call using cityID
            Log.i("url", viewModel.getCurrentForecast(cityID, RetrofitApi.JSON_MODE_PATH, RetrofitApi.API_PATH).request().url().toString());
            viewModel.getCurrentForecast(cityID, RetrofitApi.JSON_MODE_PATH, RetrofitApi.API_PATH).enqueue(new Callback<CurrentForecast>() {
                @Override
                public void onResponse(Call<CurrentForecast> call, Response<CurrentForecast> response) {
                    if(response.isSuccessful()){
                        Log.i("currentForecast", response.body().getName());
                        textView.setText(response.body().getName());
                    }else{
                        textView.setText("No forecast found for that city");
                    }
                }

                @Override
                public void onFailure(Call<CurrentForecast> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            //  Retrofit call using city name
            viewModel.getCurrentForecast(cityName, RetrofitApi.JSON_MODE_PATH, RetrofitApi.API_PATH).enqueue(new Callback<CurrentForecast>() {
                @Override
                public void onResponse(Call<CurrentForecast> call, Response<CurrentForecast> response) {
                    Log.i("currentForecast", call.request().url().toString());
                    if(response.isSuccessful()) {
                        textView.setText(response.body().getName());
                    }else{
                        textView.setText("No forecast found for that city");
                    }
                }

                @Override
                public void onFailure(Call<CurrentForecast> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }

}
