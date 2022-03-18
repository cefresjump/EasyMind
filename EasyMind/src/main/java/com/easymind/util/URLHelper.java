/*
TODO:为了处理无法通过.class.getResource()方法获取资源而新建的类，可能堆成屎山
使用途经：所有需要调用resources里面文件的相关操作
 */


package com.easymind.util;

import java.net.MalformedURLException;
import java.net.URL;

public class URLHelper {
    private static final String URLPATH;

    static {
        String urlPath = URLHelper.class.getResource("").toString();
        URLPATH = urlPath.substring(0,urlPath.indexOf("com"));
    }

    public static URL getResource(String abstractPath){
        URL url = null;
        try {
            url = new URL(URLPATH+abstractPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
