package com.gildedrose.v3;


import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

public class ConjuredTest {

    public static void main(String[] args) {
        Item[] items = new Item[]{
            new Item("Conjured", 10, 80),
            new Item("Sulfuras, Hand of Ragnaros", 10, 80)
        };

        GildedRose app = new GildedRose(items);

        int days = 2;

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }

}
