/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanms.siso.eft.proxy;

import com.sft.core.converter.ByteConvert;

/**
 *
 * @author ldavila
 */
public class ProxyCommResult
{
  private int result;
  private byte[] response;
  private String stringResponse;

  /**
   * @return the result
   */
  public int getResult()
  {
    return result;
  }

  /**
   * @param result the result to set
   */
  public void setResult(int result)
  {
    this.result = result;
  }

  /**
   * @return the stringResponse
   */
  public String getStringResponse()
  {
    return stringResponse;
  }

  /**
   * @return the response
   */
  public byte[] getResponse()
  {
    return response;
  }

  /**
   * @param response the response to set
   */
  public void setResponse(byte[] response)
  {
    this.response = response;
    stringResponse = ByteConvert.bytes2String(response);
  }

  public void setResponse(byte[] response, String encoding)
  {
    this.response = response;
    stringResponse = ByteConvert.bytes2String(response, encoding);
  }
}
