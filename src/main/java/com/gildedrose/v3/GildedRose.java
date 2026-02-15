package com.gildedrose.v3;

import com.gildedrose.Item;
import com.gildedrose.v2.*;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];


            ItemUpdater updater;

            if (item.name.equals("Aged Brie"))
                updater = new AgedBrieUpdater();
            else if (item.name.equals("Sulfuras, Hand of Ragnaros"))
                updater = new SulfurasUpdater();
            else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert"))
                updater = new BackstagePassUpdater();
            else if (item.name.startsWith("Conjured"))
                updater = new ConjuredUpdater();
            else {
                updater = new DefaultUpdater();
            }

            item.sellIn -= 1;

            updater.update(item);


        }
    }

}
