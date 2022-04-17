package com.android.asynctask19145369;

public class ResultInfo {
    private boolean completed;
    private long workTimeInMillis;

    public ResultInfo(boolean completed, long workTimeInMillis) {
        this.completed = completed;
        this.workTimeInMillis = workTimeInMillis;
    }

    public boolean isCompleted() {
        return completed;
    }

    public long getWorkTimeInMillis() {
        return workTimeInMillis;
    }

    public String getMessage() {
        if(this.completed)  {
            return "Done!";
        }
        return "Failed or cancelled";
    }
}