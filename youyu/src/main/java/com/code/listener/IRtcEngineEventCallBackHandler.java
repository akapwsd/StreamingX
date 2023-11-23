package com.code.listener;

import com.code.bean.MsgBean;

import io.agora.rtc2.IRtcEngineEventHandler;
import uyujoy.com.api.channel.frontend.ChannelImform;

/**
 * RTC call event monitoring callback
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK8
 */
public abstract class IRtcEngineEventCallBackHandler {
    /**
     * Local join room success receipt
     *
     * @param channel The channel number of the room to join
     * @param uid     The uid of the person who joined the room
     * @param elapsed The elapsed time from the local call to joinChannel to the occurrence of this event
     */
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {

    }

    /**
     * Received the remote video and completed the decoding callback
     *
     * @param uid     User ID, specifying which user's video stream it is.
     * @param width   Video stream width (px).
     * @param height  Video stream height (px).
     * @param elapsed The elapsed time from the local call to joinChannel to the occurrence of this event
     */
    public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {

    }

    /**
     * Callback when the remote user leaves the current channel
     * <p>
     * Users leave a channel for two reasons:
     * <ul>
     * <li> Leave normally: the remote user will send a message like "goodbye". After receiving this message, it is determined that the user has left the channel.
     * <li> Timeout drop: within a certain period of time (20 seconds)
     * </ul>
     *
     * @param uid    ID of the offline user
     * @param reason The reason why the remote user goes offline:
     *               <ul>
     *               <li> USER_OFFLINE_QUIT(0):
     *               The user voluntarily leaves. Users who leave the channel at this point send a message like "goodbye".
     *               Upon receiving this message, the SDK determines that the user leaves the channel.
     *               <li>USER_OFFLINE_DROPPED(1):
     *               The SDK judged that the remote user was dropped due to timeout due to the failure to receive the other party's data packets for a long time.
     *               Note: This judgment may be wrong when the network connection is unstable.
     *               It is recommended to use the real-time messaging SDK for reliable disconnection detection.
     *               <li>USER_OFFLINE_BECOME_AUDIENCE (2):
     *               The user's role is switched from host to audience.
     *               </ul>
     */
    public void onUserOffline(int uid, int reason) {

    }

    /**
     * The remote user joins the current channel callback.
     * <p>
     * This callback will be triggered when:
     * <ul>
     * <li> A remote user joins a channel
     * <li> Remote user rejoins channel after network outage
     * </ul>
     *
     * @param uid     The remote user ID of the newly joined channel
     * @param elapsed The elapsed time from the local call to joinChannel to the occurrence of this event
     */
    public void onUserJoined(int uid, int elapsed) {
    }

    public void onFacePositionChanged(int imageWidth, int imageHeight, IRtcEngineEventHandler.AgoraFacePositionInfo[] agoraFacePositionInfos) {

    }

    /**
     * The user is forcibly kicked out of the room
     * <p>
     * Reasons for being forcibly kicked out of the room:
     * <ul>
     * <li> The token expires and is not refreshed
     * <li> Disconnected from the signaling server
     * </ul>
     *
     * @param uid    The uid of the proposed room user
     * @param reason The reason for being offered a room
     */
    public void banRoom(String uid, int reason) {

    }

    /**
     * chat room is closed
     *
     * @param reason The reason why the chat room is closed
     */
    public void closeRoom(int reason) {

    }

    public void onConnectionStateChanged(int state, int reason) {

    }

    public void receiveMsg(MsgBean msg) {

    }

    public void receiveMatch(ChannelImform.channelMatched channelMatched) {

    }

    public void receiveSkip(String channelId) {

    }

    public void deviceUpdated(String ip) {

    }

    public void connectError(int code,String reason) {

    }
}
