package com.example.pulkit.mvcapp.controller;

import com.example.pulkit.mvcapp.model.CounterModel;
import com.shipdream.lib.android.mvc.controller.BaseController;
import com.shipdream.lib.android.mvc.event.BaseEventC2V;

import java.util.Objects;

/**
 * Created by pulkit on 9/24/15.
 */
public interface CounterController extends BaseController<CounterModel> {

    void increment(Object  sender);

    void decrement(Object sender);

    String convertNumberToEnglish(int number);

    void goToAdvanceView(Object sender);

    void goBackToTheBasicView(Object sender);

    interface EventC2V {

        class OnCounterUpdate extends BaseEventC2V {

            private final int count;
            private final String countInEnglish;

            public OnCounterUpdate(Object sender, int count, String countInEnglish) {
                super(sender);
                this.count = count;
                this.countInEnglish = countInEnglish;
            }

            public int getCount(){
                return count;
            }

            public String getCountInEnglish() {
                return countInEnglish;
            }
        }
    }
}
