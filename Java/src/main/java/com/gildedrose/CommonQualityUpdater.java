package com.gildedrose;

public class CommonQualityUpdater {

    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    public void update(Item item) {
        decreaseSellIn(item);
        decreaseQuality(item);
    }

    private void decreaseQuality(Item item) {
        if (isItemStillGood(item)) {
            item.quality--;
        } else {
            item.quality -= 2;
        }
        checkQualityIsInBound(item);
    }

    private void checkQualityIsInBound(Item item) {
        item.quality = Math.min(Math.max(item.quality, MIN_QUALITY), MAX_QUALITY);
    }

    private boolean isItemStillGood(Item item) {
        return item.sellIn > 0;
    }

    private void decreaseSellIn(Item item) {
        item.sellIn--;
    }
}
