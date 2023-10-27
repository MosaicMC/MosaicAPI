package io.github.mosaicmc.mosaicapi;

public class Test extends PluginEntrypoint {
    @Override
    void load() {
        this.getPlugin().logger().info("Test");
    }
}
