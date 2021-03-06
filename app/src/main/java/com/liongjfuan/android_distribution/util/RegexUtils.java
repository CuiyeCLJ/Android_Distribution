package com.liongjfuan.android_distribution.util;

import java.util.regex.Pattern;

/**
 *
 * @author Lifu.Zheng
 * @date 2017.12.08
 */

public final class RegexUtils {

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     * @param mobile
     * 移动、联通、电信运营商的号码段
     * 移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     * 、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
     * 联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
     * 电信的号段：133、153、180（未启用）、189
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[3458]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }
}
