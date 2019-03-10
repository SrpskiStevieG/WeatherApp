package rs.mbrace.weatherapp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
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

    public static String TITLE = "Today";
    private long cityID;
    private String cityName;
    private ConstraintLayout containerLayout;
    private TextView noResultTv;
    private ImageView icon;
    private TextView tempTv;
    private TextView descriptionTv;
    private TextView tempMinMaxTv;
    private TextView windTv;
    private TextView humidityTv;

    public TodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_weather, container, false);

        containerLayout = view.findViewById(R.id.container);
        noResultTv = view.findViewById(R.id.no_result);
        icon = view.findViewById(R.id.icon);
        tempTv = view.findViewById(R.id.temperature);
        descriptionTv = view.findViewById(R.id.description);
        tempMinMaxTv = view.findViewById(R.id.temp_min_max);
        windTv = view.findViewById(R.id.wind_tv);
        humidityTv = view.findViewById(R.id.humidity_tv);

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
            viewModel.getTodayWeather(cityID, RetrofitApi.JSON_MODE_PATH, RetrofitApi.API_PATH).enqueue(new Callback<TodayWeather>() {
                @Override
                public void onResponse(Call<TodayWeather> call, Response<TodayWeather> response) {
                    if(response.isSuccessful()){
                        fillData(response);
                    }else{
                        noResultFound();
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
                    if(response.isSuccessful()) {
                        fillData(response);
                    }else{
                        noResultFound();
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

    private void noResultFound() {
        noResultTv.setVisibility(View.VISIBLE);
        icon.setVisibility(View.GONE);
        tempTv.setVisibility(View.GONE);
        descriptionTv.setVisibility(View.GONE);
        tempMinMaxTv.setVisibility(View.GONE);
        windTv.setVisibility(View.GONE);
        humidityTv.setVisibility(View.GONE);
    }

    private void fillData(Response<TodayWeather> response){

        noResultTv.setVisibility(View.GONE);
        icon.setVisibility(View.VISIBLE);
        tempTv.setVisibility(View.VISIBLE);
        descriptionTv.setVisibility(View.VISIBLE);
        tempMinMaxTv.setVisibility(View.VISIBLE);
        windTv.setVisibility(View.VISIBLE);
        humidityTv.setVisibility(View.VISIBLE);

        TodayWeather today = response.body();

        switch (today.getWeather().get(0).getIcon()){
            case "01d":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_sun));
                containerLayout.setBackground(getResources().getDrawable(R.drawable.clear_sky_bg));
                break;
            case "01n":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_moon));
                containerLayout.setBackgroundColor(getResources().getColor(R.color.night_bg));
                break;
            case "02d":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_sun));
                containerLayout.setBackground(getResources().getDrawable(R.drawable.light_rain_bg));
                break;
            case "02n":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_moon));
                containerLayout.setBackgroundColor(getResources().getColor(R.color.night_bg));
                break;
            case "03d":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud));
                containerLayout.setBackground(getResources().getDrawable(R.drawable.light_rain_bg));
                break;
            case "03n":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud));
                containerLayout.setBackgroundColor(getResources().getColor(R.color.night_bg));
                break;
            case "04d":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud));
                containerLayout.setBackground(getResources().getDrawable(R.drawable.light_rain_bg));
                break;
            case "04n":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud));
                containerLayout.setBackgroundColor(getResources().getColor(R.color.night_bg));
                break;
            case "09d":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_rain));
                containerLayout.setBackground(getResources().getDrawable(R.drawable.heavy_rain_bg));
                break;
            case "09n":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_rain));
                containerLayout.setBackgroundColor(getResources().getColor(R.color.night_bg));
                break;
            case "10d":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_drizzle_sun));
                containerLayout.setBackground(getResources().getDrawable(R.drawable.light_rain_bg));
                break;
            case "10n":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_drizzle_moon));
                containerLayout.setBackgroundColor(getResources().getColor(R.color.night_bg));
                break;
            case "11d":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_lightning));
                containerLayout.setBackground(getResources().getDrawable(R.drawable.heavy_rain_bg));
                break;
            case "11n":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_lightning));
                containerLayout.setBackgroundColor(getResources().getColor(R.color.night_bg));
                break;
            case "13d":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_snow));
                containerLayout.setBackground(getResources().getDrawable(R.drawable.light_rain_bg));
                break;
            case "13n":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_snow));
                containerLayout.setBackgroundColor(getResources().getColor(R.color.night_bg));
                break;
            case "50d":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_fog));
                containerLayout.setBackground(getResources().getDrawable(R.drawable.light_rain_bg));
                break;
            case "50n":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_cloud_fog));
                containerLayout.setBackgroundColor(getResources().getColor(R.color.night_bg));
                break;
        }

        String temperature = tempCalc(today.getMain().getTemp()) + "\u00b0C";
        String desc = today.getWeather().get(0).getMain();
        String tempMinMax = tempCalc(today.getMain().getTempMin()) + "\u00b0C / " + tempCalc(today.getMain().getTempMax()) + "\u00b0C";
        String wind = today.getWind().getSpeed() + " m/s";
        String humidity = today.getMain().getHumidity() + "%";

        tempTv.setText(temperature);
        descriptionTv.setText(desc);
        tempMinMaxTv.setText(tempMinMax);
        windTv.setText(wind);
        humidityTv.setText(humidity);
    }

    private int tempCalc(double kelvin){
        return (int) (kelvin - 273.15);
    }

}
