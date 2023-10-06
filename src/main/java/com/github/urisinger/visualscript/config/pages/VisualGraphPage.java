package com.github.urisinger.visualscript.config.pages;

import cc.polyfrost.oneconfig.gui.pages.Page;
import cc.polyfrost.oneconfig.utils.InputHandler;
import com.github.urisinger.visualscript.config.elements.BlockElements.BasicBlockElement;
import com.github.urisinger.visualscript.config.elements.MenuElement;
import com.google.gson.annotations.Expose;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;

public class VisualGraphPage extends Page {

    @Expose
    public BasicBlockElement test = new BasicBlockElement("Main");
    @Expose
    public float scrollX = 0, scrollY = 0;

    private MenuElement menuElement = null;

    private boolean wasClicked = false;
    private boolean isClicked = false;

    private float lastX, lastY;

    public VisualGraphPage(String title) {
        super(title);
    }

    public void draw(long vg, int x, int y, InputHandler inputHandler) {
        if(inputHandler.isMouseDown(1)){

            if(menuElement != null && !wasClicked){
                menuElement = null;
            }
            else if(!wasClicked){
                menuElement = new MenuElement(inputHandler.mouseX(), inputHandler.mouseY(), Arrays.asList("new block", "option 2","option 3"));
            }
            wasClicked = true;
        }
        else{
            wasClicked = false;
        }


        if(inputHandler.isMouseDown(0)){
            menuElement = null;

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

        test.draw(vg,scrollX,scrollY,inputHandler);
        if(menuElement != null){
            menuElement.draw(vg,scrollX,scrollY,inputHandler);
        }

    }

    @Override
    public int getMaxScrollHeight(){
        return 0;
    }
}
