package com.github.urisinger.visualscript.config.elements;

import cc.polyfrost.oneconfig.gui.elements.BasicButton;
import cc.polyfrost.oneconfig.gui.elements.BasicElement;
import cc.polyfrost.oneconfig.renderer.NanoVGHelper;
import cc.polyfrost.oneconfig.utils.InputHandler;
import cc.polyfrost.oneconfig.utils.color.ColorPalette;
import cc.polyfrost.oneconfig.renderer.font.Fonts;
import net.minecraft.util.Tuple;

import java.util.ArrayList;
import java.util.List;

public class MenuElement extends BasicElement {

    private float x, y;
    private List<Tuple<String, BasicButton>> menuElements;
    public MenuElement(float x, float y, List<String> menuNames) {
        super(150, (menuNames.size())*50, ColorPalette.SECONDARY, false);
        this.menuElements = new ArrayList<>(menuNames.size());
        for(int i = 0; i < menuNames.size(); i++){
            this.menuElements.add(new Tuple<>(menuNames.get(i), new BasicButton(150,50,menuNames.get(i),BasicButton.ALIGNMENT_CENTER,ColorPalette.SECONDARY)));
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(long vg, float x, float y, InputHandler inputHandler) {
        this.update(this.x, this.y, inputHandler);
        NanoVGHelper.INSTANCE.drawRoundedRect(vg, this.x, this.y, width, height, currentColor, radius);

        for(int i = 0; i < menuElements.size(); i++){
            menuElements.get(i).getSecond().draw(vg, this.x, this.y + i*50,inputHandler);
        }

    }

}
