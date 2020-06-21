package com.gildedrose;

public abstract class AbstractQualityUpdater implements QualityUpdater {
    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    @Override
    public void update(Item item) {
        updateSellIn(item);
        updateQuality(item);
        checkQualityIsInBound(item);
    }

    protected abstract void updateQuality(Item item);

    protected boolean hasSellDatePassed(Item item) {
        return item.sellIn > 0;
    }

    private void checkQualityIsInBound(Item item) {
        item.quality = Math.min(Math.max(item.quality, MIN_QUALITY), MAX_QUALITY);
    }

    private void updateSellIn(Item item) {
        item.sellIn--;
    }
}
