package com.rperdomo.bibliaapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rperdomo.bibliaapp.Model.Librositem;

import java.util.ArrayList;
import java.util.List;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.LibrosViewHolder> implements Filterable {

private ArrayList<Librositem> ListaLibros;
private ArrayList<Librositem> ListaFullLibros;
private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static  class  LibrosViewHolder extends  RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public LibrosViewHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public LibrosAdapter(ArrayList<Librositem> itemList) {

        ListaLibros = itemList;
        ListaFullLibros = new ArrayList<>(itemList);
    }

    Librositem getItem(int id) {
        return ListaLibros.get(id);
    }

  @Override
    public LibrosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.librositem, parent, false);
        LibrosViewHolder evh = new LibrosViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(LibrosViewHolder holder, int position) {
        Librositem currentItem = ListaLibros.get(position);
        holder.mImageView.setImageResource(currentItem.getImageitem());
        holder.mTextView1.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return ListaLibros.size();
    }

    @Override
    public Filter getFilter()
    {
        return FiltarLibros;
    }
    private Filter FiltarLibros =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Librositem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(ListaFullLibros);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Librositem item : ListaFullLibros) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ListaLibros.clear();
            ListaLibros.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
