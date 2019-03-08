package rs.mbrace.weatherapp.model.repositories;

import retrofit2.Call;
import rs.mbrace.weatherapp.model.json.CurrentForecast;
import rs.mbrace.weatherapp.model.json.FiveDayForecast;
import rs.mbrace.weatherapp.model.network.RetrofitApi;
import rs.mbrace.weatherapp.model.network.RetrofitService;

public class FiveDayForecastRepository {

    private RetrofitApi api;

    public FiveDayForecastRepository() {
        api = RetrofitService.getInstance().getRetrofit().create(RetrofitApi.class);
    }

    public Call<FiveDayForecast> getFiveDayForecast(long id, String mode, String appID){
        return api.getFiveDayForecast(id, mode, appID);
    }

    public Call<FiveDayForecast> getFiveDayForecast(String cityName, String mode, String appID){
        return api.getFiveDayForecast(cityName, mode, appID);
    }

    public Call<FiveDayForecast> getFiveDayForecast(String cityName, String countryCode, String mode, String appID){
        return api.getFiveDayForecast(cityName, countryCode, mode, appID);
    }
}
