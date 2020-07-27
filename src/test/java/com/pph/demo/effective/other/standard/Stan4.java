package com.pph.demo.effective.other.standard;

import java.util.Objects;

/**
 * @Author: PPH
 * @date 2019-07-25 08:45
 * @Description: 明智审慎的使用延迟初始化
 */
public class Stan4 {

    private String str;

    /**
     * 如果您使用延迟初始化来取代初始化的循环(circularity)，请使用同步访问器 ，因为它是最简单、最清晰的替 代方法:
     *
     * @return
     */
    private synchronized String getStr() {
        if (Objects.isNull(str))
            str = "str";
        return str;
    }

    private static final String str2 = "str2";

    /////////////////////////////////////////////////////////////

    /**
     * 如果需要在静态字段上使用延迟初始化来提高性能，使用 lazy initialization holder class 模式。
     * 这个用法可保证 一个类在使用之前不会被初始化 [JLS, 12.4.1]。它是这样的:
     *
     * @return
     */
    private static String getStr2() {
        return Stan4.str2;
    }

    /////////////////////////////////////////////////////////////

    private volatile String str3;

    /**
     * 如果需要使用延迟初始化来提高实例字段的性能，请使用双重检查模式。
     *
     * @return
     */
    private String getStr3() {
        String result = str3;

        if (Objects.isNull(result))
            synchronized (this) {
                if (Objects.isNull(str3))
                    str3 = result = "str3";
            }

        return result;
    }

    /////////////////////////////////////////////////////////////

    private volatile String str4;

    /**
     * 「单检查」模式。
     *
     * @return
     */
    private String getStr4() {
        String result = str4;

        if (Objects.isNull(result))
            str4 = result = "str4";

        return result;
    }
}
