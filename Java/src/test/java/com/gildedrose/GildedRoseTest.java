package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GildedRoseTest {

    @Test
    void selectQualityUpdaterForCommonItem() {
        QualityUpdater qualityUpdater = GildedRose.selectQualityUpdater("foo");
        assertTrue(qualityUpdater instanceof CommonQualityUpdater);
    }

    @Test
    void selectQualityUpdaterForSulfuras() {
        QualityUpdater qualityUpdater = GildedRose.selectQualityUpdater("Sulfuras, Hand of Ragnaros");
        assertTrue(qualityUpdater instanceof SulfurasQualityUpdater);
    }

    @Test
    void selectQualityUpdaterForCheese() {
        QualityUpdater qualityUpdater = GildedRose.selectQualityUpdater("Aged Brie");
        assertTrue(qualityUpdater instanceof CheeseQualityUpdater);
    }

    @Test
    void selectQualityUpdaterForBackstagePasses() {
        QualityUpdater qualityUpdater = GildedRose.selectQualityUpdater("Backstage passes to a TAFKAL80ETC concert");
        assertTrue(qualityUpdater instanceof BackstagePassesQualityUpdater);
    }

    @Test
    void selectQualityUpdaterForConjured() {
        QualityUpdater qualityUpdater = GildedRose.selectQualityUpdater("Conjured Mana Cake");
        assertTrue(qualityUpdater instanceof ConjuredQualityUpdater);
    }

}
