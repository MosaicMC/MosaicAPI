package io.github.mosaicmc.mosaicapi.event;

final class EventRegistryBuilderImpl extends EventRegistryBuilder {

    @Override
    public <T extends Event<T>> void registerEvent(Class<T> eventClass) {
        //noinspection unchecked
        this.events.add((Class<Event<?>>) eventClass);
    }
}
