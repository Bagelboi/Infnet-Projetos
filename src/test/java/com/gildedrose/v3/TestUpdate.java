package com.gildedrose.v3;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

public class TestUpdate {

    private Item getUpdatedItem(Item item, int dias) {
        GildedRose gilded =  new GildedRose(new Item[] { item });
        for (int i = 0; i < dias; i++) {
            gilded.updateQuality();
        }
        return gilded.items[0];
    }

    @Test
    public void sulfuras() {
        Item item = getUpdatedItem(
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            2 );
        assertEquals(item.quality, 80);
        assertEquals(item.sellIn, -1);
    }

    @Test
    public void agedBrie() {
        Item item = getUpdatedItem(
            new Item("Aged Brie", -1, 50),
            2 );
        assertEquals(item.quality, 50);
    }

    @Test
    public void backstagePass() {
        Item item = getUpdatedItem(
            new Item("Backstage passes to a TAFKAL80ETC concert", -1, 50),
            2 );
        assertEquals(item.quality, 0);
    }

    @Test
    public void conjured() {
        Item item = getUpdatedItem(
            new Item("Conjured Mana Cake", 2, 50),
            1
        );
        assertEquals(item.sellIn, 0);
    }
}
