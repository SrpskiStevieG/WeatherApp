package rs.mbrace.weatherapp.model.repositories;

import retrofit2.Call;
import rs.mbrace.weatherapp.model.json.CurrentForecast;
import rs.mbrace.weatherapp.model.network.RetrofitApi;
import rs.mbrace.weatherapp.model.network.RetrofitService;

public class CurrentForecastRepository {

    private RetrofitApi api;

    public CurrentForecastRepository() {
        api = RetrofitService.getInstance().getRetrofit().create(RetrofitApi.class);
    }

    public Call<CurrentForecast> getCurrentForecast(long id, String mode, String appID){
        return api.getCurrentForecast(id, mode, appID);
    }

    public Call<CurrentForecast> getCurrentForecast(String cityName, String mode, String appID){
        return api.getCurrentForecast(cityName, mode, appID);
    }

    public Call<CurrentForecast> getCurrentForecast(String cityName, String countryCode, String mode, String appID){
        return api.getCurrentForecast(cityName, countryCode, mode, appID);
    }
}
