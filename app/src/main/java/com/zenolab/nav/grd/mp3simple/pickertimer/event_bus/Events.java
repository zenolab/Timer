package com.zenolab.nav.grd.mp3simple.pickertimer.event_bus;

/**
 * Created by grd on 4/9/18.
 */

public class Events {

    // Event used to send message from fragment to activity.
    public static class FragmentActivityMessage {
        private String message;
        public FragmentActivityMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

    // Event used to send message from activity to fragment.
    public static class ActivityToFragmentMessage {
        private String message;
        public ActivityToFragmentMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

    // Event used to send message from activity to activity.
    public static class ActivityActivityMessage {
        private String message;
        public ActivityActivityMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

}
