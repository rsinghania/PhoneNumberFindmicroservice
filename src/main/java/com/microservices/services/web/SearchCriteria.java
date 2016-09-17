package com.microservices.services.web;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class SearchCriteria
{
  private String phoneNumber;

  private String searchText;

  public String getSearchText()
  {
    return searchText;
  }

  public void setSearchText(String searchText)
  {
    this.searchText = searchText;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public boolean isValid()
  {
    if (StringUtils.hasText(phoneNumber))
      return !(StringUtils.hasText(searchText));
    else
      return (StringUtils.hasText(searchText));
  }

  public boolean validate(Errors errors)
  {
    if (StringUtils.hasText(phoneNumber))
    {
      if (phoneNumber.length() != 9)
        errors.rejectValue("phoneNumber", "badFormat", "phoneNumber  should be 9 digits");
      else
      {
        try
        {
          Integer.parseInt(phoneNumber);
        }
        catch (NumberFormatException e)
        {
          errors.rejectValue("phoneNumber", "badFormat", "phoneNumber should be 9 digits");
        }
      }

      if (StringUtils.hasText(searchText))
      {
        errors.rejectValue("searchText", "nonEmpty", "Cannot specify phoneNumber and search text");
      }
    }
    else if (StringUtils.hasText(searchText))
    {
      ; // Nothing to do
    }
    else
    {
      errors.rejectValue("phoneNumber", "nonEmpty", "Must specify an phoneNumber or search text");

    }

    return errors.hasErrors();
  }

  @Override
  public String toString()
  {
    // TODO Auto-generated method stub
    return (StringUtils.hasText(phoneNumber) ? "number: " + phoneNumber : "")
        + (StringUtils.hasText(searchText) ? " text: " + searchText : "");
  }
}
