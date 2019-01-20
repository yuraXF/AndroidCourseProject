package com.example.yura.todolist.mvvm.view.component;

import android.content.Context;
import android.util.AttributeSet;

import com.example.yura.todolist.PriorityTypeEnum;
import com.example.yura.todolist.R;

import androidx.appcompat.widget.AppCompatTextView;

public class MyView extends AppCompatTextView {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPriorityBackground(int priorityType){
        if(priorityType==PriorityTypeEnum.HIGH.ordinal()){
            setBackgroundResource(R.drawable.ic_filter_1_black_24dp);
        }else if(priorityType==PriorityTypeEnum.MIDDLE.ordinal()){
            setBackgroundResource(R.drawable.ic_filter_2_black_24dp);
        }else if(priorityType==PriorityTypeEnum.LOW.ordinal()){
            setBackgroundResource(R.drawable.ic_filter_3_black_24dp);
        }
    }
}
