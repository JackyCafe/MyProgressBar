package com.linyanheng.myprogressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pb1;
    private int progressnum = 0;
    private Handler handler = new Handler();
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        pb1.setVisibility(View.GONE);
        textView = (TextView) findViewById(R.id.tv);
    }

    public void dosomething(View view) {
        pb1.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressnum<100) {
                    progressnum += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(progressnum + "/" + pb1.getMax());
                        }
                    });


                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                while(progressnum == 100)
                {
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           pb1.setVisibility(View.GONE);
                           textView.setText("Done");
                       }
                   });

                }
            }

        }).start();
    }
}
