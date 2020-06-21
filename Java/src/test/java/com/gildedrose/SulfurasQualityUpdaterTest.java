package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SulfurasQualityUpdaterTest {

    @Test
    void update() {
        Item sulfurasItem = new Item(GildedRose.SULFURAS_HAND_OF_RAGNAROS, 50, 80);
        new SulfurasQualityUpdater().update(sulfurasItem);
        assertEquals(50, sulfurasItem.sellIn);
        assertEquals(80, sulfurasItem.quality);
    }
}