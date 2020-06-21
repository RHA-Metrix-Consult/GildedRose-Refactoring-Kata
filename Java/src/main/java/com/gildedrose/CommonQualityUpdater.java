package com.gildedrose;

public class CommonQualityUpdater extends AbstractQualityUpdater {

    @Override
    protected void updateQuality(Item item) {
        if (hasSellDatePassed(item)) {
            item.quality--;
        } else {
            item.quality -= 2;
        }
    }

}
