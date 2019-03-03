package rs.mbrace.weatherapp.model.room.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import rs.mbrace.weatherapp.model.json.CityJSON;
import rs.mbrace.weatherapp.model.json.JacksonUtil;
import rs.mbrace.weatherapp.model.room.entities.CityEntity;

@Database(entities = {CityEntity.class}, version = 1)
public abstract class WeatherDB extends RoomDatabase {

    private static WeatherDB instance;

    public abstract CityDAO cityDAO();

    public static synchronized WeatherDB getInstance(final Context ctx){
        if(instance == null){
            Log.i("roomDB", "pre-db");
            instance = Room.databaseBuilder(ctx.getApplicationContext(), WeatherDB.class, "weatherapp")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Log.i("roomDB", "pre-start");
                            instance.populateDbOnCreate(ctx);
                        }
                    }).build();
        }

        return instance;
    }

    private void populateDbOnCreate(Context ctx){
        try {
            //  Read json from file
            InputStream is = ctx.getAssets().open("city_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            //  JSON to POJO
            List<CityJSON> cityList = JacksonUtil.getObjectMapper().readValue(json, new TypeReference<ArrayList<CityJSON>>() {
            });

            //  Insert cities in new thread
            Log.i("roomDB", "start");
            InsertCitiesTask task = new InsertCitiesTask();
            task.execute(cityList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static class InsertCitiesTask extends AsyncTask<List<CityJSON>, Void, Void> {

        @Override
        protected Void doInBackground(List<CityJSON>... cities) {
            for (CityJSON city : cities[0]) {
                instance.cityDAO().insertCity(new CityEntity(city.getId(), city.getName(), city.getCountry()));
            }
            Log.i("roomDB", "finish");
            return null;
        }
    }
}
