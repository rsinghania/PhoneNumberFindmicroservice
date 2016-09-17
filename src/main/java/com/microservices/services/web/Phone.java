package com.microservices.services.web;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Account DTO - used to interact with the {@link WebAccountsService}.
 * 
 * @author Paul Chapman
 */
@JsonRootName("PhoneNumberDetails")
public class Phone
{

  protected Long id;
  protected String number;
  protected String owner;

  protected Phone()
  {

  }

  public long getId()
  {
    return id;
  }

  /**
   * Set JPA id - for testing and JPA only. Not intended for normal use.
   * 
   * @param id The new id.
   */
  protected void setId(long id)
  {
    this.id = id;
  }

  public String getNumber()
  {
    return number;
  }

  protected void setNumber(String accountNumber)
  {
    this.number = accountNumber;
  }

  public String getOwner()
  {
    return owner;
  }

  protected void setOwner(String owner)
  {
    this.owner = owner;
  }

  @Override
  public String toString()
  {
    return number + " [" + owner + "]: $";
  }

}
