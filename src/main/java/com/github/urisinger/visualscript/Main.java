package com.github.urisinger.visualscript;

import com.github.urisinger.visualscript.config.ModConfig;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
    import com.google.gson.Gson;

@Mod(modid = "visualscript", useMetadata=true)
public class Main {
    public static ModConfig config;
    public static Gson gson;
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        config = new ModConfig();
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }
}
