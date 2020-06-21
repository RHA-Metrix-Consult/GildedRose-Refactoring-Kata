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
        QualityUpdater qualityUpdater = GildedRose.selectQualityUpdater(GildedRose.SULFURAS_HAND_OF_RAGNAROS);
        assertTrue(qualityUpdater instanceof SulfurasQualityUpdater);
    }

    @Test
    void selectQualityUpdaterForCheese() {
        QualityUpdater qualityUpdater = GildedRose.selectQualityUpdater("Aged Brie");
        assertTrue(qualityUpdater instanceof CheeseQualityUpdater);
    }

}
