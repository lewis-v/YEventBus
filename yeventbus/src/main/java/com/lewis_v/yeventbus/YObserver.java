package com.lewis_v.yeventbus;

import java.util.Observable;

public abstract class YObserver<E extends IEvent> implements OnGetEvent<E> {

    @Override
    public void update(Observable o, Object arg) {
        try {
            onSuccess((E) arg);
        }catch (Exception e){
            onFail(e);
        }
    }
}
