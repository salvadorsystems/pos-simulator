/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanms.siso.eft.utils;

/**
 *
 * @author salvador
 */
public class Definition
{
  public static final String ZONE_TOKEN = "_ZONE_";
  public static final String TERMINAL_TOKEN = "_TERMINAL_";
  public static final String ISSUER_TOKEN = "_ISSUER_";

  public static final char ZONE = 'Z';
  public static final char TERMINAL = 'T';
  public static final char ISSUER = 'I';

  public static final String ZONE_S = "Z";
  public static final String TERMINAL_S = "T";
  public static final String ISSUER_S = "I";

  public static final String TerminalFile = "SISOSEC_TERMINAL";
  public static final String IssuerFile = "SISOSEC_ISSUER";
  public static final String ZoneFile = "SISOSEC_ZONE";

  // Código de error a nivel de API
  public static final int TIBTEST_RESULT_SUCCESS = 0;
  public static final int TIBTEST_RESULT_ERROR_SETUP_CONNECT = 104;
  public static final int TIBTEST_RESPONSECODE_SUCCESS = 0;
  public static final int TIBTEST_RESPONSECODE_ERROR_PROCESS = 901;
  public static final int TIBTEST_RESPONSECODE_ERROR_INVALIDSTATE = 903;
  public static final int TIBTEST_RESPONSECODE_ERROR_COMM = 904;
  public static final int TIBTEST_RESPONSECODE_ERROR_INVALID_HOST = 908;
  public static final int TIBTEST_RESPONSECODE_ERROR_SYSTEMGENERAL = 951;
  public static final int TIBTEST_RESPONSECODE_ERROR_SYSTEMDOWN = 952;
  public static final int TIBTEST_RESPONSECODE_ERROR_TIMEOUT = 959;
  public static final int TIBTEST_RESPONSECODE_ERROR_GENERAL = 999;
}
