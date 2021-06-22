package io.github.frqnny.tacocraft;

import io.github.frqnny.tacocraft.init.ModBlocks;
import io.github.frqnny.tacocraft.init.ModScreens;
import net.fabricmc.api.ClientModInitializer;

@SuppressWarnings("unused")
public class TacoCraftClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModBlocks.clientInit();
        ModScreens.clientInit();
    }
}
