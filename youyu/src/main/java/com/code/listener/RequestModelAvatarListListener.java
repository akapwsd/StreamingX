package com.code.listener;

import com.code.bean.ModelCoverListBean;

/**
 * RTC Anchor Avatar Resources interface request result callback
 *
 * @author chan
 * @version 1.0.5
 * @since 1.0.5
 * @since 6 May 2023
 * @since JDK8
 */
public interface RequestModelAvatarListListener {
    /**
     * The interface request is onResult
     *
     * @param data The parameters returned by the interface request successfully
     *             <ul>
     *             <li>{@link com.code.bean.ModelCoverListBean}
     *             </ul>
     */
    void onResult(ModelCoverListBean data);

    /**
     * Interface request failed
     *
     * @param code   Interface request failure error code
     * @param reason Interface request failed error reason
     */
    void onFailure(int code, String reason);
}