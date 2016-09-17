package com.microservices.services.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Client controller, fetches Account info from the microservice via
 * 
 */
@Controller
public class WebAccountsController
{

  @Autowired
  protected WebAccountsService phonesService;

  protected Logger logger = Logger.getLogger(WebAccountsController.class.getName());

  protected WebAccountsController()
  {

  }
  
  public WebAccountsController(WebAccountsService phonesService)
  {
    this.phonesService = phonesService;
  }

  @InitBinder
  public void initBinder(WebDataBinder binder)
  {
    binder.setAllowedFields("phoneNumber", "searchText");
  }

  @RequestMapping("/phones")
  public String goHome()
  {
    return "index";
  }

  @RequestMapping("/phones/{phoneNumber}")
  public String byNumber(Model model, @PathVariable("phoneNumber") String phoneNumber)
  {

    logger.info("web-service byNumber() invoked: " + phoneNumber);

    Phone phoneDetails = phonesService.findByNumber(phoneNumber);
    logger.info("web-service byNumber() found: " + phoneDetails);
    model.addAttribute("phone", phoneDetails);
    return "account";
  }

  @RequestMapping(value = "/phones/search", method = RequestMethod.GET)
  public String searchForm(Model model)
  {
    model.addAttribute("searchCriteria", new SearchCriteria());
    return "accountSearch";
  }

  @RequestMapping(value = "/phones/dosearch")
  public String doSearch(Model model, SearchCriteria criteria, BindingResult result)
  {
    logger.info("web-service search() invoked: " + criteria);

    criteria.validate(result);

    if (result.hasErrors())
    {
      return "accountSearch";
    }

    String accountNumber = criteria.getPhoneNumber();
    if (StringUtils.hasText(accountNumber))
    {
      return byNumber(model, accountNumber);
    }
    return accountNumber;
  }
}
