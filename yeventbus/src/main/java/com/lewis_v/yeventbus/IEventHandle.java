package com.lewis_v.yeventbus;

public interface IEventHandle {
    <T extends IEvent> void postEvent(YObservable observable, T data);
    <T extends IEvent> void postMainEvent(YObservable observable, T data);
    void destroy();
}
