package rs.mbrace.weatherapp.model.repositories;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import rs.mbrace.weatherapp.model.json.City;
import rs.mbrace.weatherapp.model.json.CurrentForecast;
import rs.mbrace.weatherapp.model.network.RetrofitApi;
import rs.mbrace.weatherapp.model.network.RetrofitService;
import rs.mbrace.weatherapp.model.room.dao.CityDAO;
import rs.mbrace.weatherapp.model.room.dao.WeatherDB;
import rs.mbrace.weatherapp.model.room.entities.CityEntity;

public class WeatherRepository {

    private CityDAO cityDAO;
    private RetrofitApi api;

    public WeatherRepository(Application application){
        instantiateDB(application);
    }

    public void instantiateDB(Application application) {
        WeatherDB db = WeatherDB.getInstance(application);
        cityDAO = db.cityDAO();
        api = RetrofitService.getInstance().getRetrofit().create(RetrofitApi.class);
    }

    public LiveData<List<CityEntity>> getAllCities(){
        return cityDAO.getAllCities();
    }

    public LiveData<Long> getCityID(String name, String countryCode){
        return cityDAO.getCityID(name, countryCode);
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

    public Call<CurrentForecast> getFiveDayForecast(long id, String mode, String appID){
        return api.getFiveDayForecast(id, mode, appID);
    }

    public Call<CurrentForecast> getFiveDayForecast(String cityName, String mode, String appID){
        return api.getFiveDayForecast(cityName, mode, appID);
    }

    public Call<CurrentForecast> getFiveDayForecast(String cityName, String countryCode, String mode, String appID){
        return api.getFiveDayForecast(cityName, countryCode, mode, appID);
    }
}
