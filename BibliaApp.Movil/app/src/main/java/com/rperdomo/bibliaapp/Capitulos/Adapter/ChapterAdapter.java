package com.rperdomo.bibliaapp.Capitulos.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rperdomo.bibliaapp.Capitulos.Model.List_Chapter_items_Card;
import com.rperdomo.bibliaapp.Capitulos.Model.chapter;
import com.rperdomo.bibliaapp.Libros.Adapter.BooksAdapter;
import com.rperdomo.bibliaapp.Libros.Model.List_Books_items_Card;
import com.rperdomo.bibliaapp.Libros.Model.bible_books;
import com.rperdomo.bibliaapp.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterAdapterViewHolder> {

    private ArrayList<List_Chapter_items_Card> ChapterList;
    private ArrayList<List_Chapter_items_Card> ChapterFilterList;
    private ChapterAdapter.OnItemClickListener mListener;


    public ChapterAdapter(ArrayList<List_Chapter_items_Card> itemList) {

        ChapterList = itemList;
        ChapterFilterList = new ArrayList<>(itemList);
    }

    public class ChapterAdapterViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;

        public ChapterAdapterViewHolder(View itemView, final ChapterAdapter.OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView1);
            mTextView1 = itemView.findViewById(R.id.textViewNum);

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

    public void setOnItemClickListener(ChapterAdapter.OnItemClickListener listener) {
        mListener = listener;
    }


    public void updateAnswers(ArrayList<chapter> items) {

        ArrayList<List_Chapter_items_Card> ListTemp = new ArrayList<List_Chapter_items_Card>();

       // Log.i(TAG, String.valueOf(items.size()));
        if (items.size() > 0)
        {
            for (int i=0; i<items.size(); i++)
            {
                ListTemp.add(new List_Chapter_items_Card(items.get(i).getNumChapter(),R.drawable.ic_book_libros));
               //  Log.i(TAG, String.valueOf(items.get(i).getNumChapter()));
            }
            ChapterList = ListTemp;
            ChapterFilterList = ChapterList;
            notifyDataSetChanged();
        }else
        {
            Log.e(TAG, "updateAnswers: error");
        }
    }

    public List_Chapter_items_Card getItem(int id) {
        return ChapterList.get(id);
    }

    @Override
    public ChapterAdapter.ChapterAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.menu.chaptercardview, parent, false);
        ChapterAdapter.ChapterAdapterViewHolder evh = new ChapterAdapter.ChapterAdapterViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ChapterAdapter.ChapterAdapterViewHolder holder, int position) {
        List_Chapter_items_Card currentItem = ChapterList.get(position);
        holder.mImageView.setImageResource(currentItem.getImagenBook());
        holder.mTextView1.setText("Capítulo "+String.valueOf(currentItem.getIdChapter()));
    }

    @Override
    public int getItemCount() {
        return ChapterList.size();
    }

}
