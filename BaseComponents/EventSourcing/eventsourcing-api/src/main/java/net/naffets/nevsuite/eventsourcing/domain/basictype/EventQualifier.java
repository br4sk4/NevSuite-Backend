package net.naffets.nevsuite.eventsourcing.domain.basictype;

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
        return this.shortText;
    }

}
