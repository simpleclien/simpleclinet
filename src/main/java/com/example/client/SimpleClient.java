package com.example.client;

import com.example.client.gui.ClickGuiScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class SimpleClient implements ClientModInitializer {
    public static boolean freecamEnabled = false;
    public static boolean storageEspEnabled = false;
    public static boolean spawnerFinderEnabled = false;
    public static boolean playerEspEnabled = false;

    private static KeyBinding guiKey;
    private static KeyBinding freecamKey;
    private static KeyBinding storageEspKey;
    private static KeyBinding spawnerFinderKey;
    private static KeyBinding playerEspKey;

    @Override
    public void onInitializeClient() {
        guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simpleclient.gui", InputUtil.Type.KEY_SYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "category.simpleclient"));
        freecamKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simpleclient.freecam", InputUtil.Type.KEY_SYM, GLFW.GLFW_KEY_F, "category.simpleclient"));
        storageEspKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simpleclient.storage_esp", InputUtil.Type.KEY_SYM, GLFW.GLFW_KEY_G, "category.simpleclient"));
        spawnerFinderKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simpleclient.spawner_finder", InputUtil.Type.KEY_SYM, GLFW.GLFW_KEY_H, "category.simpleclient"));
        playerEspKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simpleclient.player_esp", InputUtil.Type.KEY_SYM, GLFW.GLFW_KEY_J, "category.simpleclient"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            while (guiKey.wasPressed()) {
                client.setScreen(new ClickGuiScreen());
            }
            while (freecamKey.wasPressed()) {
                freecamEnabled = !freecamEnabled;
                client.player.sendMessage(Text.literal("Freecam: " + (freecamEnabled ? "§aON" : "§cOFF")), true);
            }
            while (storageEspKey.wasPressed()) {
                storageEspEnabled = !storageEspEnabled;
                client.player.sendMessage(Text.literal("Storage ESP: " + (storageEspEnabled ? "§aON" : "§cOFF")), true);
            }
            while (spawnerFinderKey.wasPressed()) {
                spawnerFinderEnabled = !spawnerFinderEnabled;
                client.player.sendMessage(Text.literal("Spawner Finder: " + (spawnerFinderEnabled ? "§aON" : "§cOFF")), true);
            }
            while (playerEspKey.wasPressed()) {
                playerEspEnabled = !playerEspEnabled;
                client.player.sendMessage(Text.literal("Player ESP: " + (playerEspEnabled ? "§aON" : "§cOFF")), true);
            }
        });
    }
}
