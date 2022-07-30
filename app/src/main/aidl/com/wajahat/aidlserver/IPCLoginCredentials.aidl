package com.wajahat.aidlserver;

import com.wajahat.aidlserver.data.LoginCredentials;

interface IPCLoginCredentials {

    void setLoginCredentials(in LoginCredentials credentials);
}