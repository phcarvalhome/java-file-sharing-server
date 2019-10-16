package com.phcarvalho.model;

import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.corba.FileMetadata;
import com.phcarvalho.model.corba.User;
import com.phcarvalho.model.corba.client.ClientServiceHelper;
import com.phcarvalho.model.corba.server.ServerServiceHelper;
import com.phcarvalho.model.service.ServerService;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import java.rmi.RemoteException;
import java.util.concurrent.Executors;

public class ConnectionModel {

//    private ConnectionController controller;
    private ORB orb;
    private POA rootPOA;
    private NamingContext namingContext;
    //    private IConnectionStrategy connectionStrategy;
//    private ICommandTemplateFactory commandTemplateFactory;
//    private ConnectedPlayerModel connectedPlayerModel;
//    private BoardModel boardModel;

//    public ConnectionModel(ConnectionController controller) {
//        this.controller = controller;
////        connectionStrategy = DependencyFactory.getSingleton().get(IConnectionStrategy.class);
////        commandTemplateFactory = DependencyFactory.getSingleton().get(ICommandTemplateFactory.class);
////        connectedPlayerModel = DependencyFactory.getSingleton().get(ConnectedPlayerModel.class);
////        boardModel = DependencyFactory.getSingleton().get(BoardModel.class);
//    }

    public void start(User localUser) {
        Configuration.getSingleton().setLocalUser(localUser);
//        Configuration.getSingleton().setRemoteUser(remoteUser);
//        connectionStrategy.connectToServer(remoteUser);
//        commandTemplateFactory.getConnection().connect(new ConnectCommand(remoteUser));

        orb = ORB.init(/*args*/ new String[]{"[-ORBInitialHost" + " Host" + "]"}, null);

        try {
            org.omg.CORBA.Object rootPOAObject = orb.resolve_initial_references("RootPOA");
            rootPOA = POAHelper.narrow(rootPOAObject);
            org.omg.CORBA.Object namingContextObject = orb.resolve_initial_references("NameService") ;
            namingContext = NamingContextHelper.narrow(namingContextObject);
            serve(); //TODO isso fica aqui mesmo?
//            connectToServer();
        } catch (org.omg.CORBA.ORBPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        }
    }

    private void serve(){
        ServerService serverService = new ServerService();
        String serviceName = Configuration.getSingleton().getLocalUser().id;

        try {
            org.omg.CORBA.Object serviceObject = rootPOA.servant_to_reference(serverService);
            NameComponent nameComponent = new NameComponent(serviceName, "Instance");
            NameComponent[] nameComponentArray = {nameComponent};

            namingContext.rebind(nameComponentArray, serviceObject);
            rootPOA.the_POAManager().activate();

            Executors.newSingleThreadExecutor().execute(() -> orb.run());

            Configuration.getSingleton().setServerConnected(true); //FIXME apagar isso... só pra teste p2p
        } catch (WrongPolicy wrongPolicy) {
            wrongPolicy.printStackTrace();
        } catch (AdapterInactive adapterInactive) {
            adapterInactive.printStackTrace();
        } catch (ServantNotActive servantNotActive) {
            servantNotActive.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }
    }

//    public void connectToServerByCallback(ConnectCommand connectCommand) {
//        Configuration.getSingleton().setServerConnected(true);
//        controller.connectToServerByCallback();
//    }

    public void disconnect() throws RemoteException {
//        Game gameSelected = Configuration.getSingleton().getGameSelected();
//
//        if(gameSelected != null){
//            Player player = Configuration.getSingleton().getPlayer();
//
//            commandTemplateFactory.getMain().addPlayer(new AddPlayerCommand(player, gameSelected, true));
//        }
////        else
////        commandTemplateFactory.getConnection().disconnect(new DisconnectCommand()); //TODO precisa disso mesmo aqui (disconnect por fechar frame do jogo)...
    }

//    public void disconnectByCallback(DisconnectCommand disconnectCommand) {
//        controller.disconnectByCallback(disconnectCommand);
//    }
//
//    public void clear() {
//        Configuration.getSingleton().clear();
//        controller.clear();
//    }
}
