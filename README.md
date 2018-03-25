# YEventBus
Y事件总线:基于java的Observe和Observable实现的事件总线

# 导入依赖

Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
 Add the dependency
```
	dependencies {
	        compile 'com.github.lewis-v:YEventBus:1.0.0'
	}
```

# 使用方式
定义事件类TestEvent2继承于IEvent,并注册事件
```
YEventBus.getInstance().subscriber(TestEvent2.class, new YObserver<TestEvent2>() {//订阅事件,处理的所在的线程与分发的线程一致
            @Override
            public void onSuccess(TestEvent2 event) {
                Log.i(TAG,event.toString());
            }

            @Override
            public void onFail(Exception e) {
                Log.e(TAG,e.getMessage());
            }
        });
        
YEventBus.getInstance().subscriber(TestEvent.class, new YMainThreadObserver<TestEvent>() {//订阅事件,会在主线程中处理
            @Override
            public void onSuccess(TestEvent event) {
                Log.i(TAG,event.toString());
            }

            @Override
            public void onFail(Exception e) {
                Log.e(TAG,e.getMessage());
            }
        });
```
发布事件
```
 YEventBus.getInstance().postMainEvent(TestEvent.class,new TestEvent(TAG));//发布在主线程分发的事件
 
 YEventBus.getInstance().postEvent(TestEvent.class,new TestEvent(TAG));//发布在子线程分发的事件
```
取消订阅
```
YEventBus.getInstance().unSubscriber(TestEvent.class,observer);//取消某事件下的某个订阅者的订阅
YEventBus.getInstance().unSubscriberEvent(TestEvent.class);//取消TestEvent整个系列事件的订阅
YEventBus.getInstance().unSubscriberAll();//取消所有事件的订阅
```
