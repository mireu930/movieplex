package com.movie.plex;

public class Utils {

    // XSS ���� ó�� (��ũ��Ʈ �±׳� ������ �±� ����/��ȯ)
    public static String XSSHandling(String content) {
        if(content != null) {
            content = content.replaceAll("&", "&amp;");
            content = content.replaceAll("<", "&lt;");
            content = content.replaceAll(">", "&gt;");
            content = content.replaceAll("\"", "&quot;");
        }
        return content;
    }

    // �ٹٲ� ó�� (\n -> <br>)
    public static String newLineHandling(String content) {
        if(content != null) {
            content = content.replaceAll("\n", "<br>");
        }
        return content;
    }
}
