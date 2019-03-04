package rs.mbrace.weatherapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import rs.mbrace.weatherapp.R;
import rs.mbrace.weatherapp.model.room.entities.CityEntity;
import rs.mbrace.weatherapp.viewmodel.ActivityViewModel;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> citiesNames;
    private int cityID;
    private String cityName;
    private TextView cityNameTv;
    private EditText searchCityEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stethoInit();

        initViews();
        dbInit();

        searchCityEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                for (int j = 0; j < citiesNames.size(); j++) {
                    if(citiesNames.get(j).toLowerCase().contains(charSequence.toString().toLowerCase())){
                        Log.i("search", "city found: " + citiesNames.get(j));
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                if(!citiesNames.contains(editable.toString())){
//                    Log.i("search", "no city found");
//                }else{
//                    Log.i("search", "city found: " + editable.toString());
//                }
            }
        });
    }

    private void initViews() {
        cityNameTv = findViewById(R.id.selected_city);
        searchCityEt = findViewById(R.id.search_city_et);
    }

    private void stethoInit() {
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(getApplicationContext())
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }

    private void dbInit() {
        final ActivityViewModel viewModel = ViewModelProviders.of(this)
                .get(ActivityViewModel.class);

        viewModel.getAllCities().observe(this, new Observer<List<CityEntity>>() {
            @Override
            public void onChanged(List<CityEntity> cityEntities) {
                citiesNames = new ArrayList<>();
                for (int i = 0; i < cityEntities.size(); i++) {
                    citiesNames.add(cityEntities.get(i).getName() + "," + cityEntities.get(i).getCountry());
                    Log.i("cityList", citiesNames.get(i));
                }
            }
        });
    }
}
