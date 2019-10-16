package com.phcarvalho;

import com.phcarvalho.model.ConnectionModel;
import com.phcarvalho.model.corba.User;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
//            DependencyFactory.getSingleton().setCommunicationStrategyFactory(new SocketStrategyFactory());
//            DependencyFactory.getSingleton().setCommunicationStrategyFactory(new RMIStrategy());
//            DependencyFactory.getSingleton().build();
//            DependencyFactory.getSingleton().get(MainView.class);
            ConnectionModel connectionModel = new ConnectionModel();
            User server = new User("SERVER");

            connectionModel.start(server);
        });
    }
}
