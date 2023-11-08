package io.github.mosaicmc.mosaicapi.test;

import io.github.mosaicmc.mosaicapi.api.PluginEntrypoint;
import io.github.mosaicmc.mosaicapi.internal.EventRegistry;
import io.github.mosaicmc.mosaicapi.internal.SubscriberRegistry;

public class Test extends PluginEntrypoint {
    @Override
    public void onLoad(SubscriberRegistry sr) {
        this.getPlugin().getLogger().info("{}", this.getPlugin().getId());
        sr.subscribe(TestEvent.type, event -> getPlugin().getLogger().info("{}", event.getType()));
    }

    @Override
    public void registerEvent(EventRegistry er) {
        er.register(TestEvent.type);
    }
}
