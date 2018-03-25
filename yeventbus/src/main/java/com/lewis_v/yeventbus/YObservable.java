package com.lewis_v.yeventbus;

import java.util.Observable;

public class YObservable extends Observable {

    public <T extends IEvent> void postEvent(T data){
        setChanged();
        notifyObservers(data);
    }
}
