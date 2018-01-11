package com.kotlinpractice.api;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * Created by jifeng.liu on 18/1/11.
 */

public class Test {

    private ObservableTransformer aa(){
        return new ObservableTransformer(){

            @Override
            public ObservableSource apply(Observable upstream) {
                return null;
            }
        };
    }
}
