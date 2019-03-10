package rs.mbrace.weatherapp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.mbrace.weatherapp.R;
import rs.mbrace.weatherapp.model.json.Forecast;
import rs.mbrace.weatherapp.model.network.RetrofitApi;
import rs.mbrace.weatherapp.view.adapters.ForecastAdapter;
import rs.mbrace.weatherapp.viewmodel.ForecastViewModel;

public class ForecastFragment extends Fragment {

    public static String TITLE = "Forecast";
    private long cityID;
    private String cityName;
    private List<List<rs.mbrace.weatherapp.model.json.List>> weatherList;

    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        weatherList = new ArrayList<>();

        final RecyclerView forecastRv = view.findViewById(R.id.forecast_rv);
        LinearLayoutManager lManager = new LinearLayoutManager(getContext());
        forecastRv.setLayoutManager(lManager);
        final ForecastAdapter adapter = new ForecastAdapter(getContext(), weatherList);
        forecastRv.setAdapter(adapter);

        final TextView noResultTv = view.findViewById(R.id.no_result);

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
            viewModel.getForecast(cityID, RetrofitApi.JSON_MODE_PATH, RetrofitApi.API_PATH).enqueue(new Callback<Forecast>() {
                @Override
                public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                    if(response.isSuccessful()){
                        noResultTv.setVisibility(View.GONE);
                        forecastRv.setVisibility(View.VISIBLE);

                        List<rs.mbrace.weatherapp.model.json.List> firstDay = response.body().getList().subList(0, 8);
                        List<rs.mbrace.weatherapp.model.json.List> secondDay = response.body().getList().subList(8, 16);
                        List<rs.mbrace.weatherapp.model.json.List> thirdDay = response.body().getList().subList(16, 24);
                        List<rs.mbrace.weatherapp.model.json.List> fourthDay = response.body().getList().subList(24, 32);
                        List<rs.mbrace.weatherapp.model.json.List> fifthDay = response.body().getList().subList(32, response.body().getCnt());

                        weatherList.add(firstDay);
                        weatherList.add(secondDay);
                        weatherList.add(thirdDay);
                        weatherList.add(fourthDay);
                        weatherList.add(fifthDay);

                        adapter.setList(weatherList);
                    }else{
                        noResultTv.setVisibility(View.VISIBLE);
                        forecastRv.setVisibility(View.GONE);
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
                    if(response.isSuccessful()) {
                        noResultTv.setVisibility(View.GONE);
                        forecastRv.setVisibility(View.VISIBLE);

                        List<rs.mbrace.weatherapp.model.json.List> firstDay = response.body().getList().subList(0, 8);
                        List<rs.mbrace.weatherapp.model.json.List> secondDay = response.body().getList().subList(8, 16);
                        List<rs.mbrace.weatherapp.model.json.List> thirdDay = response.body().getList().subList(16, 24);
                        List<rs.mbrace.weatherapp.model.json.List> fourthDay = response.body().getList().subList(24, 32);
                        List<rs.mbrace.weatherapp.model.json.List> fifthDay = response.body().getList().subList(32, response.body().getCnt());

                        weatherList.add(firstDay);
                        weatherList.add(secondDay);
                        weatherList.add(thirdDay);
                        weatherList.add(fourthDay);
                        weatherList.add(fifthDay);

                        adapter.setList(weatherList);
                    }else{
                        noResultTv.setVisibility(View.VISIBLE);
                        forecastRv.setVisibility(View.GONE);
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
