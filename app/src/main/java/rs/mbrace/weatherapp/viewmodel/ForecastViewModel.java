package rs.mbrace.weatherapp.viewmodel;

import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import rs.mbrace.weatherapp.model.json.Forecast;
import rs.mbrace.weatherapp.model.repositories.ForecastRepository;

public class ForecastViewModel extends ViewModel {

    private ForecastRepository repository;

    public ForecastViewModel() {
        repository = new ForecastRepository();
    }

    public Call<Forecast> getForecast(long id, String mode, String appID){
        return repository.getForecast(id, mode, appID);
    }

    public Call<Forecast> getForecast(String cityName, String mode, String appID){
        return repository.getForecast(cityName, mode, appID);
    }

    public Call<Forecast> getForecast(String cityName, String countryCode, String mode, String appID){
        return repository.getForecast(cityName, countryCode, mode, appID);
    }
}
