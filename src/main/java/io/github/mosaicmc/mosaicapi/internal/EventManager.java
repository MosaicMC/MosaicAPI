package io.github.mosaicmc.mosaicapi.internal;

import io.github.mosaicmc.mosaicapi.utils.InitHelper;
import io.github.mosaicmc.mosaicapi.utils.Type;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

@ApiStatus.Internal
public final class EventManager {
    private static final InitHelper<EventManager> INSTANCE;

    static {
        INSTANCE = new InitHelper<>();
    }

    @Nullable
    private Map<Type<?>, Set<SubscriberContainer<?>>> eventRegistries;

    private EventManager(Set<EventRegistry> eventRegistries, Set<SubscriberRegistry> subscriberRegistries) {
        // TODO Auto-generated constructor stub
    }

    static EventManager init(Map<SubscriberRegistry, EventRegistry> registryMap) {
        return INSTANCE.initialize(() -> {
            final var values = Set.copyOf(registryMap.values());
            final var keys = registryMap.keySet();
            return new EventManager(values, keys);
        });
    }
}
