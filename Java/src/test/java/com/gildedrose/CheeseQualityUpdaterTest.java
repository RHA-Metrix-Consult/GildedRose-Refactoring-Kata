package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheeseQualityUpdaterTest {
    private Item item;
    private CheeseQualityUpdater cheeseQualityUpdater;

    @BeforeEach
    void setUp() {
        item = new Item("name", 10, 10);
        cheeseQualityUpdater = new CheeseQualityUpdater();
    }

    @Test
    void update() {
        cheeseQualityUpdater.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(11, item.quality);
    }

    @Test
    void updateWithAboveMaxQuality() {
        item.quality = CommonQualityUpdater.MAX_QUALITY;
        cheeseQualityUpdater.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(50, item.quality);
    }

    @Test
    void updateWithBelowMinQuality() {
        item.quality = CommonQualityUpdater.MIN_QUALITY - 10;
        cheeseQualityUpdater.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(0, item.quality);
    }
}