package com.example.kejun.myapplication.utils;

/**
 * Created by kejun
 */
public class Configuration {
    //udp
    public static final int UDP_PORT = 8888;
    public static final int SEARCH_TIMOUT = 5;
    public static final int SEARCH_TIMES = 10;

    //tcp
    public static final int TCP_PORT = 65500;
    public static final int CONNECT_TIMEOUT = 1;
    public static final int CONNECT_TIMES = 10;
    public static final int WAITING_TIME = 10;

    //IO
    public static final int STRING_BUF_LEN = 1024 * 8;
    public static final int FILE_IO_BUF_LEN = 1024 * 64;

    //String
    public static final String FILE_LEN_SPT = "~";
    public static final String FILES_SPT    = "`";

    public static final String DELIMITER = "    \neofeof    \neofeof";
    public static final String EOF       = "    \neofeof";

    public static int currentTcpPort = TCP_PORT;
}
