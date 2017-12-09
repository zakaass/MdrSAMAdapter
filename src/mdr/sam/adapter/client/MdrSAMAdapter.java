/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdr.sam.adapter.client;

import com.wm.adk.WmAdapter;
import com.wm.adk.error.AdapterException;
import com.wm.adk.info.AdapterTypeInfo;
import com.wm.adk.log.ARTLogger;
import java.util.Locale;
import mdr.sam.adapter.client.connection.MdrSAMConnectionFactory;

/**
 *
 * @author lt17-zakaria
 */
public class MdrSAMAdapter extends WmAdapter implements MdrSAMConstants {

    static MdrSAMAdapter _instance = null;
    private ARTLogger _logger;

    public MdrSAMAdapter() throws AdapterException {
    }

    @Override
    public void initialize() throws AdapterException {
        this._logger = new ARTLogger(getAdapterMajorCode(), getAdapterName(), getAdapterResourceBundleName());
    }

    @Override
    public String getAdapterName() {
        return ADAPTER_NAME;
    }

    @Override
    public String getAdapterVersion() {
        return ADAPTER_VERSION;
    }

    @Override
    public String getAdapterJCASpecVersion() {
        return ADAPTER_JCA_VERSION;
    }

    @Override
    public String getAdapterResourceBundleName() {
        return "mdr.sam.adapter.client.MdrSAMAdapterResourceBundle";
    }

    @Override
    public int getAdapterMajorCode() {
        return ADAPTER_MAJOR_CODE;
    }

    @Override
    public void fillAdapterTypeInfo(AdapterTypeInfo ati, Locale locale) {
        ati.addConnectionFactory(MdrSAMConnectionFactory.class.getName());
    }

    public static MdrSAMAdapter getInstance() {
        if (_instance != null) {
            return _instance;
        }
        synchronized (MdrSAMAdapter.class) {
            if (_instance != null) {
                return _instance;
            }
            try {
                _instance = new MdrSAMAdapter();
                return _instance;
            } catch (Exception t) {
                t.printStackTrace();
                return null;
            }
        }
    }

    public ARTLogger retrieveLogger() {
        return this._logger;
    }

}
