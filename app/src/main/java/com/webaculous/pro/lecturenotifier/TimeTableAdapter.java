package com.webaculous.pro.lecturenotifier;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farhan on 29-05-2017.
 */

public class TimeTableAdapter extends ArrayAdapter{
    List list = new ArrayList();
    public TimeTableAdapter(Context context,int resource) {
        super(context, resource);
    }


    public void add(TimeTable object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        TimeTableHolder timeTableHolder;
        int[] images={R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six};
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.listview_layout,parent,false);
            timeTableHolder = new TimeTableHolder();
            timeTableHolder.tx_time = (TextView) row.findViewById(R.id.txt_time);
            timeTableHolder.tx_lecture = (TextView) row.findViewById(R.id.txt_lecture);
            timeTableHolder.tx_teacher = (TextView) row.findViewById(R.id.txt_teacher);
            timeTableHolder.tx_room = (TextView) row.findViewById(R.id.txt_room);
            timeTableHolder.img_row = (ImageView) row.findViewById(R.id.sidelogo);
            row.setTag(timeTableHolder);


        }
        else
        {
            timeTableHolder = (TimeTableHolder) row.getTag();

        }

        TimeTable timeTable = (TimeTable) this.getItem(position);
        timeTableHolder.img_row.setImageResource(images[position]);
        timeTableHolder.tx_time.setText("Time: "+timeTable.getStart()+" - "+timeTable.getEnd());
        timeTableHolder.tx_lecture.setText("Lecture: "+timeTable.getLecture());
        timeTableHolder.tx_teacher.setText("Teacher: "+timeTable.getTeacher());
        timeTableHolder.tx_room.setText("Room: "+timeTable.getRoom());
        return row;
    }
    static class TimeTableHolder
    {
        TextView tx_time,tx_lecture,tx_teacher,tx_room;
        ImageView img_row;
    }
}
