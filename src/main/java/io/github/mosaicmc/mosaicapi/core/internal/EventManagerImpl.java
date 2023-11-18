package io.github.mosaicmc.mosaicapi.core.internal;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;
import io.github.mosaicmc.mosaicapi.core.api.Event;
import io.github.mosaicmc.mosaicapi.core.api.EventManager;
import io.github.mosaicmc.mosaicapi.core.api.PluginContainer;
import io.github.mosaicmc.mosaicapi.core.api.SubscriberContainer;
import io.github.mosaicmc.mosaicapi.utils.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Internal class, used for event management.
 */
final class EventManagerImpl implements EventManager {
    private final BiMap<Type<?>, ContainerWrapper> handlerMap;

    EventManagerImpl(BiMap<String, ? super PluginContainer> plugins) {
        this.handlerMap = getRegistriesFromPlugins(plugins);
    }

    private BiMap<Type<?>, ContainerWrapper> getRegistriesFromPlugins(BiMap<String, ? super PluginContainerImpl> plugins) {
        val builder = new ConcurrentHashMap<Type<?>, ContainerWrapper>();

        plugins.values().parallelStream().forEach(plugin -> {
            val subscribersBuilder = ConcurrentHashMap.<SubscriberRegistryImpl>newKeySet();
            val eventsBuilder = ConcurrentHashMap.<EventRegistryImpl>newKeySet();
            
            loadPluginRegistries((PluginContainerImpl) plugin, subscribersBuilder, eventsBuilder);
            registerEvents(builder, ImmutableSet.copyOf(eventsBuilder));
            registerSubscribers(builder, ImmutableSet.copyOf(subscribersBuilder));
        });

        return ImmutableBiMap.copyOf(builder);
    }

    private void loadPluginRegistries(PluginContainerImpl plugin, Set<SubscriberRegistryImpl> subscribersBuilder, Set<EventRegistryImpl> eventsBuilder) {
        val subscriberRegistry = new SubscriberRegistryImpl(plugin);
        val eventRegistry = new EventRegistryImpl(plugin);

        plugin.getEntrypoints().forEach((entrypoint -> entrypoint.registerEvent(eventRegistry))) ;
        plugin.getEntrypoints().forEach((entrypoint -> entrypoint.registerSubscribers(subscriberRegistry)));

        subscribersBuilder.add(subscriberRegistry);
        eventsBuilder.add(eventRegistry);
    }

    private void registerEvents(Map<Type<?>, ContainerWrapper> builder, Set<EventRegistryImpl> eventRegs) {
        eventRegs.stream()
                .flatMap(registry -> registry.getEvents().entrySet().parallelStream())
                .forEach(entry -> builder.put(entry.getKey(), new ContainerWrapper(entry.getValue(), ConcurrentHashMap.newKeySet())));
    }

    private void registerSubscribers(Map<Type<?>, ContainerWrapper> builder, Set<SubscriberRegistryImpl> subscriberRegs) {
        subscriberRegs.parallelStream()
                .flatMap(registry -> registry.getSubscribers().entrySet().stream())
                .forEach(entry -> {
                    val type = entry.getKey();
                    val sub = entry.getValue();
                    val eventContainer = builder.get(type);
                    if (eventContainer == null) {
                        throw new IllegalStateException("Not registered event: " + type);
                    }
                    eventContainer.subscribers.add(sub);
                });
    }

    @Override
    public <T extends Event<T>> void callEvent(T event) {
        val container = handlerMap.get(event.getType());

        if (container == null) throw new IllegalStateException("Not registered event: " + event.getType().getName());
        if (container.subscribers.isEmpty()) return;

        val subs = container.<T>getCastedSubscribers();
        val eventContainer = container.<T>getCastedEventContainer();

        eventContainer.getConsumer().accept(event, subs);

        LoaderImpl.logger.debug("Called event: {}", event.getType().getName());
    }

    @Data
    static class ContainerWrapper {
        final EventContainerImpl<? extends Event<?>> eventContainer;
        @EqualsAndHashCode.Exclude
        final Set<SubscriberContainerImpl<? extends Event<?>>> subscribers;

        @SuppressWarnings("unchecked")
        <T extends Event<T>> EventContainerImpl<T> getCastedEventContainer() {
            return (EventContainerImpl<T>) eventContainer;
        }

        @SuppressWarnings("unchecked")
        <T extends Event<T>> Set<SubscriberContainer<T>> getCastedSubscribers() {
            return (Set<SubscriberContainer<T>>) (Object) subscribers;
        }
    }
}
