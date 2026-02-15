package com.gildedrose.v1;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private void updateAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
        if (item.sellIn < 0) {
            item.quality += 1;
        }
    }

    private void updateSulfuras(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
            if (item.sellIn < 11)
                item.quality += 1;
            if (item.sellIn < 6)
                item.quality += 1;
        }

        item.sellIn += 1;
    }

    private void updateBackstage(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
        if (item.sellIn < 0)
            item.quality -= item.quality;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            item.sellIn -= 1;

            if (item.name.equals("Aged Brie"))
                updateAgedBrie(item);
            else if (item.name.equals("Sulfuras, Hand of Ragnaros"))
                updateSulfuras(item);
            else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert"))
                updateBackstage(item);
            else {
                if (item.quality > 0) {
                    item.quality -= 1;
                    if (item.sellIn < 0)
                        item.quality -= 1;
                }

            }


        }
    }

}
