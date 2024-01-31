package com.code.youyu.api;

public class Constants {
    public static final String PONG = "816aee71";
    public static final String BAN_USER_ROOM = "ed6e3161";
    public static final String CLOSE_ROOM = "b108048f";
    public static final String MSG_RECORD_ACK = "b5ecf821";
    public static final String DEVICE_UPDATED = "907b7206";
    public static final String CONNECT_ERROR = "9c1c9375";
    public static final String MSG_SENT_ACK = "b838bcc1";
    public static final String CHANNEL_MSG_RECORD = "148f5df6";
    public static final String SHORT_MESSAGE = "e8e8c4b7";
    public static final String UPDATE_NEW_MESSAGES = "db78e11c";
    public static final String GET_DIFF_MSG_RECORD_ACK = "905c3244";
    public static final String CHAT_DIFF_MSG_ACK = "f454ab96";
    public static final String STATES_ACK = "366e727b";
    public static final String CHANNEL_MATCH = "807c0cc4";
    public static final String CHANNEL_SKIP = "deef91bb";
    public static final int VIDEO = 1;
    public static final int AUDIO = 0;

    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_PROTO = "application/protobuf";

    public static final int ACTIVE_TRIGGER = 1;
    public static final int PASSIVE_TRIGGER = 2;

    public static final int TIME_PATTERN = 0;
    public static final int FRACTION_MODE = 1;

    public static final int ROOM_STATE_ERROR = -2;

    public static final String AWS_HTTPS_HEAD = "https://ujoy-paas-formal-eks.s3.us-west-2.amazonaws.com/";
    public static final String Bucket = "ujoy-paas-formal-eks";
    public static final String Region = "us-west-2";
    public static final String AWS_KEY = "broadcaster/cover/";
    public static final int MSG_RECEIVER = 2;
    public static final int MSG_SENDER = 1;
    public static final int MSG_UNKNOWN = -1;
    public static final int AWS_UPLOAD = 1;
    public static final int AWS_DOWNLOAD = 2;
    public static final int AWS_REQUEST_TIMEOUT = -1;
    public static final int MSG_SEND_TEXT = 0;
    public static final int MSG_SEND_IMAGE = 1;
    public static final int MSG_SEND_VOICE = 2;

    public static final int SEND_SUCCESS = 2;
    public static final int SEND_FAIL = -1;
    public static final int SENDING = 3;
    public static final int SEND_TIMEOUT = 4;
    public static final int CHECK_DATA = 1;
    public static final int START_CHECK = 0;
    public static final int LIMIT = 1000;
}
