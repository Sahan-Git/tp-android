package com.sahan.androidv2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sahan.androidv2.R;
import com.sahan.androidv2.model.RendezVous;

import java.util.ArrayList;

/**
 * Aide Ã  l'affichage de la liste dans un listview
 */
public class RendezVousListViewAdapter extends BaseAdapter {

    private ArrayList<RendezVous> lesRendezVous;
    private LayoutInflater inflater;

    public RendezVousListViewAdapter(Context context, ArrayList<RendezVous> lesRendezVous){
        this.lesRendezVous = lesRendezVous;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lesRendezVous.size();
    }

    @Override
    public Object getItem(int position) {
        return lesRendezVous.get(position);
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
            convertView = inflater.inflate(R.layout.list_rendezvous, null);
            holder.lVtextDate = (TextView) convertView.findViewById(R.id.lVtextDate);
            holder.lVtextHeure = (TextView) convertView.findViewById(R.id.lVtxtHeure);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.lVtextDate.setText(lesRendezVous.get(position).getDate());
        holder.lVtextHeure.setText(lesRendezVous.get(position).getHeure());
        return convertView;
    }
    private class ViewHolder{
        TextView lVtextDate;
        TextView lVtextHeure;
    }
}