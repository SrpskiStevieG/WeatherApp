package rs.mbrace.weatherapp.model.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CityEntity {

    @PrimaryKey
    private long id;
    private String name;
    private String country;

    public CityEntity(long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
