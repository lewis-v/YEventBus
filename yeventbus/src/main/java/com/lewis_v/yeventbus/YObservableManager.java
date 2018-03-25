package com.lewis_v.yeventbus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class YObservableManager {
    private ConcurrentHashMap<Class,YObservable> mObservableMap;
    private IEventHandle handle;


    public YObservableManager() {
        mObservableMap = new ConcurrentHashMap<>();
        init();
    }

    public YObservableManager(ConcurrentHashMap<Class, YObservable> mObservableMap) {
        this.mObservableMap = mObservableMap;
        init();
    }

    public void init(){
        handle = new YEventHandle();
    }

    /**
     * 设置自定义的事件分发处理
     * @param handle
     */
    public void setHandle(IEventHandle handle) {
        this.handle = handle;
    }

    /**
     * 发布消息
     * @param event
     * @param <T>
     */
    public <T extends IEvent> void postEvent(Class<T> event, T data){
        YObservable observables = mObservableMap.get(event);
        if (handle == null){
            init();
        }
        handle.postEvent(observables,data);
    }

    /**
     * 发布主线程消息
     * @param event
     * @param <T>
     */
    public <T extends IEvent> void postMainEvent(Class<T> event, T data){
        YObservable observables = mObservableMap.get(event);
        if (handle == null){
            init();
        }
        handle.postMainEvent(observables,data);
    }

    /**
     * 订阅事件
     * @param event
     * @param observer
     * @param <T>
     */
    public <T extends IEvent> void subscriber(Class<T> event, OnGetEvent<T> observer){
        if (mObservableMap.containsKey(event)){
            mObservableMap.get(event).addObserver(observer);
        }else {
            YObservable observable = new YObservable();
            observable.addObserver(observer);
            mObservableMap.put(event, observable);
        }
    }

    /**
     * 解除订阅
     * @param event
     * @param observer
     * @param <T>
     */
    public <T extends IEvent> void unSubscriber(Class<T> event, YObserver<T> observer){
        if (mObservableMap.containsKey(event)){
            mObservableMap.get(event).deleteObserver(observer);
        }
    }

    /**
     * 解除一个事件系列的订阅
     * @param event
     */
    public void unSubscriberEvent(Class<? extends IEvent> event){
        if (mObservableMap.containsKey(event)) {
            mObservableMap.get(event).deleteObservers();
            mObservableMap.remove(event);
        }
    }

    /**
     * 解除所有事件订阅
     */
    public void unSubscriberAll(){
        for (Map.Entry<Class,YObservable> entry : mObservableMap.entrySet()){
            YObservable value = entry.getValue();
            if (value != null){
                value.deleteObservers();
            }
        }
        mObservableMap.clear();
    }

    /**
     * 释放资源
     */
    public void destroy(){
        handle.destroy();
        handle = null;
        unSubscriberAll();
    }
}
