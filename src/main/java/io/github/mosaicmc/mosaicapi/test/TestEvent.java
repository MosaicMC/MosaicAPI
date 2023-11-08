package io.github.mosaicmc.mosaicapi.test;

import io.github.mosaicmc.mosaicapi.api.Event;
import io.github.mosaicmc.mosaicapi.utils.Type;

public final class TestEvent extends Event<TestEvent> {
    public static final Type<TestEvent> type = Type.from(TestEvent.class);

    @Override
    public Type<TestEvent> getType() {
        return type;
    }
}
