package com.herocheer.common.common_mvp.tool;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * @time:2018/4/9 at 10:25
 * @description: 统一销毁
 */
public class RxManager {

    public RxBus mRxBus = RxBus.$();
    private Map<String, Observable<?>> mObservables = new HashMap<>();// 管理观察源
    static CompositeDisposable composite = new CompositeDisposable();// 管理订阅者者


    public void on(String eventName, Consumer<Object> consumer) {
        Observable<?> mObservable = mRxBus.register(eventName);
        mObservables.put(eventName, mObservable);
        composite.add(mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, (e) -> e.printStackTrace()));

    }

    public static void add(Disposable m) {
        composite.add(m);
    }

    public void clear() {
        composite.clear();// 取消订阅
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet())
            mRxBus.unregister(entry.getKey(), entry.getValue());// 移除观察
    }

    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }
}