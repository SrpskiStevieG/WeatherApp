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
import rs.mbrace.weatherapp.model.json.Forecast;
import rs.mbrace.weatherapp.model.network.RetrofitApi;
import rs.mbrace.weatherapp.viewmodel.ForecastViewModel;

public class ForecastFragment extends Fragment {

    public static String TITLE = "5 day";
    private long cityID;
    private String cityName;

    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        final TextView textView = view.findViewById(R.id.five_day_tv);

        if(getArguments() != null) {
            if (getArguments().getString("cityName") != null) {
                cityName = getArguments().getString("cityName");
            }

            if(getArguments().getLong("cityID") != 0L){
                cityID = getArguments().getLong("cityID");
            }
        }


        ForecastViewModel viewModel = ViewModelProviders.of(this)
                .get(ForecastViewModel.class);

        //  Get cityID from db if cityID != 0,
        //  else make a call to server using the city name
        if(cityID != 0L){
            // Retrofit call using cityID
            Log.i("url", viewModel.getForecast(cityID, RetrofitApi.JSON_MODE_PATH, RetrofitApi.API_PATH).request().url().toString());
            viewModel.getForecast(cityID, RetrofitApi.JSON_MODE_PATH, RetrofitApi.API_PATH).enqueue(new Callback<Forecast>() {
                @Override
                public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                    if(response.isSuccessful()){
                        Log.i("currentForecast", response.body().getCity().getName());
                        textView.setText(response.body().getCity().getName() + " 5 days");
                    }else{
                        textView.setText("No forecast found for that city");
                    }
                }

                @Override
                public void onFailure(Call<Forecast> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            //  Retrofit call using city name
            viewModel.getForecast(cityName, RetrofitApi.JSON_MODE_PATH, RetrofitApi.API_PATH).enqueue(new Callback<Forecast>() {
                @Override
                public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                    Log.i("currentForecast", call.request().url().toString());
                    if(response.isSuccessful()) {
                        textView.setText(response.body().getCity().getName() + " 5 days");
                    }else{
                        textView.setText("No forecast found for that city");
                    }
                }

                @Override
                public void onFailure(Call<Forecast> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }

}
