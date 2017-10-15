package net.naffets.nevsuite.backend.framework.core.base;

import net.naffets.nevsuite.backend.framework.core.api.EntityBean;
import net.naffets.nevsuite.backend.framework.core.api.Reference;

/**
 * @author br4sk4
 * created on 19.04.2016
 */
public abstract class AbstractReference implements Reference {

    protected Long primaryKey;
    protected String uuid;

    protected AbstractReference(EntityBean entityBean) {
        this.primaryKey = entityBean.getPrimaryKey();
        this.uuid = entityBean.getUuid();
    }

    @Override
    public Long getPrimaryKey() {
        return this.primaryKey;
    }

    @Override
    public String getUuid() {
        return this.uuid;
    }

}
