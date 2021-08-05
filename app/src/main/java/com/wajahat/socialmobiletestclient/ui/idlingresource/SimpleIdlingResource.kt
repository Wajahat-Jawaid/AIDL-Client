package com.wajahat.socialmobiletestclient.ui.idlingresource

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A very simple implementation of [IdlingResource].
 */
class SimpleIdlingResource(private val resName: String) : IdlingResource {

    @Volatile
    private var callback: IdlingResource.ResourceCallback? = null
    private val isIdleNow = AtomicBoolean(false)

    override fun getName() = resName
    override fun isIdleNow() = isIdleNow.get()

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

    fun reset() {
        isIdleNow.set(false)
    }

    fun setIdleState(isIdleNow: Boolean) {
        this.isIdleNow.set(isIdleNow)
        if (isIdleNow && callback != null) {
            callback!!.onTransitionToIdle()
        }
    }
}