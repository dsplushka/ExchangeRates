package com.darushina.a0203exchangerates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Currency> {
    int listLayout;
    ArrayList<Currency> ratesList;
    Context context;

    public ListViewAdapter(Context context, int listLayout , int field, ArrayList<Currency> ratesList) {
        super(context, listLayout, field, ratesList);
        this.context = context;
        this.listLayout = listLayout;
        this.ratesList = ratesList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listViewItem = inflater.inflate(listLayout, null, false);
        TextView name = listViewItem.findViewById(R.id.textViewCharCode);
        TextView email = listViewItem.findViewById(R.id.textViewValue);
        name.setText(ratesList.get(position).getCharCode());
        email.setText(ratesList.get(position).getValue());
        return listViewItem;
    }
}
