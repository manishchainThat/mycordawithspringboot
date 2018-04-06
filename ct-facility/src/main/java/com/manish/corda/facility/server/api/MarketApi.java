package com.manish.corda.facility.server.api;


import com.manish.corda.facility.server.NodeRPCConnection;
import com.google.common.collect.ImmutableMap;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.node.NodeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

// This API is accessible from /api/facility. The endpoint paths specified below are relative to it.
@Component
@Path("/market")
public class MarketApi {


    // private final CordaRPCOps rpcOps;
    // private final CordaX500Name myLegalName;
    // private final String NOTARY_NAME = "Controller";
    //private final String NETWORK_MAP_NAME = "Network Map Service";

    //static private final Logger logger = LoggerFactory.getLogger(MarketApi.class);

/*
    public MarketApi(CordaRPCOps rpcOps) {
            this.rpcOps = rpcOps;
            this.myLegalName = rpcOps.nodeInfo().getLegalIdentities().get(0).getName();
        }
*/


    private final NodeRPCConnection nodeRPCConnection;

    private final CordaRPCOps proxy;

    private @Value("${config.controller.name}") String controllerName;
    private final CordaX500Name myLegalName;
    private final String NETWORK_MAP_NAME = "Network Map Service";

    static private final Logger logger = LoggerFactory.getLogger(MarketApi.class);

    public MarketApi(NodeRPCConnection nodeRPCConnection) {
        this.nodeRPCConnection = nodeRPCConnection;
        this.proxy = nodeRPCConnection.getProxy();
        this.myLegalName = proxy.nodeInfo().getLegalIdentities().get(0).getName();


    }

    /**
     * Returns the node's name.
     */


    // @GetMapping(value="me", produces = MediaType.APPLICATION_JSON_VALUE)
    @GET
    @Path("me")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)

    public Map<String, String> whoami() { return ImmutableMap.of("me", myLegalName.toString()); }

    /**
     * Returns all parties registered with the [NetworkMapService]. These names can be used to look up identities
     * using the [IdentityService].
     */

    // @GetMapping(value="peers", produces = MediaType.APPLICATION_JSON_VALUE)






    //!!!!!!!!!!!!!!!!!!!!!!!!!!! IMPORTANT NOTE ***********************************************************************

    // @joel please note I know if i would change the return type i can get the same response but the issue is our ui has been made
    // according to the response coming from the corda webserver .And when we change from the corda websever to the spring boot
    // these kind of difference is coming .I have shown u one example we are facing the same issue with lots of API response
    // please provide some generic solution or atleast the cordawebserver doing the response tweak so that  we can have it in spring boot as weel
    /// thankss

   // ***********************************************************************
    @GET
    @Path("peers")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<CordaX500Name>> getPeers() {
        List<NodeInfo> nodeInfoSnapshot = proxy.networkMapSnapshot();
        return ImmutableMap.of(
                "peers",
                nodeInfoSnapshot
                        .stream()
                        .map(node -> node.getLegalIdentities().get(0).getName())
                        .filter(name -> !name.equals(myLegalName) && !name.getOrganisation().equals(controllerName)
                                && !name.getOrganisation().equals(NETWORK_MAP_NAME))
                        .collect(toList()));
    }



}