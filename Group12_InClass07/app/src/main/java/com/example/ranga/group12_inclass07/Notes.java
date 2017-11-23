package com.example.ranga.group12_inclass07;

/**
 * Created by ranga on 2/27/2017.
 */

public class Notes {

    private long _id;
    private String noteText,priority,updateTime2,status;
    boolean statusNew;

    public boolean isStatusNew() {
        return statusNew;
    }

    public void setStatusNew(boolean statusNew) {
        this.statusNew = statusNew;
    }

    public Notes(String noteText, String priority, String status, String updateTime) {
        this.noteText = noteText;
        this.priority = priority;
        this.status = status;
        this.updateTime2 = updateTime;
    }

    public Notes()
    {

    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime2;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime2 = updateTime;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "_id=" + _id +
                ", noteText='" + noteText + '\'' +
                ", priority='" + priority + '\'' +
                ", updateTime='" + updateTime2 + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
