package org.mqttbee.mqtt.message.publish.pubcomp;

import org.mqttbee.annotations.NotNull;
import org.mqttbee.annotations.Nullable;
import org.mqttbee.api.mqtt.mqtt5.message.publish.pubcomp.Mqtt5PubComp;
import org.mqttbee.api.mqtt.mqtt5.message.publish.pubcomp.Mqtt5PubCompReasonCode;
import org.mqttbee.mqtt.codec.encoder.provider.MqttMessageEncoderProvider;
import org.mqttbee.mqtt.datatypes.MqttUTF8StringImpl;
import org.mqttbee.mqtt.datatypes.MqttUserPropertiesImpl;
import org.mqttbee.mqtt.message.MqttMessageWithUserProperties.MqttMessageWithIdAndReasonCode;
import org.mqttbee.mqtt.message.publish.MqttQoSMessage;

import javax.annotation.concurrent.Immutable;

/**
 * @author Silvio Giebl
 */
@Immutable
public class MqttPubComp extends
        MqttMessageWithIdAndReasonCode<MqttPubComp, Mqtt5PubCompReasonCode, MqttMessageEncoderProvider<MqttPubComp>>
        implements Mqtt5PubComp, MqttQoSMessage {

    @NotNull
    public static final Mqtt5PubCompReasonCode DEFAULT_REASON_CODE = Mqtt5PubCompReasonCode.SUCCESS;

    public MqttPubComp(
            final int packetIdentifier, @NotNull final Mqtt5PubCompReasonCode reasonCode,
            @Nullable final MqttUTF8StringImpl reasonString, @NotNull final MqttUserPropertiesImpl userProperties,
            @NotNull final MqttMessageEncoderProvider<MqttPubComp> encoderProvider) {

        super(packetIdentifier, reasonCode, reasonString, userProperties, encoderProvider);
    }

    @NotNull
    @Override
    protected MqttPubComp getCodable() {
        return this;
    }

}
