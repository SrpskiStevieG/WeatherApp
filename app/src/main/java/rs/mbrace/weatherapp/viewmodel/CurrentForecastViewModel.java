package rs.mbrace.weatherapp.viewmodel;

import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import rs.mbrace.weatherapp.model.json.CurrentForecast;
import rs.mbrace.weatherapp.model.repositories.CurrentForecastRepository;

public class CurrentForecastViewModel extends ViewModel {

    private CurrentForecastRepository repository;

    public CurrentForecastViewModel() {
        repository = new CurrentForecastRepository();
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
}
