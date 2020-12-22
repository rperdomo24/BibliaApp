package com.rperdomo.bibliaapp.Libros.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.rperdomo.bibliaapp.Libros.Model.List_Books_items_Card;
import com.rperdomo.bibliaapp.Libros.Model.bible_books;
import com.rperdomo.bibliaapp.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksAdapterViewHolder> implements Filterable {

    private ArrayList<List_Books_items_Card> BookList;
    private ArrayList<List_Books_items_Card> BookFilterList;
    private BooksAdapter.OnItemClickListener mListener;

    public BooksAdapter(ArrayList<List_Books_items_Card> itemList) {

        BookList = itemList;
        BookFilterList = new ArrayList<>(itemList);
    }

    public static  class  BooksAdapterViewHolder extends  RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextViewId;

        public BooksAdapterViewHolder(View itemView, final BooksAdapter.OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextViewId = itemView.findViewById(R.id.textViewId);

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

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(BooksAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public void updateAnswers(ArrayList<bible_books> items) {

        ArrayList<List_Books_items_Card> ListTemp = new ArrayList<List_Books_items_Card>();
        if (items.size() > 0)
        {
            for (int i=0; i<items.size(); i++)
            {
                ListTemp.add(new List_Books_items_Card(R.drawable.ic_book_libros, items.get(i).getIdBook() ,items.get(i).getName()));
               // Log.i(TAG, String.valueOf(items.get(i).getIdBook()));
            }
            BookList = ListTemp;
            BookFilterList = BookList;
            notifyDataSetChanged();
        }else
        {
            Log.e(TAG, "updateAnswers: error");
        }
    }



    public List_Books_items_Card getItem(int id) {
        return BookList.get(id);
    }

    @Override
    public BooksAdapter.BooksAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.menu.bookcardview, parent, false);
        BooksAdapter.BooksAdapterViewHolder evh = new BooksAdapter.BooksAdapterViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(BooksAdapter.BooksAdapterViewHolder holder, int position) {
        List_Books_items_Card currentItem = BookList.get(position);
        holder.mImageView.setImageResource(currentItem.getImagenBook());
        holder.mTextView1.setText(currentItem.getNameBook());
        holder.mTextViewId.setText(String.valueOf(currentItem.getIdBook()));
    }

    @Override
    public int getItemCount() {
        return BookList.size();
    }

    @Override
    public Filter getFilter()
    {
        return Filterbooks;
    }

    private Filter Filterbooks =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<List_Books_items_Card> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(BookFilterList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (List_Books_items_Card item : BookFilterList) {
                    if (item.getNameBook().toLowerCase().contains(filterPattern)) {
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
            BookList.clear();
            BookList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };



}
