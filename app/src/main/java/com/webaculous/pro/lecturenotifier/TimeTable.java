package com.webaculous.pro.lecturenotifier;

/**
 * Created by Farhan on 29-05-2017.
 */

public class TimeTable {
    private String start,end,day,lecture,teacher,room;

    public TimeTable(String start,String end,String day,String lecture,String teacher,String room)
    {
        this.setDay(day);
        this.setEnd(end);
        this.setStart(start);
        this.setLecture(lecture);
        this.setTeacher(teacher);
        this.setRoom(room);

    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
