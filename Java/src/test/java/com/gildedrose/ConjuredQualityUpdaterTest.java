package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConjuredQualityUpdaterTest {

    private Item item;
    private ConjuredQualityUpdater conjuredQualityUpdater;

    @BeforeEach
    void setUp() {
        item = new Item("name", 10, 10);
        conjuredQualityUpdater = new ConjuredQualityUpdater();
    }

    @Test
    void update() {
        conjuredQualityUpdater.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(8, item.quality);
    }

    @Test
    void updateWithOutdated() {
        item.sellIn = 0;
        conjuredQualityUpdater.update(item);
        assertEquals(-1, item.sellIn);
        assertEquals(6, item.quality);
    }

    @Test
    void updateWithAboveMaxQuality() {
        item.quality = ConjuredQualityUpdater.MAX_QUALITY + 10;
        conjuredQualityUpdater.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(50, item.quality);
    }

    @Test
    void updateWithBelowMinQuality() {
        item.quality = ConjuredQualityUpdater.MIN_QUALITY - 10;
        conjuredQualityUpdater.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(0, item.quality);
    }

}