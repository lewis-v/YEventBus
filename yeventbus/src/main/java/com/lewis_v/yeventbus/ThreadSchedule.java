package com.lewis_v.yeventbus;

import android.os.Handler;
import android.os.Looper;

/**
 * auth: lewis-v
 * time: 2018/3/25.
 */

public class ThreadSchedule {

    public static Handler getMainHandle(){
        return new Handler(Looper.getMainLooper());
    }
}
