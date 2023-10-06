package com.github.urisinger.visualscript.config.pages;

import cc.polyfrost.oneconfig.config.elements.OptionSubcategory;
import cc.polyfrost.oneconfig.gui.elements.config.ConfigPageButton;
import cc.polyfrost.oneconfig.gui.pages.Page;
import cc.polyfrost.oneconfig.utils.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class PageListSubCategory extends OptionSubcategory {
    List<ConfigPageButton> pageList = new ArrayList<>();
    public PageListSubCategory(String name, String category) {
        super(name, category);
        addPageList(new VisualGraphPage("page 1"));
        addPageList(new VisualGraphPage("page 2"));
    }

    public void addPageList(Page page){
        pageList.add(new ConfigPageButton(null, null, page.getTitle(), "", null, this.getName(), page));
    }
    @Override
    public int draw(long vg, int x, int y, InputHandler inputHandler) {
        int totalHeight = y;
        for(int i = 0; i < pageList.size(); i++){
            pageList.get(i).draw(vg,x,y - 20 + totalHeight,inputHandler);
            totalHeight += 72;
        }

        return totalHeight;
    }
}
