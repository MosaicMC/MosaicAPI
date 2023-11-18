package io.github.mosaicmc.mosaicapi.test;

import io.github.mosaicmc.mosaicapi.core.api.EventRegistry;
import io.github.mosaicmc.mosaicapi.core.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.core.api.PluginEntrypoint;
import io.github.mosaicmc.mosaicapi.core.api.SubscriberRegistry;

import java.util.concurrent.atomic.AtomicInteger;

public class Test extends PluginEntrypoint {
    @Override
    public void load(PluginContainer plugin) {
        getPlugin().getLogger().info("{} load", getPlugin().getId());
    }

    @Override
    public void registerSubscribers(SubscriberRegistry sr) {
        getPlugin().getLogger().info("{} register", getPlugin().getId());
        sr.subscribe(TestEvent.type, event -> {
            getPlugin().getLogger().info("{}", integer.incrementAndGet());
        });
    }

    static AtomicInteger integer = new AtomicInteger(0);

    @Override
    public void registerEvent(EventRegistry er) {
        er.registerParallel(TestEvent.type);
    }
}
