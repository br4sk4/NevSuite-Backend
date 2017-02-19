package net.naffets.nevsuite.backend.timeseries.webservice;

import net.naffets.nevsuite.backend.framework.lang.util.JndiNameBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.builder.TimeseriesHeadBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.facade.TimeseriesDomainServiceFacade;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author br4sk4
 * created on 25.12.2016
 */
@Path("/DomainService")
public class TimeseriesDomainWebservice {

    private TimeseriesDomainServiceFacade timeseriesDomainServiceFacade;

    @PostConstruct
    public void init() {
        try {
            InitialContext ic = new InitialContext();
            this.timeseriesDomainServiceFacade = (TimeseriesDomainServiceFacade) new JndiNameBuilder()
                    .withInitialContext(ic)
                    .withApplication("timeseries-backend-0.11")
                    .withFacade(TimeseriesDomainServiceFacade.class)
                    .build();
        } catch (javax.naming.NamingException e) {
            this.timeseriesDomainServiceFacade = null;
        }
    }

    @GET
    @Path("/TimeseriesHead/Uuid/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findTimeseriesHeadByUuid(@PathParam("uuid") String uuid) {
        return new TimeseriesHeadBuilder().fromDTO(timeseriesDomainServiceFacade.findTimeseriesHeadByUuid(uuid)).toJson();
    }

    @GET
    @Path("/TimeseriesHead/Identifier/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findTimeseriesHeadByIdentifier(@PathParam("identifier") String identifier) {
        final StringBuilder sb = new StringBuilder("");
        sb.append("[");
        timeseriesDomainServiceFacade.findTimeseriesHeadByIdentifier(identifier).stream()
                .forEach(head -> sb.append(new TimeseriesHeadBuilder().fromDTO(head).toJson()
                        .replace("{\"TimeseriesHead\":", "")
                        .replace("}}", "}")
                ).append(","));
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]");
        return sb.toString();
    }

    @GET
    @Path("/TimeseriesHead/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String findAllTimeseriesHeads() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("[");
        timeseriesDomainServiceFacade.findAllTimeseriesHeads().stream()
                .forEach(head -> sb.append(new TimeseriesHeadBuilder().fromDTO(head).toJson()
                        .replace("{\"TimeseriesHead\":", "")
                        .replace("}}", "}")
                ).append(","));
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("]");
        return sb.toString();
    }

}
