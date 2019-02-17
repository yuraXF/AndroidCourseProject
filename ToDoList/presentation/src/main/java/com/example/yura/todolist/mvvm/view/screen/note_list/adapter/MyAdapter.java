package com.example.yura.todolist.mvvm.view.screen.note_list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.data.mapper.DateMapper;
import com.example.yura.todolist.mvvm.model.NoteModel;
import com.example.yura.todolist.mvvm.view.widget.MyView;
import com.example.yura.todolist.R;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements ItemsAdapter {

    private List<NoteModel> arrayListNotes = Collections.emptyList();
    private Callback callback;
    private DateMapper dateMapper;

    public MyAdapter() {
        dateMapper=new DateMapper();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_item, viewGroup, false);
        return new MyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.myView.setPriorityBackground(arrayListNotes.get(i).getPriority());
        viewHolder.tv_title.setText(arrayListNotes.get(i).getTitle());
        if (arrayListNotes.get(i).getDescription().toCharArray().length <= 80) {
            viewHolder.tv_description.setText(arrayListNotes.get(i).getDescription().toCharArray(), 0, arrayListNotes.get(i).getDescription().toCharArray().length);
        } else {
            viewHolder.tv_description.setText(arrayListNotes.get(i).getDescription().toCharArray(), 0, 80);
        }
        viewHolder.tv_edit_date.setText(arrayListNotes.get(i).getEditDate());
        viewHolder.tv_end_date.setText(arrayListNotes.get(i).getEndDate());
        Date current_date=new Date();
        if (!arrayListNotes.get(i).getEndDate().isEmpty()) {
            try {
                Date end_date = dateMapper.transformToDate(arrayListNotes.get(i).getEndDate());
                if (end_date.compareTo(current_date)==-1){
                    viewHolder.cardView.setCardBackgroundColor(viewHolder.cardView.getContext().getResources().getColor(R.color.colorRedLight));
                }else{
                    viewHolder.cardView.setCardBackgroundColor(null);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return arrayListNotes.size();
    }

    @Override
    public void setItems(List items) {
        this.arrayListNotes = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MyView myView;
        TextView tv_title;
        TextView tv_description;
        Button btn_delete;
        TextView tv_edit_date;
        TextView tv_end_date;
        CardView cardView;
        LinearLayoutCompat recycleNoteDataLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.priority_mainscreen);
            tv_title = itemView.findViewById(R.id.title_mainscreen);
            tv_description = itemView.findViewById(R.id.description_mainscreen);
            tv_edit_date=itemView.findViewById(R.id.edit_date);
            tv_end_date=itemView.findViewById(R.id.end_date);
            cardView=itemView.findViewById(R.id.card_view_item);
            recycleNoteDataLayout=itemView.findViewById(R.id.recycle_note_data_layout);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        callback.deleteCurrentNote(arrayListNotes.get(getAdapterPosition()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });

            View.OnClickListener onItemClickListener=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null) {
                        callback.showEditFragment(arrayListNotes.get(getAdapterPosition()));
                    }
                }
            };

            recycleNoteDataLayout.setOnClickListener(onItemClickListener);

        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void showEditFragment(NoteModel noteModel);

        void deleteCurrentNote(NoteModel noteModel) throws ParseException;
    }

}
