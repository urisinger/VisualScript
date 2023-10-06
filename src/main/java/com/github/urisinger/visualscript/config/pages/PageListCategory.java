package com.github.urisinger.visualscript.config.pages;

import cc.polyfrost.oneconfig.config.elements.OptionCategory;


public class PageListCategory extends OptionCategory {

    public PageListCategory(String category) {
        this.subcategories.clear();
        this.subcategories.add(new PageListSubCategory("", category));
    }
}
