package com.android.asynctask19145369;

import android.os.AsyncTask;
import android.widget.Button;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

// first void because no input required; second void because progress is not published; string for result type
public class SimpleAsyncTask extends AsyncTask<ParamInfo, ProgressInfo, ResultInfo> {
    private final ProgressBar progressBar;
    private final TextView textViewInfo;
    private final Button buttonStart;

    private final int PROGRESS_MAX;
    private int workCount = 0;
    private long startTimeInMillis;

    public SimpleAsyncTask(ProgressBar progressBar, TextView textViewInfo,
                           Button buttonStart)  {
        this.progressBar = progressBar;
        this.textViewInfo = textViewInfo;
        this.buttonStart = buttonStart;
        this.PROGRESS_MAX = this.progressBar.getMax();
    }

    @Override
    protected void onPreExecute() {
        this.progressBar.setVisibility(ProgressBar.VISIBLE);
        this.textViewInfo.setText("Start...");
        this.buttonStart.setEnabled(false);
        this.startTimeInMillis = new Date().getTime();
    }

    @Override
    protected ResultInfo doInBackground(ParamInfo... params) {
        final int WORK_MAX = 100;

        while (this.workCount < WORK_MAX) {
            SystemClock.sleep(572); // 100 Milliseconds.
            this.workCount++;

            int progress = (this.workCount * PROGRESS_MAX) / WORK_MAX; // Progress value.
            int percent = (progress * 100) / PROGRESS_MAX;
            String info = "(" + percent +"%) - Working part " + this.workCount + " of " + WORK_MAX;

            ProgressInfo progressInfo = new ProgressInfo(progress, info);
            this.publishProgress(progressInfo); // Progress ...values
        }
        long finishTimeInMillis = new Date().getTime();
        long workTimeInMillis = finishTimeInMillis - this.startTimeInMillis;
        ResultInfo result = new ResultInfo(true, workTimeInMillis);
        return result;
    }

    @Override
    protected void onProgressUpdate(ProgressInfo... values) { // Progress ...values
        ProgressInfo progressInfo= values[0];

        int progress = progressInfo.getProgress();

        this.progressBar.setProgress(progress);
        this.textViewInfo.setText(progressInfo.getWorkingInfo());
    }

    @Override
    protected void onPostExecute(ResultInfo resultInfo) {
        super.onPostExecute(resultInfo);
        this.buttonStart.setEnabled(true);
        this.textViewInfo.setText(resultInfo.getMessage());
    }

}