package com.gildedrose.v2;

import com.gildedrose.Item;

public class SulfurasUpdater implements ItemUpdater{
    @Override
    public void update(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
            if (item.sellIn < 11)
                item.quality += 1;
            if (item.sellIn < 6)
                item.quality += 1;
        }

        item.sellIn += 1;
    }
}
