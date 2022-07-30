package com.wajahat.aidlclient;

import com.wajahat.aidlclient.data.Profile;

interface IPCProfile {

    void setLoginResponse(in Profile response);
}