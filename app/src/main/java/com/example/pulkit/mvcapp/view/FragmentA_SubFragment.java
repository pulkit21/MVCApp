package com.example.pulkit.mvcapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pulkit.mvcapp.R;
import com.example.pulkit.mvcapp.controller.CounterController;
import com.shipdream.lib.android.mvc.view.MvcFragment;

import javax.inject.Inject;

/**
 * Created by pulkit on 9/24/15.
 */
public class FragmentA_SubFragment extends MvcFragment {

    @Inject
    private CounterController counterController;

    private TextView tvCountInEnglish;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_a_sub;
    }

    @Override
    public void onViewReady(View view, Bundle savedInstanceState, Reason reason) {
        super.onViewReady(view, savedInstanceState, reason);
        tvCountInEnglish = (TextView) view.findViewById(R.id.fragment_a_sub_countInEnglish);
        String text = counterController.convertNumberToEnglish(counterController.getModel().getCount());
        tvCountInEnglish.setText(text);
    }

    private void onEvent(CounterController.EventC2V.OnCounterUpdate event) {
        tvCountInEnglish.setText(event.getCountInEnglish());
    }



}
