package net.naffets.nevsuite.backend.framework.lang.util;

import net.naffets.nevsuite.backend.framework.core.api.ServiceFacade;
import net.naffets.nevsuite.backend.framework.lang.annotation.Facade;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author br4sk4
 * created on 06.07.2016
 */
public class JndiNameBuilder {

    private String application = null;
    private Class<? extends ServiceFacade> facadeClass = null;
    private InitialContext context = null;

    public JndiNameBuilder withApplication(String application) {
        this.application = application;
        return this;
    }

    public JndiNameBuilder withInitialContext(InitialContext context) {
        this.context = context;
        return this;
    }

    public JndiNameBuilder withFacade(Class<? extends ServiceFacade> facadeClass) {
        this.facadeClass = facadeClass;
        return this;
    }

    public ServiceFacade build() throws NamingException {
        if (this.facadeClass == null) return null;

        InitialContext context;
        StringBuilder jndiBuilder;
        String jndiName;

        context = this.context != null ? this.context : new InitialContext();
        jndiBuilder = new StringBuilder();
        jndiName = jndiBuilder
                .append("java:global/")
                .append(this.application != null ? this.application : "NevSuite-Backend-0.11")
                .append("/")
                .append(facadeClass.getAnnotation(Facade.class).module())
                .append("/")
                .append(facadeClass.getAnnotation(Facade.class).implementation())
                .append("!")
                .append(facadeClass.getName())
                .toString();

        return (ServiceFacade) context.lookup(jndiName);
    }

}
