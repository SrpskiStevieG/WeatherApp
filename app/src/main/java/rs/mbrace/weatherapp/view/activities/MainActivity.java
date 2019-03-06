package rs.mbrace.weatherapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rs.mbrace.weatherapp.R;
import rs.mbrace.weatherapp.model.room.entities.CityEntity;
import rs.mbrace.weatherapp.view.adapters.CityAdapter;
import rs.mbrace.weatherapp.viewmodel.ActivityViewModel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> citiesNames;
    private int cityID;
    private String cityName;
    private TextView cityNameTv;
    private RecyclerView dbCitiesRv;
    private EditText searchCityEt;
    private CityAdapter adapter;
    public static final String TAG_CITY_CLICKED = "cityListener";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Method for viewing database in Chrome
        stethoInit();

        initViews();
        initData();

        searchCityEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 0) {
                    dbCitiesRv.setVisibility(View.VISIBLE);
                    adapter.getFilter().filter(charSequence);
                }else{
                    dbCitiesRv.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //  Register listener for the selected city
        LocalBroadcastManager.getInstance(this).registerReceiver(cityReceiver, new IntentFilter(TAG_CITY_CLICKED));
    }

    private void initViews() {
        cityNameTv = findViewById(R.id.selected_city_tv);
        searchCityEt = findViewById(R.id.search_city_et);
        dbCitiesRv = findViewById(R.id.db_cities_rv);

        LinearLayoutManager lManager = new LinearLayoutManager(this);
        dbCitiesRv.setLayoutManager(lManager);
        adapter = new CityAdapter(this);
        dbCitiesRv.setAdapter(adapter);
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

    private void initData() {
        final ActivityViewModel viewModel = ViewModelProviders.of(this)
                .get(ActivityViewModel.class);

        viewModel.getAllCities().observe(this, new Observer<List<CityEntity>>() {
            @Override
            public void onChanged(List<CityEntity> cityEntities) {
                citiesNames = new ArrayList<>();
                for (int i = 0; i < cityEntities.size(); i++) {
                    citiesNames.add(cityEntities.get(i).getName() + "," + cityEntities.get(i).getCountry());
                }
                adapter.setList(citiesNames);
            }
        });
    }

    private BroadcastReceiver cityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getStringExtra("selectedCity").equals("Search anyway")){
                cityName = searchCityEt.getText().toString();
            }else {
                cityName = intent.getStringExtra("selectedCity");
            }

            cityNameTv.setText(cityName);
            searchCityEt.getText().clear();
            dbCitiesRv.setVisibility(View.GONE);

            //TODO  Retrofit call
        }
    };
}
