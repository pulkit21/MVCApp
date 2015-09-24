package com.example.pulkit.mvcapp.view;

import android.os.Bundle;
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
public class FragmentA extends MvcFragment {

    @Inject
    private CounterController counterController;
    @Bind(R.id.fragment_a_counterDisplay)
    TextView display;

    @Bind(R.id.fragment_a_buttonIncrement)
    Button increment;

    @Bind(R.id.fragment_a_buttonDecrement)
    Button decrement;

    @Bind(R.id.fragment_a_buttonShowAdvancedView)
    Button buttonShowAdvancedView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_a;
    }

    @Override
    public void onViewReady(View view, Bundle savedInstanceState, Reason reason) {
        super.onViewReady(view, savedInstanceState, reason);
        ButterKnife.bind(this,view);
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
