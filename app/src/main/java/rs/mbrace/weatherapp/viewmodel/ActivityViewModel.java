package rs.mbrace.weatherapp.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
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
}
