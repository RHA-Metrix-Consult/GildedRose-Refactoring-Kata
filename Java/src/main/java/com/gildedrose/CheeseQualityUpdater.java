package com.gildedrose;

public class CheeseQualityUpdater extends AbstractQualityUpdater {

    @Override
    protected void updateQuality(Item item) {
        item.quality++;
    }
}
