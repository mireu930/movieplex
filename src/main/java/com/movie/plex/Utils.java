package com.movie.plex;

public class Utils {

    // XSS 방지 처리 (스크립트 태그나 위험한 태그 제거/변환)
    public static String XSSHandling(String content) {
        if(content != null) {
            content = content.replaceAll("&", "&amp;");
            content = content.replaceAll("<", "&lt;");
            content = content.replaceAll(">", "&gt;");
            content = content.replaceAll("\"", "&quot;");
        }
        return content;
    }

    // 줄바꿈 처리 (\n -> <br>)
    public static String newLineHandling(String content) {
        if(content != null) {
            content = content.replaceAll("\n", "<br>");
        }
        return content;
    }
}
