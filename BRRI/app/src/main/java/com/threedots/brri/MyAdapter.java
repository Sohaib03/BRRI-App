package com.threedots.brri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    List<RiceSpecies> riceSpeciesList;
    List<RiceSpecies> riceSpeciesListAll;
    Context context;
    public MyAdapter(Context context, List<RiceSpecies> riceSpeciesList) {
        this.context = context;
        this.riceSpeciesList = riceSpeciesList;
        this.riceSpeciesListAll = new ArrayList<>(riceSpeciesList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RiceSpecies rice = riceSpeciesList.get(position);
        holder.titleText.setText(rice.name);
        holder.cardId.setText("#" + String.valueOf(rice.id));

        if (rice.salinityScore == -1) holder.salinityScoreText.setText("N/A");
        else holder.salinityScoreText.setText(String.valueOf(rice.salinityScore));

        if (rice.submergenceScore == -1) holder.submergenceScoreText.setText("N/A");
        else holder.submergenceScoreText.setText(String.valueOf(rice.submergenceScore));

    }

    @Override
    public int getItemCount() {
        return riceSpeciesList.size();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RiceSpecies> filteredList = new ArrayList<>();
            if (constraint.toString().isEmpty()) {
                filteredList.addAll(riceSpeciesListAll);
            }
            else {
                for (RiceSpecies rice: riceSpeciesListAll) {
                    if (rice.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(rice);
                    }
                    else if (String.valueOf(rice.id).contains(constraint.toString())) {
                        filteredList.add(rice);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            riceSpeciesList.clear();
            riceSpeciesList.addAll((Collection<? extends RiceSpecies>) results.values);
            notifyDataSetChanged();

        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }

    public void sortBySubmergence(boolean reversed) {

        Comparator<RiceSpecies> comparator = new Comparator<RiceSpecies>() {
            @Override
            public int compare(RiceSpecies o1, RiceSpecies o2) {
                if (o1.submergenceScore == o2.submergenceScore) return o1.name.compareTo(o2.name);
                else if (o1.submergenceScore < o2.submergenceScore) return -1;
                else return 1;
            }
        };
        Comparator<RiceSpecies> reverseComparator = new Comparator<RiceSpecies>() {
            @Override
            public int compare(RiceSpecies o1, RiceSpecies o2) {
                if (o1.submergenceScore == o2.submergenceScore) return o1.name.compareTo(o2.name);
                else if (o1.submergenceScore < o2.submergenceScore) return 1;
                else return -1;
            }
        };



        if (reversed) Collections.sort(riceSpeciesList, reverseComparator);
        else Collections.sort(riceSpeciesList, comparator);
        notifyDataSetChanged();

    }

    public void filterRange(int lo, int hi, int factor) {
        List<RiceSpecies> filteredList = new ArrayList<>();
        if (factor == RiceSpecies.SUBMERGENCE) {
            for(RiceSpecies rice: riceSpeciesListAll) {
                if (rice.submergenceScore>=lo && rice.submergenceScore<=hi) {
                    filteredList.add(rice);
                }
            }
        }
        riceSpeciesList.clear();
        riceSpeciesList.addAll(filteredList);
        notifyDataSetChanged();
    }

    public void reverse() {
        Collections.reverse(riceSpeciesList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView salinityScoreText;
        TextView submergenceScoreText;
        TextView cardId;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.item_card_title);
            salinityScoreText = itemView.findViewById(R.id.card_salinity_score);
            submergenceScoreText = itemView.findViewById(R.id.card_submergence_score);
            cardId = itemView.findViewById(R.id.card_id_text);
        }
    }
}
