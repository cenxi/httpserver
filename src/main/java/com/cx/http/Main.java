package com.cx.http;

import com.cx.http.server.Server;

/**
 * Created by Administrator on 2017/8/17.
 */
public class Main {

    public static void main(String[] args) {

        new Thread(new Server(false)).start();

    }
}
