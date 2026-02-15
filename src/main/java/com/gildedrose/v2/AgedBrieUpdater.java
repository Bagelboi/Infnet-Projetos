package com.gildedrose.v2;

import com.gildedrose.Item;

public class AgedBrieUpdater implements ItemUpdater{
    @Override
    public void update(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
            if (item.sellIn < 0) {
                item.quality += 1;
            }
        }
    }
}
