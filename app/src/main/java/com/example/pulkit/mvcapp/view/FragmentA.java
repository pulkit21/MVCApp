package com.example.pulkit.mvcapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pulkit.mvcapp.R;
import com.example.pulkit.mvcapp.controller.CounterController;
import com.shipdream.lib.android.mvc.view.MvcFragment;

import javax.inject.Inject;

/**
 * Created by pulkit on 9/24/15.
 */
public class FragmentA extends MvcFragment {

    @Inject
    private CounterController counterController;

    private TextView display;
    private Button increment;
    private Button decrement;
    private Button buttonShowAdvancedView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_a;
    }

    @Override
    public void onViewReady(View view, Bundle savedInstanceState, Reason reason) {
        super.onViewReady(view, savedInstanceState, reason);
        display = (TextView) view.findViewById(R.id.fragment_a_counterDisplay);
        increment = (Button) view.findViewById(R.id.fragment_a_buttonIncrement);
        decrement = (Button) view.findViewById(R.id.fragment_a_buttonDecrement);
        buttonShowAdvancedView = (Button) view.findViewById(R.id.fragment_a_buttonShowAdvancedView);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterController.increment(v);
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterController.decrement(v);
            }
        });

        buttonShowAdvancedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterController.goToAdvanceView(v);
            }
        });

        if (reason == Reason.FIRST_TIME) {
            FragmentA_SubFragment fragmentA_subFragment = new FragmentA_SubFragment();
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_a_anotherFragmentContainer, fragmentA_subFragment).commit();
        }



        updateCounterDisplay(counterController.getModel().getCount());
    }

    @Override
    protected void onPoppedOutToFront() {
        super.onPoppedOutToFront();
        updateCounterDisplay(counterController.getModel().getCount());
    }

    private void onEvent(CounterController.EventC2V.OnCounterUpdate event) {
        updateCounterDisplay(event.getCount());
    }

    private void  updateCounterDisplay(int count) {
        display.setText(String.valueOf(count));
    }
}
