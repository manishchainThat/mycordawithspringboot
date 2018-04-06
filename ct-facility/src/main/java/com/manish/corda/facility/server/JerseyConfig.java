package com.manish.corda.facility.server;

import com.manish.corda.facility.server.api.MarketApi;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("rest")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
      //  packages("com.chainthat.reinsurance.facility.server.api");


    }

    @PostConstruct
    public void setUp() {
        register(MarketApi.class);
        //register(GenericExceptionMapper.class);
    }
}
