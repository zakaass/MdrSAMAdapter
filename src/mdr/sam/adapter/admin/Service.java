/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdr.sam.adapter.admin;

import com.wm.app.b2b.server.ServiceException;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import mdr.sam.adapter.client.MdrSAMAdapter;


/**
 *
 * @author lt17-zakaria
 */
public class Service {

    public static void registerAdapter(IData pipeline)
            throws ServiceException {
        IDataCursor serviceInputCursor = null;
        try {
            IData serviceInput = IDataFactory.create();
            serviceInputCursor = serviceInput.getCursor();

            serviceInputCursor.first();
            serviceInputCursor.insertBefore("adapter", MdrSAMAdapter.getInstance());
            com.wm.app.b2b.server.Service.doInvoke("wm.art.adapter.deployment", "registerAdapterType", serviceInput);

            //Util.initializeLog();
        } catch (Throwable t) {
            if ((t instanceof ServiceException)) {
                throw ((ServiceException) t);
            }
            //throw new ServiceException(JDBCAdapter.getInstance().createAdapterException(598, new String[]{JDBCAdapter.class
            //  .getName(), t
            //  .getLocalizedMessage()}, t));
        } finally {
            if (serviceInputCursor != null) {
                serviceInputCursor.destroy();
            }
        }
    }

    public static void unregisterAdapter(IData pipeline)
            throws ServiceException {
        IDataCursor serviceInputCursor = null;
        try {
            IData serviceInput = IDataFactory.create();
            serviceInputCursor = serviceInput.getCursor();

            serviceInputCursor.first();
            serviceInputCursor.insertBefore("adapter",
                    MdrSAMAdapter.getInstance());
            com.wm.app.b2b.server.Service.doInvoke("wm.art.adapter.deployment", "unregisterAdapterType", serviceInput);

            //Util.closeLog();
        } catch (Throwable t) {
            ServiceException se = null;
            if ((t instanceof ServiceException)) {
                se = (ServiceException) t;
            } else {
                //throw new ServiceException(JDBCAdapter.getInstance().createAdapterException(599, new String[]{JDBCAdapter.class
                //    .getName(), t
                //    .getLocalizedMessage()}, t));
            }
        } finally {
            if (serviceInputCursor != null) {
                serviceInputCursor.destroy();
            }
        }
    }
}
