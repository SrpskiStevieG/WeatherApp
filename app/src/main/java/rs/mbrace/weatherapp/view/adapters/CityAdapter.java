package rs.mbrace.weatherapp.view.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import rs.mbrace.weatherapp.R;
import rs.mbrace.weatherapp.view.activities.MainActivity;

import static rs.mbrace.weatherapp.view.activities.MainActivity.TAG_CITY_CLICKED;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> implements Filterable {

    private View itemView;
    private List<String> cityList;
    private List<String> helperList;
    private Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView cityNameTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            cityNameTv = itemView.findViewById(R.id.city_tv);
        }

    }

    public CityAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);

        final String value = cityList.get(position);
        holder.cityNameTv.setText(value);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCity(value);
            }
        });
    }

    private void selectCity(String city) {
        Intent intent = new Intent(TAG_CITY_CLICKED);
        intent.putExtra("selectedCity", city);
        LocalBroadcastManager.getInstance(ctx).sendBroadcast(intent);
    }

    public void setList(List<String> list){
        this.cityList = list;
        helperList = new ArrayList<>(cityList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(helperList);
            }else{
                String pattern = constraint.toString().toLowerCase().trim();

                for (String city : helperList) {
                    if(city.toLowerCase().contains(pattern)){
                        filteredList.add(city);
                    }
                }
                if(filteredList.size() == 0){
                    filteredList.add("Search for " + constraint.toString());
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cityList.clear();
            cityList.addAll((Collection<? extends String>) results.values);
            notifyDataSetChanged();
        }
    };
}
