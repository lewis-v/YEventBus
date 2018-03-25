package com.lewis_v.yeventbus;

import java.util.Observable;

/**
 * 此事件在触发是会在主线程进行
 * auth: lewis-v
 * time: 2018/3/24.
 */

public abstract class YMainThreadObserver<E extends IEvent> implements OnGetEvent<E> {
    @Override
    public void update(final Observable o, final Object arg) {
        ThreadSchedule.getMainHandle().post(new Runnable() {
            @Override
            public void run() {
                try {
                    onSuccess((E) arg);
                }catch (Exception e){
                    onFail(e);
                }
            }
        });
    }
}
