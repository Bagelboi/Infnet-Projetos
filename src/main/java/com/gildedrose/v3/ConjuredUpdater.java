package com.gildedrose.v3;

import com.gildedrose.Item;
import com.gildedrose.v2.ItemUpdater;

public class ConjuredUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.sellIn -= 1;
    }
}
