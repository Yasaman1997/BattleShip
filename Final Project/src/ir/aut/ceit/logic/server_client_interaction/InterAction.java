package ir.aut.ceit.logic.server_client_interaction;

import ir.aut.ceit.logic.server_client_interaction.messageHandler.BaseMessage;

/**
 * Created by Admin on 7/4/2017.
 */
public class InterAction extends BaseMessage {
    /**
     * Fields are stored into serial bytes in this method.
     */
    @Override
    protected void serialize() {

    }

    /**
     * Fields are restored from serial bytes in this method.
     */
    @Override
    protected void deserialize() {

    }

    /**
     * Return message type code.
     */
    @Override
    public byte getMessageType() {
        return 0;
    }
}
