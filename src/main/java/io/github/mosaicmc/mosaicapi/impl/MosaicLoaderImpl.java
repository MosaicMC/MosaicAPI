package io.github.mosaicmc.mosaicapi.impl;

import io.github.mosaicmc.mosaicapi.api.MosaicLoader;
import io.github.mosaicmc.mosaicapi.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.api.PluginManager;
import io.github.mosaicmc.mosaicapi.api.event.EventManager;
import io.github.mosaicmc.mosaicapi.api.mc.Server;
import io.github.mosaicmc.mosaicapi.impl.event.EventManagerImpl;
import io.github.mosaicmc.mosaicapi.impl.event.EventRegistryImpl;
import io.github.mosaicmc.mosaicapi.impl.event.SubscriberRegistryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

public final class MosaicLoaderImpl implements MosaicLoader {
    private final Server server;
    private final Logger logger;
    private final PluginManager pluginManager;
    private final EventManager eventManager;

    public MosaicLoaderImpl(Server server) {
        this.server = server;
        this.logger = LoggerFactory.getLogger("MosaicAPI");
        this.pluginManager = new PluginManagerImpl(this);
        this.eventManager = new EventManagerImpl();
        try {
            print();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        onServerLoad();
    }
    void print() throws InterruptedException, IOException {
        final Random rnd = new Random();
        final int progressBarSize = 50;
        final int totalPercentage = 100;
        final int progressIncrement = totalPercentage / progressBarSize;

        StringBuilder progressBar = new StringBuilder(" ".repeat(progressBarSize));

        for (int i = 0; i <= totalPercentage; i++) {
            if (i >= progressIncrement && i % progressIncrement == 0) {
                progressBar.setCharAt(i / progressIncrement - 1, '#');
            }
            Thread.sleep(rnd.nextInt(50,400));
            System.out.print("<|" + progressBar + "|> %" + i + "\r");
        }
        System.out.println();
        System.out.println("Done!");
    }


    private void onServerLoad() {
        logger.info("Loading Plugins...");
        pluginManager.craftPlugins(server);
        var pluginIterator = pluginManager.getPlugins();
        pluginIterator.forEach(this::setContainer);
        pluginIterator.forEach(this::registerEvents);
        pluginIterator.forEach(this::registerPlugins);
    }

    private void setContainer(PluginContainer container) {
        container.getInitializers().forEach(init -> init.setPluginContainer(container));
    }

    private void registerPlugins(PluginContainer container) {
        final var subscriberRegistry = new SubscriberRegistryImpl(container);
        container.getInitializers().forEach(
                init -> init.onEnable(subscriberRegistry)
        );
        eventManager.registerSubscribers(subscriberRegistry);
    }

    private void registerEvents(PluginContainer container) {
        final var eventRegistry = new EventRegistryImpl(container);
        container.getInitializers().forEach(
                init -> init.eventRegister(eventRegistry)
        );
        eventManager.registerEvents(eventRegistry);
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }
}
