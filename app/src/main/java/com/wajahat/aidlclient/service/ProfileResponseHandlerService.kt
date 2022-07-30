package com.wajahat.aidlclient.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.wajahat.aidlclient.IPCProfile
import com.wajahat.aidlclient.data.Profile
import com.wajahat.aidlclient.data.ProfileHolder

class ProfileResponseHandlerService : Service() {

    // AIDL - Binder object to pass to the client
    private val aidlBinder = object : IPCProfile.Stub() {

        // Is received as the response of the login API which is triggered in Server app
        override fun setLoginResponse(profile: Profile?) {
            // Set the singleton object's value so that it will be consumed by the fragment
            // to display profile
            ProfileHolder.profile = profile
        }
    }

    // Pass the binder object to clients so they can communicate with this service
    override fun onBind(intent: Intent?): IBinder {
        return aidlBinder
    }
}