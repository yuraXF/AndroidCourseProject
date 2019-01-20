package com.example.yura.todolist.mvvm.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yura.todolist.mvvm.model.NoteModel;
import com.example.yura.todolist.mvvm.view.component.MyView;
import com.example.yura.todolist.R;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements ItemsAdapter {

    private List<NoteModel> arrayListNotes = Collections.emptyList();
    private Callback callback;

    public MyAdapter() {}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.recycle_item,viewGroup,false);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        MyView myView;
        TextView tv_title;
        TextView tv_description;
        Button btn_delete;
        public ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.priority_mainscreen);
            tv_title=itemView.findViewById(R.id.title_mainscreen);
            tv_description=itemView.findViewById(R.id.description_mainscreen);
            btn_delete=itemView.findViewById(R.id.btn_delete);
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.deleteCurrentNote(arrayListNotes.get(getAdapterPosition()));
                }
            });

            tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(callback !=null){
                        callback.showEditActivity(arrayListNotes.get(getAdapterPosition()));
                    }
                }
            });

            tv_description.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(callback !=null){
                        callback.showEditActivity(arrayListNotes.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void showEditActivity(NoteModel noteModel);
        void deleteCurrentNote(NoteModel noteModel);
    }
}
