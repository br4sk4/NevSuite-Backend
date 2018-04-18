package net.naffets.nevsuite.backgroundprocesses.domain.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;
import net.naffets.nevsuite.framework.core.api.DataTransferObject;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @author br4sk4
 * created on 17.04.18
 */
@XmlType(propOrder = {
        "primaryKey",
        "identifier",
        "type",
        "derivationType",
        "persistence",
        "periodicity",
        "blockSize",
        "rasterType"
})
@XmlRootElement(name = "backgroundprocess")
@NoArgsConstructor
public class BackgroundProcessDTO implements Serializable, DataTransferObject {

    private final static long serialVersionUID = 1L;

    public String primaryKey;
    public String name;
    public String start;
    public String end;
    public String status;

    @Builder
    public BackgroundProcessDTO(
            String primaryKey,
            String name,
            String start,
            String end,
            String status) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.start = start;
        this.end = end;
        this.status = status;
    }
}
