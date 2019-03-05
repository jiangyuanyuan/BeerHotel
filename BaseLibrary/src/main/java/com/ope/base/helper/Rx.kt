package com.ope.base.helper

import com.ope.base.net.Result
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

fun <T> applyWidgetSchedulers(windowDuration: Long = 1L): ObservableTransformer<T, T> {
    return ObservableTransformer {
        it.throttleFirst(windowDuration, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> applyWidgetSchedulersForMilliSeconds(windowDuration: Long = 1L): ObservableTransformer<T, T> {
    return ObservableTransformer {
        it.throttleFirst(windowDuration, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> applySchedulers(): ObservableTransformer<T, T> {
    return ObservableTransformer {
        it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
fun <T> applySchedulersOnFlowable(): FlowableTransformer<T, T> {
    return FlowableTransformer {
        it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> applySchedulersOnSingle(): SingleTransformer<T, T> {
    return SingleTransformer {
        it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> T.asSuccess(): Result<T> {
    return Result.Success(this)
}

fun <T> Throwable.asError(): Result<T> {
    return Result.Error(this)
}

fun <T> Single<T>.repToResult(): Observable<Result<T>> {
    return this
            .toObservable()
            .map {
                it.asSuccess()
            }.onErrorReturn {
                when (it) {
                    is IOException,
                    is HttpException,
                    is SocketTimeoutException -> {
                        return@onErrorReturn it.asError()
                    }
                    else -> {
                        throw it
                    }
                }
            }
}