package com.sahan.androidv2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sahan.androidv2.R;
import com.sahan.androidv2.model.Medicament;

import java.util.ArrayList;

/**
 * Aide Ã  l'affichage de la liste dans un listview
 */
public class MedicamentListViewAdapter extends BaseAdapter {

    private ArrayList<Medicament> lesMedicaments;
    private LayoutInflater inflater;

    public MedicamentListViewAdapter(Context context, ArrayList<Medicament> lesMedicaments){
        this.lesMedicaments = lesMedicaments;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lesMedicaments.size();
    }

    @Override
    public Object getItem(int position) {
        return lesMedicaments.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_medicaments, null);
            holder.txtNomCommercial = (TextView) convertView.findViewById(R.id.lVtextDate);
            holder.txtEffet = (TextView) convertView.findViewById(R.id.lVtxtHeure);
            holder.txtPrix = (TextView) convertView.findViewById(R.id.txtPrixEchant);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtNomCommercial.setText(lesMedicaments.get(position).getMNomCommercial());
        holder.txtEffet.setText(lesMedicaments.get(position).getMEffet());
        holder.txtPrix.setText(Double.toString(lesMedicaments.get(position).getMPrixEchant()));
        return convertView;
    }
    private class ViewHolder{
        TextView txtNomCommercial;
        TextView txtEffet;
        TextView txtPrix;

    }
}