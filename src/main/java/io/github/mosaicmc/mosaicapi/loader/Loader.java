package io.github.mosaicmc.mosaicapi.loader;

import com.google.common.collect.BiMap;
import io.github.mosaicmc.mosaicapi.event.Event;
import io.github.mosaicmc.mosaicapi.event.EventHandler;
import io.github.mosaicmc.mosaicapi.event.EventHandlerImpl;
import io.github.mosaicmc.mosaicapi.mc.MosaicServer;

public abstract sealed class Loader permits LoaderImpl {
    protected final MosaicServer server;
    protected final BiMap<String, PluginContainer> pluginsCache;
    protected final EventHandler eventHandler;

    protected Loader(MosaicServer server) {
        this.server = server;
        this.pluginsCache = createPluginsMap();
        this.eventHandler = new EventHandlerImpl();
        loadPlugins();
    }

    public static Loader of(MosaicServer server) {
        return new LoaderImpl(server);
    }

    public abstract void unloadPlugin(String id);

    public void unloadPlugin(PluginContainer pluginContainer) {
        unloadPlugin(pluginContainer.id);
    }

    protected abstract BiMap<String, PluginContainer> createPluginsMap();

    protected abstract void loadPlugins();

    public abstract <T extends Event<T>> void fireEvent(T event);
}
