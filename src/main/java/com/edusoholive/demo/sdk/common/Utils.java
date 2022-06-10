package com.edusoholive.demo.sdk.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class Utils {
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String appendQueryParams(String uri, QueryParams params) {
        return appendQueryParams(uri, params.toQueryParams());
    }

    /**
     * copy from https://dzone.com/articles/two-ways-convert-java-map
     */
    public static String appendQueryParams(String uri, Map<String, String> params) {
        var sb = new StringBuilder();

        for (String key : params.keySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            String value = params.get(key);
            try {
                sb.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
                sb.append("=");
                sb.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }

        return uri + "?" + sb.toString();
    }
}
