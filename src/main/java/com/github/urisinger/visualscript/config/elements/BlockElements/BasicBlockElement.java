package com.github.urisinger.visualscript.config.elements.BlockElements;

import cc.polyfrost.oneconfig.gui.elements.BasicElement;
import cc.polyfrost.oneconfig.renderer.NanoVGHelper;
import cc.polyfrost.oneconfig.renderer.font.Fonts;
import cc.polyfrost.oneconfig.utils.InputHandler;
import cc.polyfrost.oneconfig.utils.color.ColorPalette;
import com.google.gson.annotations.Expose;
import javafx.util.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

public class BasicBlockElement extends BasicElement {
    @Expose
    private String name;
    @Expose
    public float x, y;
    @Expose
    private List<Pair<String, BasicBlockElement>> connectedElements = new ArrayList<>();
    private float lastX, lastY;
    private boolean isClicked = false;
    private int selectedElement = -1;

    public BasicBlockElement(String name) {
        super(150 , 50, ColorPalette.PRIMARY, true);
        this.name = name;
        addElement("on finish");
    }
    public void addElement(String key){
        connectedElements.add(new Pair<>(key,null));
        this.height += 20;
    }

    public void draw(long vg, float x, float y, InputHandler inputHandler) {
        this.update(this.x + x, this.y + y, inputHandler);

        if(inputHandler.isClicked() && selectedElement != -1){
            selectedElement = -1;
        }

        //Check for clicked buttons
        for (int i = 0; i < connectedElements.size(); i++) {
            if(inputHandler.isAreaClicked(this.x + x + 140, this.y + y + 45 + i*25, 40, 30)){
                selectedElement = i;
                isClicked = false;
            }
        }

        //Drag element
        if((this.pressed || isClicked) && selectedElement == -1){
            if(!isClicked){
                lastX = inputHandler.mouseX();
                lastY = inputHandler.mouseY();
            }

            this.x += inputHandler.mouseX() - lastX;
            this.y += inputHandler.mouseY() - lastY;

            lastX = inputHandler.mouseX();
            lastY = inputHandler.mouseY();


            if(!inputHandler.isMouseDown()) {
                isClicked = false;
            }
            else{
                isClicked = true;
            }
        }

        //Draw
        NanoVGHelper.INSTANCE.drawRoundedRect(vg, this.x + x, this.y + y, width, height, currentColor, radius);
        NanoVGHelper.INSTANCE.drawRoundedRectVaried(vg, this.x + x, this.y + y, width, 25, new Color(42, 44, 48, 255).getRGB(), radius,radius,0,0);
        NanoVGHelper.INSTANCE.drawText(vg,this.name,this.x + x + 5, this.y + y + 15,Color.WHITE.getRGB(),20f, Fonts.REGULAR);
        for (int i = 0; i < connectedElements.size(); i++) {
            float textWidth = NanoVGHelper.INSTANCE.getTextWidth(vg,connectedElements.get(i).getKey(),18f,Fonts.REGULAR);
            NanoVGHelper.INSTANCE.drawCircle(vg, this.x + x + 140, this.y + y + 45 + 25*i, 5, ColorPalette.SECONDARY.getPressedColor());
            NanoVGHelper.INSTANCE.drawText(vg,connectedElements.get(i).getKey(),this.x + x + 130 - textWidth, this.y + y + 45 + 25*i,Color.WHITE.getRGB(),18f, Fonts.REGULAR);
        }

        if(selectedElement != -1 && connectedElements.get(selectedElement) == null){
            NanoVGHelper.INSTANCE.drawLine(vg,this.x + x + 140,this.y + y + 45 + selectedElement*25, inputHandler.mouseX(), inputHandler.mouseY(),5, Color.DARK_GRAY.getRGB());
        }
    }

}
