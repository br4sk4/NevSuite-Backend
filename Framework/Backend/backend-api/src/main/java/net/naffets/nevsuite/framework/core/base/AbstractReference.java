package net.naffets.nevsuite.framework.core.base;

import net.naffets.nevsuite.framework.core.api.EntityBean;
import net.naffets.nevsuite.framework.core.api.Reference;

/**
 * @author br4sk4
 * created on 19.04.2016
 */
public abstract class AbstractReference implements Reference {

    protected String primaryKey;

    protected AbstractReference(EntityBean entityBean) {
        this.primaryKey = entityBean.getPrimaryKey();
    }

    @Override
    public String getPrimaryKey() {
        return this.primaryKey;
    }

}
