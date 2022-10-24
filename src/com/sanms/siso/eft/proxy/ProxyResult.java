/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanms.siso.eft.proxy;

import com.sanms.siso.eft.constantes.Definition;

/**
 *
 * @author ldavila
 */
public class ProxyResult
{
  private int result;
  private int responseCode;

  public ProxyResult()
  {
    result = Definition.TIBTEST_RESPONSECODE_ERROR_GENERAL;
    responseCode = Definition.TIBTEST_RESPONSECODE_ERROR_GENERAL;
  }

  public int getResult()
  {
    return result;
  }

  public void setResult(int result)
  {
    this.result = result;
  }

  public int getResponseCode()
  {
    return responseCode;
  }

  public void setResponseCode(int responseCode)
  {
    this.responseCode = responseCode;
  }
}
