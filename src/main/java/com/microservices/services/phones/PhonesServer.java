package com.microservices.services.phones;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.microservices.phones.PhoneNumberRepository;
import com.microservices.phones.PhonesConfiguration;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 *
 * @author Paul Chapman
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(PhonesConfiguration.class)
public class PhonesServer
{

  @Autowired
  protected PhoneNumberRepository phoneNumberRepository;

  protected Logger logger = Logger.getLogger(PhonesServer.class.getName());

  /**
   * Run the application using Spring Boot and an embedded servlet engine.
   * 
   * @param args Program arguments - ignored.
   */
  public static void main(String[] args)
  {
    // Tell server to look for accounts-server.properties or
    // accounts-server.yml
    System.setProperty("spring.config.name", "accounts-server");

    SpringApplication.run(PhonesServer.class, args);
  }
}
