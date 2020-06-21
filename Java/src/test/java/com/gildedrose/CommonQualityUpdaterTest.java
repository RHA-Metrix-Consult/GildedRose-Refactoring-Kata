package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonQualityUpdaterTest {

    private Item item;
    private CommonQualityUpdater commonQualityUpdater;

    @BeforeEach
    void setUp() {
        item = new Item("name", 10, 10);
        commonQualityUpdater = new CommonQualityUpdater();
    }

    @Test
    void update() {
        commonQualityUpdater.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(9, item.quality);
    }

    @Test
    void updateWithOutdated() {
        item.sellIn = 0;
        commonQualityUpdater.update(item);
        assertEquals(-1, item.sellIn);
        assertEquals(8, item.quality);
    }

    @Test
    void updateWithAboveMaxQuality() {
        item.quality = CommonQualityUpdater.MAX_QUALITY + 10;
        commonQualityUpdater.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(50, item.quality);
    }

    @Test
    void updateWithBelowMinQuality() {
        item.quality = CommonQualityUpdater.MIN_QUALITY;
        commonQualityUpdater.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(0, item.quality);
    }
}