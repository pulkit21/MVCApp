package com.example.pulkit.mvcapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pulkit.mvcapp.R;
import com.example.pulkit.mvcapp.controller.CounterController;
import com.shipdream.lib.android.mvc.view.MvcFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pulkit on 9/24/15.
 */
public class FragmentB extends MvcFragment {

    @Inject
    private CounterController counterController;

    @Bind(R.id.fragment_b_counterDisplay)
    TextView display;

    @Bind(R.id.fragment_b_buttonIncrement)
    Button increment;

    @Bind(R.id.fragment_b_buttonDecrement)
    Button decrement;

    @Bind(R.id.fragment_b_buttonAutoIncrement)
    Button autoIncrement;

    private Handler handler;

    private ContinousCounter  incrementCounter;
    private ContinousCounter  decrementCounter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
    }


    @Override
    public void onViewReady(View view, Bundle savedInstanceState, Reason reason) {
        super.onViewReady(view, savedInstanceState, reason);
        ButterKnife.bind(this, view);
        increment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startContinuousIncrement();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        stopContinuousIncrement();
                        break;
                }
                return false;
            }
        });

        decrement.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startContinuousIncrement();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        stopContinuousIncrement();
                        break;
                }
                return false;
            }
        });

        autoIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(),Count)
            }
        });


        updateCountDisplay(counterController.getModel().getCount());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_b;
    }

    @Override
    public boolean onBackButtonPressed() {
        counterController.goBackToTheBasicView(this);
        return true;
    }

    private void startContinuousIncrement() {
        stopContinuousIncrement();
        incrementCounter = new ContinousCounter(true);
        incrementCounter.run();
    }

    private void startContinuousDecrement() {
        stopContinuousDecrement();
        decrementCounter = new ContinousCounter(false);
        decrementCounter.run();
    }

    private void stopContinuousIncrement() {
        if (incrementCounter != null) {
            incrementCounter.cancel();
        }
    }

    private void stopContinuousDecrement() {
        if (decrementCounter != null) {
            decrementCounter.cancel();
        }
    }

    private class ContinousCounter implements Runnable {
        private final boolean incrementing;
        private boolean canceled = false;
        private static final long INTERVAL = 200;

        public ContinousCounter(boolean incrementing) {
            this.incrementing = incrementing;
        }

        @Override
        public void run() {
            if (!canceled) {
                if (incrementing) {
                    counterController.increment(this);
                } else {
                    counterController.decrement(this);
                }
                handler.postDelayed(this, INTERVAL);
            }
        }
        private void cancel() {
            this.canceled = true;
        }
    }

    private void onEvent(CounterController.EventC2V.OnCounterUpdate event) {
        updateCountDisplay(event.getCount());
    }

    private void updateCountDisplay(int count) {
        display.setText(String.valueOf(count));
    }
}
