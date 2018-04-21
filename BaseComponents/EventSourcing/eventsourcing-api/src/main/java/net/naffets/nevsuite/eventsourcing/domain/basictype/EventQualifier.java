package net.naffets.nevsuite.eventsourcing.domain.basictype;

import java.util.ResourceBundle;

/**
 * @author br4sk4
 * created on 08.07.2016
 */
public enum EventQualifier {

    E_100("notification.test"),
    E_101("backgroundprocess.start"),
    E_102("backgroundprocess.end");

    private String shortText;

    EventQualifier(String shortText) {
        this.shortText = shortText;
    }

    public String getShortText() {
        ResourceBundle resource = ResourceBundle.getBundle("eventsourcing");
        return resource.getString(this.shortText);
    }

}
