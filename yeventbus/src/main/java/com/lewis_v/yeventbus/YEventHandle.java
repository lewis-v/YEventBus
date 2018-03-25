package com.lewis_v.yeventbus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class YEventHandle implements IEventHandle {
    private ExecutorService executorServiceHandle;//处理线程池

    public YEventHandle() {
        init();
    }
    private void init(){
        executorServiceHandle = Executors.newFixedThreadPool(2*Runtime.getRuntime().availableProcessors());
    }

    /**
     * 发布消息
     * @param observable
     * @param data
     * @param <T>
     * @throws InterruptedException
     */
    @Override
    public <T extends IEvent> void postEvent(YObservable observable, T data) {
        handle(observable,data);
    }

    /**
     * 发布主线程处理消息
     * @param observable
     * @param data
     * @param <T>
     */
    @Override
    public <T extends IEvent> void postMainEvent(YObservable observable, T data) {
        handleInMain(observable,data);
    }

    /**
     * 处理
     * @param observable
     * @param data
     * @param <T>
     */
    private <T extends IEvent>  void handle(final YObservable observable, final T data){
        executorServiceHandle.execute(new Runnable() {
            @Override
            public void run() {
                if (observable != null) {
                    observable.postEvent(data);
                }
            }
        });
    }

    /**
     * 在主线程处理
     * @param observable
     * @param data
     * @param <T>
     */
    private <T extends IEvent>  void handleInMain(final YObservable observable, final T data){
        executorServiceHandle.execute(new Runnable() {
            @Override
            public void run() {
                if (observable != null) {
                    ThreadSchedule.getMainHandle().post(new Runnable() {
                        @Override
                        public void run() {
                            observable.postEvent(data);
                        }
                    });

                }
            }
        });
    }

    /**
     * 释放资源
     */
    @Override
    public void destroy() {
        executorServiceHandle.shutdownNow();
        executorServiceHandle = null;
    }


}
