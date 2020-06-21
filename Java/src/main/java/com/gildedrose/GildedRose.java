package com.gildedrose;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.lang.RuleBookRuleBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

class GildedRose {

    /**
     * The name of the legendary item Sulfuras
     */
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String CHEESE_PREFIX = "Aged";
    private static final String BACKSTAGE_PASSES_PREFIX = "Backstage passes";
    private static final String CONJURED_PREFIX = "Conjured";
    private static final RuleBook<QualityUpdater> RULE_BOOK = RuleBookBuilder.create()
            .withResultType(QualityUpdater.class)
            .withDefaultResult(new CommonQualityUpdater())
            .addRule(sulfurasRule())
            .addRule(cheeseRule())
            .addRule(backstagePassesRule())
            .addRule(conjuredRule())
            .build();
    private Item[] items;

    /**
     * Creates a Gilded Rose app with the provided items
     *
     * @param items the items
     */
    public GildedRose(Item[] items) {
        this.items = items;
    }

    /**
     * Select a quality udpater based on the provided name
     *
     * @param itemName the name
     * @return the quality updater
     */
    public static QualityUpdater selectQualityUpdater(String itemName) {
        NameValueReferableMap<String> facts = new FactMap<>();
        facts.setValue("name", itemName);
        RULE_BOOK.run(facts);
        return RULE_BOOK.getResult()
                .map(Result::getValue)
                .orElse(new CommonQualityUpdater());
    }

    private static Consumer<RuleBookRuleBuilder<QualityUpdater>> sulfurasRule() {
        return rule(SULFURAS_HAND_OF_RAGNAROS::equals, SulfurasQualityUpdater::new);

    }

    private static Consumer<RuleBookRuleBuilder<QualityUpdater>> cheeseRule() {
        return rule(s -> s.startsWith(CHEESE_PREFIX), CheeseQualityUpdater::new);
    }

    private static Consumer<RuleBookRuleBuilder<QualityUpdater>> backstagePassesRule() {
        return rule(s -> s.startsWith(BACKSTAGE_PASSES_PREFIX), BackstagePassesQualityUpdater::new);
    }

    private static Consumer<RuleBookRuleBuilder<QualityUpdater>> conjuredRule() {
        return rule(s -> s.startsWith(CONJURED_PREFIX), ConjuredQualityUpdater::new);
    }

    private static Consumer<RuleBookRuleBuilder<QualityUpdater>> rule(Predicate<String> when, Supplier<QualityUpdater> resultSupplier) {
        return rule -> rule.withFactType(String.class)
                .when(facts -> when.test(facts.getOne()))
                .then((facts, result) -> result.setValue(resultSupplier.get()));
    }

    /**
     * Updates the quality of all the items
     */
    public void updateQuality() {
        for (Item item : items) {
            selectQualityUpdater(item.name).update(item);
        }
    }
}