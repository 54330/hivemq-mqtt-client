package org.mqttbee.mqtt.message.auth;

import org.mqttbee.annotations.NotNull;
import org.mqttbee.annotations.Nullable;
import org.mqttbee.api.mqtt.mqtt5.message.auth.Mqtt5Auth;
import org.mqttbee.api.mqtt.mqtt5.message.auth.Mqtt5AuthReasonCode;
import org.mqttbee.mqtt.codec.encoder.provider.MqttMessageEncoderProvider;
import org.mqttbee.mqtt.datatypes.MqttUTF8StringImpl;
import org.mqttbee.mqtt.datatypes.MqttUserPropertiesImpl;
import org.mqttbee.mqtt.message.MqttMessageWithUserProperties.MqttMessageWithReasonCode;
import org.mqttbee.util.ByteBufferUtil;

import javax.annotation.concurrent.Immutable;
import java.nio.ByteBuffer;
import java.util.Optional;

/**
 * @author Silvio Giebl
 */
@Immutable
public class MqttAuth
        extends MqttMessageWithReasonCode<MqttAuth, Mqtt5AuthReasonCode, MqttMessageEncoderProvider<MqttAuth>>
        implements Mqtt5Auth {

    private final MqttUTF8StringImpl method;
    private final ByteBuffer data;

    public MqttAuth(
            @NotNull final Mqtt5AuthReasonCode reasonCode, @NotNull final MqttUTF8StringImpl method,
            @Nullable final ByteBuffer data, @Nullable final MqttUTF8StringImpl reasonString,
            @NotNull final MqttUserPropertiesImpl userProperties,
            @NotNull final MqttMessageEncoderProvider<MqttAuth> encoderProvider) {

        super(reasonCode, reasonString, userProperties, encoderProvider);
        this.method = method;
        this.data = data;
    }

    @NotNull
    @Override
    public MqttUTF8StringImpl getMethod() {
        return method;
    }

    @NotNull
    @Override
    public Optional<ByteBuffer> getData() {
        return ByteBufferUtil.optionalReadOnly(data);
    }

    @Nullable
    public ByteBuffer getRawData() {
        return data;
    }

    @NotNull
    @Override
    protected MqttAuth getCodable() {
        return this;
    }

}
