package com.zenolab.nav.grd.mp3simple.pickertimer.rxbus_to_fragment;

import android.text.format.DateFormat;

public class RxEvents  {

    private RxEvents() {

    }

    /*
    public static class Increment {
    }

    public static class Decrement {
    }

    public static class Reset {
    }
   */

    public static class Message {
        private String message;
        public Message(String message) {
            // this.message = message;
            this.message = DateFormat.format("MM/dd/yy h:mm:ss aa", System.currentTimeMillis()) + ": " + message;

        }
        public String getMessage() {
            return message;
        }
    }
}
