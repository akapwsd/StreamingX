package com.code.constants;

import io.agora.rtc2.video.BeautyOptions;

public class AgoraConstants
{
    public static final int BEAUTY_EFFECT_DEFAULT_CONTRAST = 1;
    public static final float BEAUTY_EFFECT_DEFAULT_LIGHTNESS = .7f;
    public static final float BEAUTY_EFFECT_DEFAULT_SMOOTHNESS = .5f;
    public static final float BEAUTY_EFFECT_DEFAULT_REDNESS = .1f;
    public static final float BEAUTY_EFFECT_DEFAULT_SHARPNESS = .1f;

    public static final BeautyOptions BEAUTY_OPTIONS = new BeautyOptions(BEAUTY_EFFECT_DEFAULT_CONTRAST, BEAUTY_EFFECT_DEFAULT_LIGHTNESS, BEAUTY_EFFECT_DEFAULT_SMOOTHNESS, BEAUTY_EFFECT_DEFAULT_REDNESS,BEAUTY_EFFECT_DEFAULT_SHARPNESS);

}
