package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackstagePassesQualityUpdaterTest {

    private Item item;
    private BackstagePassesQualityUpdater backstagePassesQualityUpdater;

    @BeforeEach
    void setUp() {
        item = new Item("name", 15, 10);
        backstagePassesQualityUpdater = new BackstagePassesQualityUpdater();
    }

    @Test
    void updateMoreThan10() {
        backstagePassesQualityUpdater.update(item);
        assertEquals(14, item.sellIn);
        assertEquals(11, item.quality);
    }

    @Test
    void updateLessThan10() {
        item.sellIn = 10;
        backstagePassesQualityUpdater.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(12, item.quality);
    }

    @Test
    void updateLessThan5() {
        item.sellIn = 5;
        backstagePassesQualityUpdater.update(item);
        assertEquals(4, item.sellIn);
        assertEquals(13, item.quality);
    }

    @Test
    void updateConcertDatePassed() {
        item.sellIn = 0;
        backstagePassesQualityUpdater.update(item);
        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void updateWithAboveMaxQuality() {
        item.quality = CommonQualityUpdater.MAX_QUALITY;
        backstagePassesQualityUpdater.update(item);
        assertEquals(14, item.sellIn);
        assertEquals(50, item.quality);
    }

    @Test
    void updateWithBelowMinQuality() {
        item.quality = CommonQualityUpdater.MIN_QUALITY - 10;
        backstagePassesQualityUpdater.update(item);
        assertEquals(14, item.sellIn);
        assertEquals(0, item.quality);
    }

}