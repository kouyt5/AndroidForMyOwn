package com.example.permission.test;

import com.orhanobut.logger.Logger;

public class Test {

    private static Client client;

    public static void init() {
        if (client == null) {
            client = new Client();
            Logger.d("Client is initted");
        }
        else
            Logger.d("Client is not initted again");
    }
}
