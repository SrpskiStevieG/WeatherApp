package rs.mbrace.weatherapp.model.repositories;

import retrofit2.Call;
import rs.mbrace.weatherapp.model.json.Forecast;
import rs.mbrace.weatherapp.model.network.RetrofitApi;
import rs.mbrace.weatherapp.model.network.RetrofitService;

public class ForecastRepository {

    private RetrofitApi api;

    public ForecastRepository() {
        api = RetrofitService.getInstance().getRetrofit().create(RetrofitApi.class);
    }

    public Call<Forecast> getForecast(long id, String mode, String appID){
        return api.getForecast(id, mode, appID);
    }

    public Call<Forecast> getForecast(String cityName, String mode, String appID){
        return api.getForecast(cityName, mode, appID);
    }

    public Call<Forecast> getForecast(String cityName, String countryCode, String mode, String appID){
        return api.getForecast(cityName, countryCode, mode, appID);
    }
}
