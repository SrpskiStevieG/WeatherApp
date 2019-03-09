package rs.mbrace.weatherapp.viewmodel;

import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import rs.mbrace.weatherapp.model.json.TodayWeather;
import rs.mbrace.weatherapp.model.repositories.TodayRepository;

public class TodayViewModel extends ViewModel {

    private TodayRepository repository;

    public TodayViewModel() {
        repository = new TodayRepository();
    }

    public Call<TodayWeather> getTodayWeather(long id, String mode, String appID){
        return repository.getTodayWeather(id, mode, appID);
    }

    public Call<TodayWeather> getTodayWeather(String cityName, String mode, String appID){
        return repository.getTodayWeather(cityName, mode, appID);
    }

    public Call<TodayWeather> getTodayWeather(String cityName, String countryCode, String mode, String appID){
        return repository.getTodayWeather(cityName, countryCode, mode, appID);
    }
}
