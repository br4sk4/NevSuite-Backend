package net.naffets.nevsuite.backend.timeseries.domain.adapter;

import net.naffets.nevsuite.backend.framework.domain.facade.BackendComponentServiceFacade;
import net.naffets.nevsuite.backend.framework.lang.util.JndiNameBuilder;

import javax.ejb.Stateless;
import javax.naming.InitialContext;

/**
 * @author br4sk4
 * created on 25.12.2016
 */
@Stateless
public class BackendComponentServiceAdapter {

    public String respond() {
        try {
            InitialContext ic = new InitialContext();
            BackendComponentServiceFacade backendComponentServiceFacade = (BackendComponentServiceFacade) new JndiNameBuilder()
                    .withInitialContext(ic)
                    .withApplication("common-backend-0.11")
                    .withFacade(BackendComponentServiceFacade.class)
                    .build();
            return backendComponentServiceFacade.respond();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
