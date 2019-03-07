package rs.mbrace.weatherapp.view.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import rs.mbrace.weatherapp.view.fragments.CurrentForecastFragment;
import rs.mbrace.weatherapp.view.fragments.FiveDayForecastFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private String cityName;

    public ViewPagerAdapter(@NonNull FragmentManager fm, String city) {
        super(fm);
        this.cityName = city;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CurrentForecastFragment currentForecastFragment = new CurrentForecastFragment();
                Bundle currentArgs = new Bundle();
                currentArgs.putString("city", cityName);
                currentForecastFragment.setArguments(currentArgs);
                return currentForecastFragment;
            case 1:
                FiveDayForecastFragment fiveDayForecastFragment = new FiveDayForecastFragment();
                Bundle fiveDayArgs = new Bundle();
                fiveDayArgs.putString("city", cityName);
                fiveDayForecastFragment.setArguments(fiveDayArgs);
                return fiveDayForecastFragment;
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
                return CurrentForecastFragment.TITLE;
            case 1:
                return FiveDayForecastFragment.TITLE;
        }
        return super.getPageTitle(position);
    }
}