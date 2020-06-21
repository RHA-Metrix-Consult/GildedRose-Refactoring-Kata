package com.gildedrose;

public class CommonQualityUpdater extends AbstractQualityUpdater {

    @Override
    protected void updateQuality(Item item) {
        if (hasSellDatePassed(item)) {
            item.quality -= 2 * getFactor();
        } else {
            item.quality -= getFactor();
        }
    }

    protected int getFactor() {
        return 1;
    }

}
