package net.naffets.nevsuite.backend.framework.domain.entity;

import lombok.*;
import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.framework.core.base.BaseReference;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author br4sk4 / created on 12.10.2017
 */
@Entity
@Table(name = "T_USER")
@AttributeOverride(name = "primaryKey", column = @Column(name = "user_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class User extends AbstractEntityBean implements Serializable {

    @Column(name = "user_nickname")
    private String nickName;

    @Column(name = "user_password")
    private String passwordHash;

    @Builder
    public User(String nickName,
                String password) {
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
