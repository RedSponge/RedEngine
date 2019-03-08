package com.redsponge.redengine.utils;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

/**
 * A class forked from {https://github.com/tecksup/ReEngine2}
 */
public class Discord {

    private Thread thread;
    private DiscordEventHandlers handlers;
    private DiscordRichPresence presence;

    public Discord(String appId, String steamId) {
        init(appId, steamId);
    }

    public Discord(String ApplicationId) {
        init(ApplicationId, "");
    }

    private void init(String appId, String steamId) {
        handlers = new DiscordEventHandlers();
        presence = new DiscordRichPresence();

        DiscordRPC.INSTANCE.Discord_Initialize(appId, handlers, true, steamId);
        presence.details = "Nyeh!";
        presence.state = "Hi";
        presence.largeImageKey = "clouds";
        presence.smallImageKey = "clouds";
        presence.smallImageText = "The Character";
        DiscordRPC.INSTANCE.Discord_UpdatePresence(presence);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    DiscordRPC.INSTANCE.Discord_RunCallbacks();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        DiscordRPC.INSTANCE.Discord_Shutdown();
                        break;
                    }
                }
            }
        }, "RPC-Callback-Handler");
        thread.start();
    }

    public void setPresenceDetails(String detail) {
        presence.details = detail;
    }

    public void setPresenceState(String state) {
        presence.state = state;
    }

    public DiscordRichPresence getPresence() {
        return presence;
    }

    public DiscordEventHandlers getHandlers() {
        return handlers;
    }

    public void updatePresence() {
        DiscordRPC.INSTANCE.Discord_UpdatePresence(presence);
    }

    public void dispose() {
        thread.interrupt();
    }
}