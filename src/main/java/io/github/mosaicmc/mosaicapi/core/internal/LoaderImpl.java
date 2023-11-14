package io.github.mosaicmc.mosaicapi.core.internal;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import io.github.mosaicmc.mosaicapi.core.api.Loader;
import io.github.mosaicmc.mosaicapi.core.api.PluginContainer;
import lombok.Getter;
import lombok.val;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * Internal class, used for plugin loading.
 */
@Getter
public final class LoaderImpl implements Loader {
    public static final Logger logger = LoggerFactory.getLogger("MosaicAPI");
    @Nullable
    private static LoaderImpl INSTANCE;

    private final PluginManagerImpl pluginManager;
    private final EventManagerImpl eventManager;
    private final MinecraftServer server;

    private LoaderImpl(MinecraftServer server) {
        this.server = server;
        this.pluginManager = new PluginManagerImpl();
        this.eventManager = new EventManagerImpl(loadPlugins(pluginManager.getPlugins()));
        load();
    }

    public static LoaderImpl getInstance() {
        assert INSTANCE != null;
        return INSTANCE;
    }

    public static LoaderImpl initialize(MinecraftServer server) {
        return INSTANCE = new LoaderImpl(server);
    }

    public static boolean isLoaded() {
        return INSTANCE != null;
    }

    private void load() {
        logger.info("Loaded plugins: {}", pluginManager.getPlugins().keySet());
    }

    private BiMap<SubscriberRegistryImpl, EventRegistryImpl> loadPlugins(BiMap<String, PluginContainer> plugins) {
        val registryMap = ImmutableBiMap.<SubscriberRegistryImpl, EventRegistryImpl>builderWithExpectedSize(plugins.size());

        plugins.values().forEach(loadPlugin(registryMap));

        return registryMap.build();
    }

    private Consumer<PluginContainer> loadPlugin(ImmutableBiMap.Builder<SubscriberRegistryImpl, EventRegistryImpl> registryMap) {
        return (plugin) -> {
            val pluginContainer = (PluginContainerImpl) plugin;
            val subscriberRegistry = new SubscriberRegistryImpl(pluginContainer);
            val eventRegistry = new EventRegistryImpl(pluginContainer);

            pluginContainer.getEntrypoint().registerEvent(eventRegistry);
            pluginContainer.getEntrypoint().onLoad(subscriberRegistry);

            registryMap.put(subscriberRegistry, eventRegistry);
        };
    }
}
