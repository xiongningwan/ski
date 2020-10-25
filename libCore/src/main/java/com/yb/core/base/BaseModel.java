package com.yb.core.base;

import com.yb.core.net.ApiException;
import com.yb.core.net.ApiException_new;
import com.yb.core.net.HttpResult;
import com.yb.core.net.HttpResult_new;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class BaseModel {
    protected static final int TYPE_OBJECT = 0;
    protected static final int TYPE_LIST = 1;

    protected <T> Disposable toSubscribe(Single<T> o, Consumer<T> s, Consumer<Throwable> e) {
        Disposable disposable = o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s, e);

        return disposable;
    }

    protected <T> Disposable toSubscribe(Single<T> o, Consumer<T> s) {
        Disposable disposable = o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s, new BaseConsumer());

        return disposable;
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     *            <p>
     */
    public class HttpResultFunc<T> implements Function<HttpResult<T>, T> {
        int type;

        public HttpResultFunc() {
        }

        public HttpResultFunc(int type) {
            this.type = type;
        }

        @Override
        public T apply(HttpResult<T> tHttpResult) throws Exception {
            if (0 != tHttpResult.getCode()) {
                throw new ApiException(tHttpResult);
            } else {
                if (null == tHttpResult.getData()) {
                    if (type == TYPE_LIST) {
                        return (T) new ArrayList<>();
                    } else if (type == TYPE_OBJECT) {
                        return (T) new Object();
                    } else {
                        return (T) new Object();
                    }
                } else {
                    return tHttpResult.getData();
                }

            }
        }
    }

    public class HttpResultFunc_new<T> implements Function<HttpResult_new<T>, T> {
        int type;

        public HttpResultFunc_new() {
        }

        public HttpResultFunc_new(int type) {
            this.type = type;
        }

        @Override
        public T apply(HttpResult_new<T> tHttpResultNew) throws Exception {
            if (!tHttpResultNew.isStatus()) {
                throw new ApiException_new(tHttpResultNew);
            } else {
                if (null == tHttpResultNew.getData()) {
                    if (type == TYPE_LIST) {
                        return (T) new ArrayList<>();
                    } else if (type == TYPE_OBJECT) {
                        return (T) new Object();
                    } else {
                        return (T) new Object();
                    }
                } else {
                    return tHttpResultNew.getData();
                }

            }
        }
    }


}
