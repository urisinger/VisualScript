package com.github.urisinger.visualscript.config.pages;

import cc.polyfrost.oneconfig.gui.pages.Page;
import cc.polyfrost.oneconfig.utils.InputHandler;
import com.github.urisinger.visualscript.config.elements.BlockElements.BasicBlockElement;
import com.github.urisinger.visualscript.config.elements.MenuElement;
import com.google.gson.annotations.Expose;
import net.minecraft.util.Tuple;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisualGraphPage extends Page {

    @Expose
    public List<BasicBlockElement> elements = new ArrayList<>();
    @Expose
    public float scrollX = 0, scrollY = 0;

    private MenuElement menuElement = null;

    private boolean wasClicked = false;
    private boolean isClicked = false;

    private float lastX, lastY;

    public VisualGraphPage(String title) {
        super(title);
        elements.add(new BasicBlockElement("On start"));
    }

    public void draw(long vg, int x, int y, InputHandler inputHandler) {

        if(inputHandler.isMouseDown(1)){
            if(!wasClicked){
                if(menuElement != null){
                    menuElement = null;
                }
                else {
                    menuElement = new MenuElement(inputHandler.mouseX(), inputHandler.mouseY(), Arrays.asList(new Tuple<>("New block", () -> this.elements.add(new BasicBlockElement(inputHandler.mouseX()-x,inputHandler.mouseY()-y    ,"wow")))));
                }
            }
            wasClicked = true;
        }
        else{
            wasClicked = false;
        }


        if(inputHandler.isMouseDown(0)){

            if(Keyboard.isKeyDown(Keyboard.KEY_LMENU)){
                if(!isClicked){
                    lastX = inputHandler.mouseX();
                    lastY = inputHandler.mouseY();
                    isClicked = true;

                }

                this.scrollX += (inputHandler.mouseX() - lastX);
                this.scrollY += (inputHandler.mouseY() - lastY);

                lastX = inputHandler.mouseX();
                lastY = inputHandler.mouseY();

            }
            else{
                isClicked = false;
            }
        }else{
            isClicked = false;
        }

        for (BasicBlockElement blockElement : elements) {
            blockElement.draw(vg, scrollX, scrollY, inputHandler);
        }
        if(menuElement != null){
            menuElement.draw(vg,scrollX,scrollY,inputHandler);
        }
        if(inputHandler.isMouseDown(0)){
            menuElement = null;
        }
    }

    @Override
    public int getMaxScrollHeight(){
        return 0;
    }
}
