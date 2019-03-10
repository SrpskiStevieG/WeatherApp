package rs.mbrace.weatherapp.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.RecyclerView;
import rs.mbrace.weatherapp.R;
import rs.mbrace.weatherapp.model.json.Forecast;
import rs.mbrace.weatherapp.model.json.Weather;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.MyViewHolder> {

    private List<List<rs.mbrace.weatherapp.model.json.List>> weatherList;
    private Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView date;
        private ImageView icon;
        private TextView descriptionTv;
        private TextView tempMinMaxTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            icon = itemView.findViewById(R.id.icon);
            descriptionTv = itemView.findViewById(R.id.description);
            tempMinMaxTv = itemView.findViewById(R.id.temp_min_max);
        }

    }

    public ForecastAdapter(Context context, List<List<rs.mbrace.weatherapp.model.json.List>> list) {
        this.ctx = context;
        this.weatherList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);

        List<rs.mbrace.weatherapp.model.json.List> singleDay = weatherList.get(position);

        Calendar calendar = Calendar.getInstance();
        long timeInMillis = singleDay.get(0).getDt() * 1000L;
        calendar.setTimeInMillis(timeInMillis);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE    dd-MMM-yyyy", new Locale("rs"));
        String dateStr = sdf.format(calendar.getTime());

        holder.date.setText(dateStr);

        switch (singleDay.get(4).getWeather().get(0).getIcon()){
            case "01d":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_sun));
                break;
            case "01n":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_moon));
                break;
            case "02d":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_sun));
                break;
            case "02n":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_moon));
                break;
            case "03d":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud));
                break;
            case "03n":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud));
                break;
            case "04d":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud));
                break;
            case "04n":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud));
                break;
            case "09d":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_rain));
                break;
            case "09n":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_rain));
                break;
            case "10d":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_drizzle_sun));
                break;
            case "10n":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_drizzle_moon));
                break;
            case "11d":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_lightning));
                break;
            case "11n":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_lightning));
                break;
            case "13d":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_snow));
                break;
            case "13n":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_snow));
                break;
            case "50d":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_fog));
                break;
            case "50n":
                holder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_cloud_fog));
                break;
        }

        String desc = singleDay.get(4).getWeather().get(0).getMain();

        double minTemp;
        double maxTemp;

        List<Double> helperList = new ArrayList<>();
        for (rs.mbrace.weatherapp.model.json.List day : singleDay) {
            helperList.add(day.getMain().getTemp());
        }

        minTemp = Collections.min(helperList);
        maxTemp = Collections.max(helperList);

        String tempMinMax = tempCalc(minTemp) + "\u00b0C / " + tempCalc(maxTemp) + "\u00b0C";

        holder.descriptionTv.setText(desc);
        holder.tempMinMaxTv.setText(tempMinMax);
    }

    public void setList(List<List<rs.mbrace.weatherapp.model.json.List>> list){
        this.weatherList = list;
        notifyDataSetChanged();
    }

    private int tempCalc(double kelvin){
        return (int) (kelvin - 273.15);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
