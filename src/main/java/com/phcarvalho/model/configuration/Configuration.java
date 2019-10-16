package com.phcarvalho.model.configuration;

import com.phcarvalho.model.corba.User;

public class Configuration {

    private static Configuration singleton;

    public static Configuration getSingleton(){

        if(singleton == null)
            singleton = new Configuration();

        return singleton;
    }

    private User localUser;
    private boolean serverConnected;

    private Configuration() {
        serverConnected = false;
    }

    public User getLocalUser() {

//        if(localUser == null)
//            throw new RuntimeException("The localUser field is null!");

        return localUser;
    }

    public void setLocalUser(User localUser) {
        this.localUser = localUser;
    }

    public boolean isServerConnected() {
        return serverConnected;
    }

    public void setServerConnected(boolean serverConnected) {
        this.serverConnected = serverConnected;
    }
}
