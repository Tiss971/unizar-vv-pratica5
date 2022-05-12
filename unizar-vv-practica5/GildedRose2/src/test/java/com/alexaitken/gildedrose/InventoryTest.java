package com.alexaitken.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.Inventory;
import com.alexaitken.gildedrose.Item;


public class InventoryTest {

    private Inventory createInventory(Item... items) {
        return new Inventory(items);
    }

    @Test
    // Prueba(s) :P3
    public void should_never_changes_quality_of_Sulfuras() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Inventory inventory = createInventory(sulfuras);
        inventory.updateQuality();
        assertEquals(80, sulfuras.getQuality());
    }

    @Test
    //  Prueba(s) :P3
    public void should_never_changes_sellIn_of_Sulfuras() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Inventory inventory = createInventory(sulfuras);
        inventory.updateQuality();
        assertEquals(0, sulfuras.getSellIn());
    }

    @Test
    //  Prueba(s) :P9
    public void should_lower_the_sellIn_by_one_for_normal_items() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 20);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(9, normalItem.getSellIn());
    }

    @Test
    //  Prueba(s) : P9
    public void should_lower_the_quality_by_one_for_normal_items() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 20);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(19, normalItem.getQuality());
    }

    @Test
    // Prueba(s) :P9b
    public void should_not_lower_the_quality_below_zero() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 0);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(0, normalItem.getQuality());
    }

    @Test
    // Prueba(s) :P10
    public void should_lower_the_quality_twice_as_fast_once_the_sell_in_date_has_passed() {
        Item normalItem = new Item("+5 Dexterity Vest", -1, 25);
        Inventory inventory = createInventory(normalItem);
        inventory.updateQuality();
        assertEquals(23, normalItem.getQuality());
    }

    @Test
    // Prueba(s) :P1
    public void should_increase_the_quality_of_aged_brie_as_it_gets_older() {
        Item agedBrie = new Item("Aged Brie", 10, 25);
        Inventory inventory = createInventory(agedBrie);
        inventory.updateQuality();
        assertEquals(26, agedBrie.getQuality());
    }

    @Test
    // Prueba(s) : P1b
    public void should_not_increase_the_quality_of_aged_brie_over_50() {
        Item agedBrie = new Item("Aged Brie", 10, 50);
        Inventory inventory = createInventory(agedBrie);
        inventory.updateQuality();
        assertEquals(50, agedBrie.getQuality());
    }

    @Test
    //  Prueba(s) :P7
    public void should_lower_backstage_passes_to_zero_quality_once_concert_has_happened() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(0, backStagePass.getQuality());
    }

    @Test
    //  Prueba(s) :P4
    public void should_increase_backstage_passes_quality_by_1_when_the_concert_is_more_than_10_days_away() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(21, backStagePass.getQuality());
    }

    @Test
    // Prueba(s) :P5
    public void should_increase_backstage_passes_quality_by_2_when_the_concert_is_10_days_or_less_away() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 27);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(29, backStagePass.getQuality());
    }

    @Test
    //  Prueba(s) :P6
    public void should_increase_backstage_passes_quality_by_3_when_the_concert_is_5_days_or_less_away() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 44);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(47, backStagePass.getQuality());
    }

    @Test
    //  Prueba(s) :P4b P5c P6d
    public void should_not_increase_backstage_passes_above_a_quality_of_50() {
        Item backStagePassMoreThan10DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50);
        Item backStagePass10DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        Item backStagePass5DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48);
        Inventory inventory = createInventory(backStagePassMoreThan10DaysAway, backStagePass10DaysAway, backStagePass5DaysAway);
        inventory.updateQuality();
        assertEquals(50, backStagePassMoreThan10DaysAway.getQuality());
        assertEquals(50, backStagePass10DaysAway.getQuality());
        assertEquals(50, backStagePass5DaysAway.getQuality());
    }

    // ***
    // Métodos de prueba que faltan
    // ***
    @Test
    //  Prueba(s) :P2
    public void should_increase_the_quality_of_aged_brie_twice_as_fast_once_the_sell_in_date_has_passed() {
        Item agedBriePassed = new Item("Aged Brie", -8, 25);
        Inventory inventory = createInventory(agedBriePassed);
        inventory.updateQuality();
        assertEquals(27, agedBriePassed.getQuality());
    }

    @Test
    //  Prueba(s) :P2b P2c
    public void should_not_increase_the_quality_of_aged_brie_over_50_once_the_sell_in_date_has_passed() {
        Item agedBriePassedQuality50 = new Item("Aged Brie", -8, 50);
        Item agedBriePassedQuality49 = new Item("Aged Brie", -8, 49);
        Inventory inventory = createInventory(agedBriePassedQuality50,agedBriePassedQuality49);
        inventory.updateQuality();
        assertEquals(50, agedBriePassedQuality50.getQuality());
        assertEquals(50, agedBriePassedQuality49.getQuality());
    }

    @Test
    //  Prueba(s) :P2d
    public void should_increase_the_quality_of_aged_brie_twice_as_fast_once_the_sell_in_date_just_passed() {
        Item agedBriePassed = new Item("Aged Brie", 0, 25);
        Inventory inventory = createInventory(agedBriePassed);
        inventory.updateQuality();
        assertEquals(27, agedBriePassed.getQuality());
    }

    @Test
    //  Prueba(s) :P5b P5e P5f P6b P6c P6f P6g P6h
    public void should_not_increase_backstage_passes_above_a_quality_of_50_valores_limites() {
        Item backStagePass10DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50);
        Item backStagePassMoreThan5DaysAwayQuality50 = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 50);
        Item backStagePassMoreThan5DaysAwayQuality49 = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 49);
        Item backStagePass5DaysAwayQuality50 = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50);
        Item backStagePass5DaysAwayQuality49 = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        Item backStagePass1DayAwayQuality50 = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50);
        Item backStagePass1DayAwayQuality49 = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 49);
        Item backStagePass1DayAwayQuality48 = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 48);
        Inventory inventory = createInventory(backStagePass10DaysAway, backStagePassMoreThan5DaysAwayQuality50, backStagePassMoreThan5DaysAwayQuality49,
                backStagePass5DaysAwayQuality50,backStagePass5DaysAwayQuality49,backStagePass1DayAwayQuality50,backStagePass1DayAwayQuality49,backStagePass1DayAwayQuality48);
        inventory.updateQuality();
        assertEquals(50, backStagePass10DaysAway.getQuality());
        assertEquals(50, backStagePassMoreThan5DaysAwayQuality50.getQuality());
        assertEquals(50, backStagePassMoreThan5DaysAwayQuality49.getQuality());
        assertEquals(50, backStagePass5DaysAwayQuality50.getQuality());
        assertEquals(50, backStagePass5DaysAwayQuality49.getQuality());
        assertEquals(50, backStagePass1DayAwayQuality50.getQuality());
        assertEquals(50, backStagePass1DayAwayQuality49.getQuality());
        assertEquals(50, backStagePass1DayAwayQuality48.getQuality());
    }

    @Test
    //  Prueba(s) :P5d
    public void should_increase_backstage_passes_quality_by_2_when_the_concert_is_6_days_or_more_away() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 25);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(27, backStagePass.getQuality());
    }

    @Test
    //  Prueba(s) :P6e
    public void should_increase_backstage_passes_quality_by_3_when_the_concert_is_1_day_or_more_away() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 25);
        Inventory inventory = createInventory(backStagePass);
        inventory.updateQuality();
        assertEquals(28, backStagePass.getQuality());
    }

    @Test
    //  Prueba(s) :P8
    public void should_stays_to_0_the_quality_of_backstage_passes_once_the_sell_in_date_has_passed() {
        Item BackstagePassPassed = new Item("Backstage passes to a TAFKAL80ETC concert", -8, 0);
        Inventory inventory = createInventory(BackstagePassPassed);
        inventory.updateQuality();
        assertEquals(0, BackstagePassPassed.getQuality());
    }

    @Test
    // Prueba(s) :P10b P10c
    public void should_not_lower_the_quality_below_zero_once_the_sell_in_date_has_passed() {
        Item normalItemPassedQuality1 = new Item("+5 Dexterity Vest", -8, 1);
        Item normalItemPassedQuality0 = new Item("+5 Dexterity Vest", -8, 0);
        Inventory inventory = createInventory(normalItemPassedQuality1,normalItemPassedQuality0);
        inventory.updateQuality();
        assertEquals(0, normalItemPassedQuality1.getQuality());
        assertEquals(0, normalItemPassedQuality0.getQuality());
    }

    @Test
    // Prueba(s) :P10d
    public void should_lower_the_quality_twice_as_fast_once_the_sell_in_date_just_passed() {
        Item normalItemJustPassed = new Item("+5 Dexterity Vest", 0, 25);
        Inventory inventory = createInventory(normalItemJustPassed);
        inventory.updateQuality();
        assertEquals(23, normalItemJustPassed.getQuality());
    }


    @Test
    // Prueba(s) :método que falta
    public void should_never_changes_quality_of_Sulfuras_once_sell_in_date_has_passed() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", -8, 80);
        Inventory inventory = createInventory(sulfuras);
        inventory.updateQuality();
        assertEquals(80, sulfuras.getQuality());
    }



}
