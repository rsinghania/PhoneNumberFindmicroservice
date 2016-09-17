package com.microservices.services.web;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.exceptions.PhoneNotFoundException;

/**
 * Hide the access to the microservice inside this local service.
 * 
 * @author Paul Chapman
 */
@Service
public class WebAccountsService
{

  @Autowired
  @LoadBalanced
  protected RestTemplate restTemplate;

  protected String serviceUrl;

  protected Logger logger = Logger.getLogger(WebAccountsService.class.getName());

  public WebAccountsService(String serviceUrl)
  {
    this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
  }
  /**
   * The RestTemplate works because it uses a custom request-factory that uses
   * Ribbon to look-up the service to use. This method simply exists to show
   * this.
   */
  @PostConstruct
  public void demoOnly()
  {
    // Can't do this in the constructor because the RestTemplate injection
    logger.warning("The RestTemplate request factory is " + restTemplate.getRequestFactory().getClass());
  }

  public Phone findByNumber(String phoneNumber)
  {

    logger.info("findByNumber() invoked: for " + phoneNumber);
    return restTemplate.getForObject(serviceUrl + "/phone/{phoneNumber}", Phone.class, phoneNumber);
  }

  public Phone getByNumber(String phoneNumber)
  {
    Phone account = restTemplate.getForObject(serviceUrl + "/phone/{phoneNumber}", Phone.class, phoneNumber);

    if (account == null)
      throw new PhoneNotFoundException(phoneNumber);
    else
      return account;
  }
}
