package rs.mbrace.weatherapp.view.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import rs.mbrace.weatherapp.view.fragments.TodayFragment;
import rs.mbrace.weatherapp.view.fragments.ForecastFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private String cityName;
    private long cityID;

    public ViewPagerAdapter(@NonNull FragmentManager fm, String city) {
        super(fm);
        this.cityName = city;
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm, long id){
        super(fm);
        this.cityID = id;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TodayFragment todayFragment = new TodayFragment();
                Bundle currentArgs = new Bundle();
                currentArgs.putString("cityName", cityName);
                currentArgs.putLong("cityID", cityID);
                todayFragment.setArguments(currentArgs);
                return todayFragment;
            case 1:
                ForecastFragment forecastFragment = new ForecastFragment();
                Bundle fiveDayArgs = new Bundle();
                fiveDayArgs.putString("cityName", cityName);
                fiveDayArgs.putLong("cityID", cityID);
                forecastFragment.setArguments(fiveDayArgs);
                return forecastFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return TodayFragment.TITLE;
            case 1:
                return ForecastFragment.TITLE;
        }
        return super.getPageTitle(position);
    }
}