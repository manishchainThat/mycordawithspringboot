package com.manish.corda.facility.server;

import net.corda.client.rpc.CordaRPCClient;
import net.corda.client.rpc.CordaRPCClientConfiguration;
import net.corda.client.rpc.CordaRPCConnection;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.utilities.NetworkHostAndPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class NodeRPCConnection {


    private CordaRPCOps proxy;
    private CordaRPCConnection cordaRPCConnection;

    private static NodeRPCConnection instance;

    private @Value("${config.rpc.username}") String username;
    private @Value("${config.rpc.password}") String password;
    private @Value("${config.rpc.host}") String host;
    private @Value("${config.rpc.port}") int rpcPort;


    @PostConstruct
    public void initiateNodeRPCConnection() {
        final NetworkHostAndPort nodeAddress= new NetworkHostAndPort(host, rpcPort);
        final CordaRPCClient client = new CordaRPCClient(nodeAddress, CordaRPCClientConfiguration.DEFAULT);
        System.out.println ("set up corda client="+ client);
        cordaRPCConnection = client.start(username, password);
        System.out.println ("connection="+ cordaRPCConnection);

        proxy = cordaRPCConnection.getProxy();
        instance = this;
        System.out.println ("set proxy="+ proxy);

    }

    @PreDestroy
    public void  closeNodeRPCConnection() {
        cordaRPCConnection.notifyServerAndClose();
    }

    public  CordaRPCOps getProxy() {
        return this.proxy;
    }

    public static NodeRPCConnection getNodeRPCConnection() {
        return instance;
    }
}
