package net.naffets.nevsuite.backend.framework.webservice;

import net.naffets.nevsuite.backend.framework.domain.facade.BackendComponentServiceFacade;
import net.naffets.nevsuite.backend.framework.lang.util.JndiNameBuilder;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author br4sk4
 * created on 24.12.2016
 */
@Path("/ComponentService")
public class BackendComponentWebservice {

    private BackendComponentServiceFacade backendComponentServiceFacade;

    @PostConstruct
    public void init() {
        try {
            InitialContext ic = new InitialContext();
            this.backendComponentServiceFacade = (BackendComponentServiceFacade) new JndiNameBuilder()
                    .withInitialContext(ic)
                    .withApplication("common-backend-0.11")
                    .withFacade(BackendComponentServiceFacade.class)
                    .build();
        } catch (javax.naming.NamingException e) {
            this.backendComponentServiceFacade = null;
        }
    }

    @GET
    @Path("respond")
    @Produces(MediaType.TEXT_PLAIN)
    public String respond() {
        return backendComponentServiceFacade.respond();
    }

}
