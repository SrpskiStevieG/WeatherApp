package rs.mbrace.weatherapp.model.room.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import rs.mbrace.weatherapp.model.room.entities.CityEntity;

@Dao
public interface CityDAO {

    @Query("SELECT * FROM CityEntity")
    LiveData<List<CityEntity>> getAllCities();

    @Query("SELECT id FROM CityEntity WHERE name = :name AND country = :country")
    LiveData<Integer> getCityID(String name, String country);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(CityEntity city);
}
