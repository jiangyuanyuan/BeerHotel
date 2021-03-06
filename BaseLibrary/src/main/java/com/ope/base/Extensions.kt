package com.ope.base

import android.content.res.Resources
import android.widget.ImageView
import com.ope.base.DEBUG_API_BASE_URL
import com.ope.base.KEY_TOKEN
import com.ope.base.R
import com.ope.base.glide.GlideApp
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions

import com.orhanobut.hawk.Hawk
import com.uber.autodispose.*
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

fun ImageView.loadFromUrl(imageUrl: String? = "", placeHolder: Int = R.drawable.abc_ab_share_pack_mtrl_alpha) {
    GlideApp.with(this.context)
            .load(getGlideUrl(imageUrl))
            .placeholder(placeHolder)
            .fitCenter()
            .into(this)
}

/**
 * 毛玻璃
 */
fun ImageView.blur(imageUrl: String? = "", placeHolder: Int = R.drawable.abc_ab_share_pack_mtrl_alpha) {
    GlideApp.with(this.context)
            .load(getGlideUrl(imageUrl))
            .placeholder(placeHolder)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(1, 100)))
            .into(this)
}

/**
 * 圆角
 *
 * redius:传dp值
 */
fun ImageView.corner(imageUrl: String? = "", redius: Int = 8, placeHolder: Int = R.drawable.abc_ab_share_pack_mtrl_alpha) {
    GlideApp.with(this.context)
            .load(getGlideUrl(imageUrl))
            .placeholder(placeHolder)
            .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation((Resources.getSystem().displayMetrics.density * redius).toInt(), 0,
                    RoundedCornersTransformation.CornerType.ALL)))
            .into(this)
}

/**
 * 圆形图
 */
fun ImageView.circle(imageUrl: String? = "", placeHolder: Int = R.drawable.abc_ab_share_pack_mtrl_alpha) {
    GlideApp.with(this.context)
            .load(getGlideUrl(imageUrl))
            .placeholder(placeHolder)
            .apply(RequestOptions.noTransformation().circleCrop())
            .into(this)
}

fun getGlideUrl(imageUrl: String?): GlideUrl = GlideUrl(if (BuildConfig.DEBUG) DEBUG_API_BASE_URL + "coron-api/" + imageUrl else DEBUG_API_BASE_URL + "coron-api/" + imageUrl, LazyHeaders.Builder().addHeader("Authorization", Hawk.get<String>(KEY_TOKEN)).build())



/**
 * 校验是否是电话号码
 */
fun String.isMobileNO():Boolean{
    val telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$"
    if (this.isEmpty()){
        return false
    }else{
        return this.matches(Regex(telRegex))
    }
}


private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = { RxJavaPlugins.onError(OnErrorNotImplementedException(it)) }
private val onCompleteStub: () -> Unit = {}

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun <T : Any> ObservableSubscribeProxy<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
): Disposable {
    return if (onError === onErrorStub && onComplete === onCompleteStub) {
        subscribe(onNext)
    } else {
        subscribe(onNext, onError, onComplete)
    }
}

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun <T : Any> FlowableSubscribeProxy<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
): Disposable {
    return if (onError === onErrorStub && onComplete === onCompleteStub) {
        subscribe(onNext)
    } else {
        subscribe(onNext, onError, onComplete)
    }
}

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun <T : Any> SingleSubscribeProxy<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onSuccess: (T) -> Unit = onNextStub
): Disposable {
    return if (onError === onErrorStub) {
        subscribe(onSuccess)
    } else {
        subscribe(onSuccess, onError)
    }
}

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun <T : Any> MaybeSubscribeProxy<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onSuccess: (T) -> Unit = onNextStub
): Disposable {
    return if (onError === onErrorStub && onComplete === onCompleteStub) {
        subscribe(onSuccess)
    } else {
        subscribe(onSuccess, onError, onComplete)
    }
}


/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun CompletableSubscribeProxy.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable {
    return if (onError === onErrorStub) {
        subscribe(onComplete)
    } else {
        subscribe(onComplete, onError)
    }
}


