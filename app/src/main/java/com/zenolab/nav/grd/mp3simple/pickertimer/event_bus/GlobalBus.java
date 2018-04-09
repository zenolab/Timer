package com.zenolab.nav.grd.mp3simple.pickertimer.event_bus;

/**
 * Created by grd on 4/9/18.
 */

import com.squareup.otto.Bus;

public class GlobalBus {

    private static Bus sBus;

    public static Bus getBus() {
        if (sBus == null)
            sBus = new Bus();
        return sBus;
    }


}