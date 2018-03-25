package com.lewis_v.yeventbus;

public class YEventBus {
    private YObservableManager manager;
    private static final class Holder {
        private final static YEventBus instance = new YEventBus();
    }

    private YEventBus() {
        manager = new YObservableManager();
    }

    public static YEventBus getInstance(){
        return Holder.instance;
    }

    public <T extends IEvent> void subscriber(Class<T> event,OnGetEvent<T> observer){
        manager.subscriber(event,observer);
    }

    public <T extends IEvent> void postEvent(Class<T> event,T data){
        manager.postEvent(event,data);
    }

    public <T extends IEvent> void postMainEvent(Class<T> event,T data){
        manager.postMainEvent(event,data);
    }


    public <T extends IEvent> void unSubscriber(Class<T> event,YObserver<T> observer){
        manager.unSubscriber(event,observer);
    }

    public void unSubscriberEvent(Class<? extends IEvent> event){
        manager.unSubscriberEvent(event);
    }

    public void unSubscriberAll(){
        manager.unSubscriberAll();
    }

    public void destroy(){
        manager.destroy();
    }
}
