package com.gildedrose;

public class BackstagePassesQualityUpdater extends AbstractQualityUpdater {

    /**
     * Another solution to avoid this pile of if-else would be to use another rule book
     * or something based on Range
     * but for now, let's KISS...
     *
     * @param item the item
     */
    @Override
    protected void updateQuality(Item item) {
        if (hasSellDatePassed(item)) {
            item.quality = 0;
        } else if (hasFewerDays(item, 5)) {
            item.quality += 3;
        } else if (hasFewerDays(item, 10)) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }
    }
}
