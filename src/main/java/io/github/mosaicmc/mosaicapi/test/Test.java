package io.github.mosaicmc.mosaicapi.test;

import io.github.mosaicmc.mosaicapi.api.IEventRegistry;
import io.github.mosaicmc.mosaicapi.api.ISubscriberRegistry;
import io.github.mosaicmc.mosaicapi.api.PluginEntrypoint;

public class Test extends PluginEntrypoint {
    @Override
    public void onLoad(ISubscriberRegistry sr) {
        this.getPlugin().getLogger().info("{}", this.getPlugin().getId());
        sr.subscribe(TestEvent.type, event -> getPlugin().getLogger().info("{}", event.getType()));
    }

    @Override
    public void registerEvent(IEventRegistry er) {
        er.register(TestEvent.type);
    }
}
