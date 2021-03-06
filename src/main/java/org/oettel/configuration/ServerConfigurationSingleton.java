package org.oettel.configuration;

import org.oettel.model.message.ServerMessage;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class  ServerConfigurationSingleton {
    private static ServerConfigurationSingleton instance;
    private int serverPort;
    private InetAddress serverAddress;
    private InetAddress leader;
    private boolean isLeader;
    private boolean electionActive;
    private int queueIdCounter = 0;
    List<ServerMessage> messageQueue = new ArrayList<>();




    private final List<InetAddress> replicaServer = new ArrayList<>();

    private ServerConfigurationSingleton() {

    }


    public static ServerConfigurationSingleton getInstance() {
        if (ServerConfigurationSingleton.instance == null) {
            ServerConfigurationSingleton.instance = new ServerConfigurationSingleton();
        }
        return ServerConfigurationSingleton.instance;
    }

    public InetAddress getLeader() {return leader;}

    public void setLeader(InetAddress leader) {
        this.leader = leader;
    }

    public boolean getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(boolean leader) {
        isLeader = leader;
    }

    public void setServerPort(final int serverPort) {
        this.serverPort = serverPort;
        System.out.println("Configure Server Port: " + this.serverPort);
    }

    public void setServerAddress(final InetAddress serverAddress) {
        this.serverAddress = serverAddress;
        System.out.println("Configure Server Address: " + this.serverAddress);
    }

    public int getServerPort() {
        return serverPort;
    }

    public InetAddress getServerAddress() {
        return serverAddress;
    }

    public void addReplicaToReplicaList(final InetAddress replicaAddress) {
        replicaServer.add(replicaAddress);
    }
    
    public void removeReplicaFromReplicaList(final InetAddress replicaAddress) {
        replicaServer.remove(replicaServer.indexOf(replicaAddress));
    }

    public List<InetAddress> getReplicaServer() {
        return replicaServer;
    }



    public void addMessageToHoldbackQueue(ServerMessage message) {
        this.queueIdCounter = queueIdCounter + 1;
        message.setQueueIdCounter(queueIdCounter);
        messageQueue.add(message);
    }

    public int getQueueIdCounter() {
        return queueIdCounter;
    }

    public List<ServerMessage> getMessageQueue() {
        return messageQueue;
    }

    public void setElectionActive(boolean electionActive) {
        this.electionActive = electionActive;
    }
}

