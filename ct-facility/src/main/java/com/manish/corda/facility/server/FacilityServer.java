package com.manish.corda.facility.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import org.springframework.context.event.EventListener;

@SpringBootApplication
public class FacilityServer  {


    @Autowired
    public  static NodeRPCConnection nodeRPCConnection;
    /**
     * Starts our Spring Boot application.
     */
    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication();
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(FacilityServer.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initiateFacilityObserverPostStartup() throws Exception{

       // FacilityObserver.startFacilityWatch();

    }


}
