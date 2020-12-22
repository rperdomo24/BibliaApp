package com.rperdomo.bibliaapp.Lecturas.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.rperdomo.bibliaapp.Lecturas.Model.List_Verses_items_Card;
import com.rperdomo.bibliaapp.Lecturas.Model.bible_verses;
import com.rperdomo.bibliaapp.Libros.Adapter.BooksAdapter;
import com.rperdomo.bibliaapp.Libros.Model.List_Books_items_Card;
import com.rperdomo.bibliaapp.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class VersesAdapter extends RecyclerView.Adapter<VersesAdapter.VersesAdapterViewHolder>{

    private ArrayList<List_Verses_items_Card> VerseList;
    private ArrayList<List_Verses_items_Card> VerseFilterList;
    private VersesAdapter.OnItemClickListener mListener;

    public VersesAdapter(ArrayList<List_Verses_items_Card> Listitems) {
        VerseList = Listitems;
        VerseFilterList =new ArrayList<>(Listitems);
    }

    public class VersesAdapterViewHolder extends  RecyclerView.ViewHolder{
        public TextView mTextIdVerView;
        public TextView mTextViewVerse;

        public VersesAdapterViewHolder(View itemView, final VersesAdapter.OnItemClickListener listener) {
            super(itemView);
            mTextIdVerView = itemView.findViewById(R.id.textViewIdVerse);
            mTextViewVerse = itemView.findViewById(R.id.textViewVerse);

            mTextViewVerse.setOnClickListener(new View.OnClickListener() {
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
    public void setOnItemClickListener(VersesAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public void updateAnswers(ArrayList<bible_verses> items) {

        ArrayList<List_Verses_items_Card> ListTemp = new ArrayList<List_Verses_items_Card>();
        if (items.size() > 0)
        {
            for (int i=0; i<items.size(); i++)
            {
                ListTemp.add(new List_Verses_items_Card(items.get(i).getText() ,items.get(i).getVerse()));
            }
            VerseList = ListTemp;
            VerseFilterList = VerseList;
            notifyDataSetChanged();
        }else
        {
            Log.e(TAG, "updateAnswers: error");
        }
    }

    public List_Verses_items_Card getItem(int id) {
        return VerseList.get(id);
    }

    @Override
    public VersesAdapter.VersesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.menu.versecardview, parent, false);
        VersesAdapter.VersesAdapterViewHolder evh = new VersesAdapter.VersesAdapterViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(VersesAdapter.VersesAdapterViewHolder holder, int position) {
        List_Verses_items_Card currentItem = VerseList.get(position);
        holder.mTextIdVerView.setText(String.valueOf(currentItem.getIdVerse()));
        holder.mTextViewVerse.setText(currentItem.getTextVerse());
    }

    @Override
    public int getItemCount() {
        return VerseList.size();
    }


}
