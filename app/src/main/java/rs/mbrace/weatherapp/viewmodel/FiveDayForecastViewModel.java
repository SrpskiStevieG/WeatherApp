package rs.mbrace.weatherapp.viewmodel;

import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import rs.mbrace.weatherapp.model.json.CurrentForecast;
import rs.mbrace.weatherapp.model.json.FiveDayForecast;
import rs.mbrace.weatherapp.model.repositories.FiveDayForecastRepository;

public class FiveDayForecastViewModel extends ViewModel {

    private FiveDayForecastRepository repository;

    public FiveDayForecastViewModel() {
        repository = new FiveDayForecastRepository();
    }

    public Call<FiveDayForecast> getFiveDayForecast(long id, String mode, String appID){
        return repository.getFiveDayForecast(id, mode, appID);
    }

    public Call<FiveDayForecast> getFiveDayForecast(String cityName, String mode, String appID){
        return repository.getFiveDayForecast(cityName, mode, appID);
    }

    public Call<FiveDayForecast> getFiveDayForecast(String cityName, String countryCode, String mode, String appID){
        return repository.getFiveDayForecast(cityName, countryCode, mode, appID);
    }
}
