package net.naffets.nevsuite.backend.framework.domain.entity;

import lombok.*;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.framework.core.base.BaseReference;
import org.springframework.util.DigestUtils;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author br4sk4 / created on 12.10.2017
 */
@Entity
@Table(name = "T_USER")
@AttributeOverride(name = "primaryKey", column = @Column(name = "user_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class User extends AbstractEntityBean {

    @Column(name = "user_nickname")
    private String nickName;

    @Column(name = "user_password")
    private String passwordHash;

    @Builder
    public User(String primaryKey,
                String nickName,
                String password) {
        super(primaryKey);
        this.nickName = nickName;
        this.passwordHash = DigestUtils.md5DigestAsHex(password.getBytes());
    }

    @Override
    public Reference asReference() {
        return new BaseReference(this) {
            @Override
            public String getRepresentableName() {
                return nickName;
            }
        };
    }
}