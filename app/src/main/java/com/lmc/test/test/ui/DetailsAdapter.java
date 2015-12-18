package com.lmc.test.test.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lmc.test.test.R;
import com.lmc.test.test.utility.Details;

import java.util.List;

/**
 * Created by lmarathchathu on 12/18/2015.
 */
public class DetailsAdapter extends BaseAdapter {
    private Context context;

    private List<Details> listDetails;

    public DetailsAdapter(Context context, List<Details> listPhonebook) {
        this.context = context;
        this.listDetails = listPhonebook;
    }

    @Override
    public int getCount() {
        return listDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return listDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Details entry = listDetails.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_lay_out, null);
        }
        TextView title = (TextView) view.findViewById(R.id.text_view_1);
        title.setText(entry.title);

        TextView description = (TextView) view.findViewById(R.id.text_view_2);
        description.setText(entry.description);


        return view;
    }
}
