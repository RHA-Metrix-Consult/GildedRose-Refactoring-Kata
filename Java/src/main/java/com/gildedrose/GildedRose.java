package com.gildedrose;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.lang.RuleBookRuleBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;

import java.util.function.Consumer;

class GildedRose {

    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED = "Aged";
    private Item[] items;

    /**
     * Select a quality udpater based on the provdided name
     *
     * @param itemName the name
     * @return the quality updater
     */
    public static QualityUpdater selectQualityUpdater(String itemName) {
        NameValueReferableMap<String> facts = new FactMap<>();
        facts.setValue("name", itemName);
        RuleBook<QualityUpdater> ruleBook = RuleBookBuilder.create()
                .withResultType(QualityUpdater.class)
                .withDefaultResult(new CommonQualityUpdater())
                .addRule(sulfurasRule())
                .addRule(cheeseRule())
                .build();
        ruleBook.run(facts);
        return ruleBook.getResult().map(Result::getValue).orElse(new CommonQualityUpdater());
    }

    private static Consumer<RuleBookRuleBuilder<QualityUpdater>> cheeseRule() {
        return rule -> rule.withFactType(String.class)
                .when(facts -> SULFURAS_HAND_OF_RAGNAROS.equals(facts.getOne()))
                .then((facts, result) -> result.setValue(new SulfurasQualityUpdater()));

    }

    private static Consumer<RuleBookRuleBuilder<QualityUpdater>> sulfurasRule() {
        return rule -> rule.withFactType(String.class)
                .when(facts -> facts.getOne().startsWith(AGED))
                .then((facts, result) -> result.setValue(new CheeseQualityUpdater()));
    }

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            selectQualityUpdater(item.name).update(item);
        }
    }
}