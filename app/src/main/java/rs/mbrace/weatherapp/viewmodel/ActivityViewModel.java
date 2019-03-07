package rs.mbrace.weatherapp.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import rs.mbrace.weatherapp.model.json.CurrentForecast;
import rs.mbrace.weatherapp.model.repositories.WeatherRepository;
import rs.mbrace.weatherapp.model.room.entities.CityEntity;

public class ActivityViewModel extends AndroidViewModel {

    private WeatherRepository repository;

    public ActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new WeatherRepository(application);
    }

    public LiveData<List<CityEntity>> getAllCities(){
        return repository.getAllCities();
    }

    public LiveData<Long> getCityID(String name, String code){
        return repository.getCityID(name, code);
    }

    public Call<CurrentForecast> getCurrentForecast(long id, String mode, String appID){
        return repository.getCurrentForecast(id, mode, appID);
    }

    public Call<CurrentForecast> getCurrentForecast(String cityName, String mode, String appID){
        return repository.getCurrentForecast(cityName, mode, appID);
    }

    public Call<CurrentForecast> getCurrentForecast(String cityName, String countryCode, String mode, String appID){
        return repository.getCurrentForecast(cityName, countryCode, mode, appID);
    }

    public Call<CurrentForecast> getFiveDayForecast(long id, String mode, String appID){
        return repository.getFiveDayForecast(id, mode, appID);
    }

    public Call<CurrentForecast> getFiveDayForecast(String cityName, String mode, String appID){
        return repository.getFiveDayForecast(cityName, mode, appID);
    }

    public Call<CurrentForecast> getFiveDayForecast(String cityName, String countryCode, String mode, String appID){
        return repository.getFiveDayForecast(cityName, countryCode, mode, appID);
    }
}
