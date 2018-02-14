package org.mqttbee.mqtt5.codec.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.mqttbee.annotations.NotNull;
import org.mqttbee.api.mqtt5.message.disconnect.Mqtt5DisconnectReasonCode;
import org.mqttbee.mqtt5.Mqtt5ClientDataImpl;
import org.mqttbee.mqtt5.message.ping.Mqtt5PingRespImpl;

import javax.inject.Singleton;

import static org.mqttbee.mqtt5.codec.decoder.Mqtt5MessageDecoderUtil.disconnect;
import static org.mqttbee.mqtt5.codec.decoder.Mqtt5MessageDecoderUtil.disconnectWrongFixedHeaderFlags;
import static org.mqttbee.mqtt5.message.ping.Mqtt5PingRespImpl.INSTANCE;

/**
 * @author Silvio Giebl
 */
@Singleton
public class Mqtt5PingRespDecoder implements Mqtt5MessageDecoder {

    private static final int FLAGS = 0b0000;

    @Override
    public Mqtt5PingRespImpl decode(
            final int flags, @NotNull final ByteBuf in, @NotNull final Mqtt5ClientDataImpl clientData) {
        final Channel channel = clientData.getChannel();

        if (flags != FLAGS) {
            disconnectWrongFixedHeaderFlags("PING", channel);
            return null;
        }

        if (in.readableBytes() != 0) {
            disconnect(Mqtt5DisconnectReasonCode.MALFORMED_PACKET, "PING must not have a variable header or payload",
                    channel);
            return null;
        }

        return INSTANCE;
    }

}
