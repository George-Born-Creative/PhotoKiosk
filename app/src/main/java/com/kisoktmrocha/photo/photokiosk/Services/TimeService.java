package com.kisoktmrocha.photo.photokiosk.Services;

import java.util.Calendar;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.view.View;
import android.widget.TextView;

import com.kisoktmrocha.photo.photokiosk.R;

/**
 * Created by tmrocha on 11/11/2016.
 */

public class TimeService {

    private TextView tvHour, tvMinute, tvDay, tvMonth, tvYear;
    private Handler handler;
    private Clock clock;
    final private static int DELAY = 1000;

    public TimeService(final View view){
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        tvHour = (TextView) view.findViewById(R.id.tvHour);
        tvMinute = (TextView) view.findViewById(R.id.tvMinute);
        tvDay = (TextView) view.findViewById(R.id.tvDay);
        tvMonth = (TextView) view.findViewById(R.id.tvMonth);
        tvYear = (TextView) view.findViewById(R.id.tvYear);
        handler = new Handler(Looper.getMainLooper());
        clock = new Clock();
        handler.postDelayed(clock, 0);
    }


    private class Clock implements Runnable {

        @Override
        public void run() {
            Calendar calendar = Calendar.getInstance();
            tvHour.setText(String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)));
            tvMinute.setText(String.format("%02d", calendar.get(Calendar.MINUTE)));
            tvDay.setText(""+calendar.get(Calendar.DAY_OF_MONTH));
            tvMonth.setText(getMonth(calendar.get(Calendar.MONTH)));
            tvYear.setText(""+calendar.get(Calendar.YEAR));
            handler.postDelayed(clock, DELAY);
        }

        private String getMonth(int month){
            switch (month){
                case 0: return "JAN";
                case 1: return "FEB";
                case 2: return "MAR";
                case 3: return "ABR";
                case 4: return "MAI";
                case 5: return "JUN";
                case 6: return "JUL";
                case 7: return "AGO";
                case 8: return "SET";
                case 9: return "OUT";
                case 10: return "NOV";
                case 11: return "DEZ";
            }
            return "XXX";
        }


    }

}
