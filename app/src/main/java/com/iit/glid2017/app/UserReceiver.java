package com.iit.glid2017.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by slim on 11/04/17.
 */

public class UserReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("iit", "user present");
    }
}
