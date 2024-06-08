package co.il.bulletdance;

import android.widget.TextView;

public class Timer extends Thread {

    public int timer;
    private TextView txt;
    private boolean threadRunning = true;
    private boolean isRunning = true;

    public Timer(TextView txt) {
        this.txt = txt;
        this.timer = 0;
    }


    // ... (Getters and setters for timer)

    @Override
    public void run() {
        super.run();

        while (threadRunning) {
            try {
                if (isRunning) {
                    timer++;
                    // Use runOnUiThread to update the TextView on the main thread
                    txt.post(new Runnable() {
                        @Override
                        public void run() {
                            txt.setText(String.valueOf(timer));
                        }
                    });
                    sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setTR(boolean b){this.threadRunning = b;}

    // ... (stopTimer and continueTimer methods)
}