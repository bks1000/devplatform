package com.june.core.utils;

/**
 * Created by lenovo on 2017/8/23.
 */
public class Common {
    /**
     * 截取byte[]
     * @param src
     * @param begin
     * @param count
     * @return
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

    /**
     * 将html转换为html标签
     * @param html
     * @return
     */
    public static String htmlDecode(String html){
        if ("".equals(html)){
            return "";
        }
        String str = html.replace("&amp;","&");
        str = str.replace("&lt;","<");
        str = str.replace("&gt;",">");
        str = str.replace("&quot;","\"");
        return str;
    }
}
