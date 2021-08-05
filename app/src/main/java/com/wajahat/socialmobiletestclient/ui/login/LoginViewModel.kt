package com.wajahat.socialmobiletestclient.ui.login

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.wajahat.socialmobiletestclient.R
import com.wajahat.socialmobiletestclient.data.LoginCredentials
import com.wajahat.socialmobiletestserver.IPCLoginCredentials
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class LoginViewModel @Inject constructor(val app: Application) : AndroidViewModel(app),
    ServiceConnection {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private var iRemoteService: IPCLoginCredentials? = null

    fun connectToRemoteService() {
        val intent = Intent("loginCredentials")
        val pack = IPCLoginCredentials::class.java.`package`
        pack?.let {
            intent.setPackage(pack.name)
            app.applicationContext?.bindService(
                intent, this, Context.BIND_AUTO_CREATE
            )
        }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Toast.makeText(app, R.string.login_request_init, Toast.LENGTH_SHORT).show()

        // Gets an instance of the AIDL interface named IPCLoginCredentials,
        // which we can use to call on the service
        iRemoteService = IPCLoginCredentials.Stub.asInterface(service)
        iRemoteService?.setLoginCredentials(LoginCredentials(username.value!!, password.value!!))
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Toast.makeText(app, R.string.server_disconnected, Toast.LENGTH_SHORT).show()
        iRemoteService = null
    }
}