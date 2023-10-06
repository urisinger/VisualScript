package com.github.urisinger.visualscript.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.CustomOption;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.elements.BasicOption;
import cc.polyfrost.oneconfig.config.elements.OptionPage;
import com.github.urisinger.visualscript.config.pages.PageListCategory;

import java.lang.reflect.Field;


public class ModConfig extends Config {
    public ModConfig() {
        super(new Mod("Page test", ModType.SKYBLOCK), "VisualMacros.json");
        initialize();
    }


    @CustomOption(id = "erm! what the flip...")
    public static boolean throwaway = true;

    @Override
    protected BasicOption getCustomOption(Field field, CustomOption annotation, OptionPage page, Mod mod, boolean migrate) {
        //inject the custom category into oneconfig
        page.categories.put("Macro list page", new PageListCategory("Macro list page"));
        return null;
    }
}
