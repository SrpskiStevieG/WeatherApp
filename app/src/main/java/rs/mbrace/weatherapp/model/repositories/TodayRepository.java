package rs.mbrace.weatherapp.model.repositories;

import retrofit2.Call;
import rs.mbrace.weatherapp.model.json.TodayWeather;
import rs.mbrace.weatherapp.model.network.RetrofitApi;
import rs.mbrace.weatherapp.model.network.RetrofitService;

public class TodayRepository {

    private RetrofitApi api;

    public TodayRepository() {
        api = RetrofitService.getInstance().getRetrofit().create(RetrofitApi.class);
    }

    public Call<TodayWeather> getTodayWeather(long id, String mode, String appID){
        return api.getTodayWeather(id, mode, appID);
    }

    public Call<TodayWeather> getTodayWeather(String cityName, String mode, String appID){
        return api.getTodayWeather(cityName, mode, appID);
    }

    public Call<TodayWeather> getTodayWeather(String cityName, String countryCode, String mode, String appID){
        return api.getTodayWeather(cityName, countryCode, mode, appID);
    }
}
