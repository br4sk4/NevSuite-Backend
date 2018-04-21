package net.naffets.nevsuite.framework.core.base;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.naffets.nevsuite.framework.core.api.EntityBean;
import net.naffets.nevsuite.framework.core.api.Reference;

/**
 * @author br4sk4
 * created on 19.04.2016
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseReference implements Reference {

    protected String primaryKey;
    protected String typeDiscriminator;

    public BaseReference(String primaryKey, String typeDiscriminator) {
        this.primaryKey = primaryKey;
        this.typeDiscriminator = typeDiscriminator;
    }

    protected BaseReference(EntityBean entityBean) {
        this.primaryKey = entityBean.getPrimaryKey();
        this.typeDiscriminator = entityBean.getClass().getSimpleName();
    }

    @Override
    public String getPrimaryKey() {
        return this.primaryKey;
    }

    @Override
    public String getRepresentableName() {
        return this.getTypeDiscriminator() + " : " + this.getPrimaryKey();
    }

    @Override
    public String getTypeDiscriminator() {
        return typeDiscriminator;
    }
}
