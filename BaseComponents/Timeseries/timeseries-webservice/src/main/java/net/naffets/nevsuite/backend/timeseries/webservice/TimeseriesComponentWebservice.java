package net.naffets.nevsuite.backend.timeseries.webservice;

import net.naffets.nevsuite.backend.framework.lang.util.JndiNameBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.facade.TimeseriesComponentServiceFacade;

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
public class TimeseriesComponentWebservice {

    private TimeseriesComponentServiceFacade timeseriesComponentServiceFacade;

    @PostConstruct
    public void init() {
        try {
            InitialContext ic = new InitialContext();
            this.timeseriesComponentServiceFacade = (TimeseriesComponentServiceFacade) new JndiNameBuilder()
                    .withInitialContext(ic)
                    .withApplication("timeseries-backend-0.11")
                    .withFacade(TimeseriesComponentServiceFacade.class)
                    .build();
        } catch (javax.naming.NamingException e) {
            this.timeseriesComponentServiceFacade = null;
        }
    }

    @GET
    @Path("respond")
    @Produces(MediaType.TEXT_PLAIN)
    public String respond() {
        return timeseriesComponentServiceFacade.respond();
    }

}
