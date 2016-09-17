package com.microservices.phones;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PHONENUMBER")
public class PhoneNumberDetails implements Serializable
{

  private static final long serialVersionUID = 1L;

  public static Long nextId = 0L;

  @Id
  protected Long id;

  protected String number;

  @Column(name = "name")
  protected String owner;

  protected static Long getNextId()
  {
    synchronized (nextId)
    {
      return nextId++;
    }
  }
  protected PhoneNumberDetails() {
    
  }
  public PhoneNumberDetails(String number, String owner)
  {
    id = getNextId();
    this.number = number;
    this.owner = owner;
  }

  public long getId()
  {
    return id;
  }

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
