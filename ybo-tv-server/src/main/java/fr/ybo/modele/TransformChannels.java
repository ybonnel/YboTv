package fr.ybo.modele;

import com.google.common.base.Function;
import fr.ybo.xmltv.Channel;

import java.io.Serializable;

public class TransformChannels implements Function<Channel, ChannelForMemCache>, Serializable {

    @Override
    public ChannelForMemCache apply(Channel channel) {
        return ChannelForMemCache.fromChannel(channel);
    }
}
