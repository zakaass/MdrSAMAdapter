/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdr.sam.adapter.log;

import com.wm.adk.log.ARTLogger;
import mdr.sam.adapter.client.MdrSAMAdapter;

/**
 *
 * @author lt17-zakaria
 */
public class Util
{
  public static final int VERBOSE_LEVEL1 = 1;
  public static final int VERBOSE_LEVEL4 = 4;
  private static MdrSAMAdapter adapter = (MdrSAMAdapter)MdrSAMAdapter.getInstance();
  private static ARTLogger _logger = new ARTLogger(adapter
    .getAdapterMajorCode(), adapter
    .getAdapterName(), adapter
    .getAdapterResourceBundleName());
  
  public static void logDebug(int key, String[] args)
  {
    if (_logger != null) {
      _logger.logDebug(key, args);
    }
  }
  
  public static void logDebug(int key, String arg)
  {
    if (_logger != null) {
      _logger.logDebug(key, arg);
    }
  }
  
  public static void logDebug(int key)
  {
    if (_logger != null) {
      _logger.logDebug(key);
    }
  }
  
  public static void logDebug1(int key, String[] args)
  {
    if (_logger != null) {
      _logger.logDebugPlus(1, key, args);
    }
  }
  
  public static void logDebug1(int key, String arg)
  {
    if (_logger != null) {
      _logger.logDebugPlus(1, key, arg);
    }
  }
  
  public static void logDebug1(int key)
  {
    if (_logger != null) {
      _logger.logDebugPlus(1, key);
    }
  }
  
  public static void logDebug4(int key, String[] args)
  {
    if (_logger != null) {
      _logger.logDebugPlus(4, key, args);
    }
  }
  
  public static void logDebug4(int key, String arg)
  {
    if (_logger != null) {
      _logger.logDebugPlus(4, key, arg);
    }
  }
  
  public static void logDebug4(int key)
  {
    if (_logger != null) {
      _logger.logDebugPlus(4, key);
    }
  }
  
  public static void logWarning4(int key, String arg)
  {
    if (_logger != null) {
      _logger.logWarning(key, arg);
    }
  }
  
  public static void logWarning4(int key, String[] arg)
  {
    if (_logger != null) {
      _logger.logWarning(key, arg);
    }
  }
  
  public static void logInfo(int key, String[] arg)
  {
    if (_logger != null) {
      _logger.logInfo(key, arg);
    }
  }
  
  public static void logError(int key, String[] arg)
  {
    if (_logger != null) {
      _logger.logError(key, arg);
    }
  }
  
  public static void closeLog()
  {
    if (_logger != null)
    {
      _logger.close();
      _logger = null;
    }
  }
  
  public static void initializeLog()
  {
    if (_logger == null) {
      _logger = new ARTLogger(adapter.getAdapterMajorCode(), adapter.getAdapterName(), adapter.getAdapterResourceBundleName());
    }
  }
}
