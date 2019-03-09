package rs.mbrace.weatherapp.model.repositories;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
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
}
