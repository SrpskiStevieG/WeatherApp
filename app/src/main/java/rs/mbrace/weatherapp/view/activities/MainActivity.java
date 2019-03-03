package rs.mbrace.weatherapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import rs.mbrace.weatherapp.R;
import rs.mbrace.weatherapp.model.room.entities.CityEntity;
import rs.mbrace.weatherapp.viewmodel.ActivityViewModel;

import android.os.Bundle;
import android.util.Log;

import com.facebook.stetho.Stetho;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stethoInit();

        dbInit();
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
        ActivityViewModel viewModel = ViewModelProviders.of(this)
                .get(ActivityViewModel.class);

        viewModel.getAllCities().observe(this, new Observer<List<CityEntity>>() {
            @Override
            public void onChanged(List<CityEntity> cityEntities) {
                Log.i("cityList", "changed");
            }
        });
    }
}
