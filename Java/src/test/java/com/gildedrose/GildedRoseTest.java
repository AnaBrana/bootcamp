package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 7, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(6, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }
    
    
    @ParameterizedTest(name="{0} {1} {2} {3} {4}")
	@CsvSource(value= {"'Aged Brie',3,5,2,6","'Aged Brie',5,0,4,5",
			"'Aged Brie',-1,2,-2,4","'Aged Brie',0,0,-1,2"}) 
    void AgedBrie(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn);
        assertEquals(qualityOut, app.items[0].quality);
    }
    
 
    
    @ParameterizedTest(name="{0} {1} {2} {3} {4}")
    @CsvSource(value= {"'Backstage passes to a TAFKAL80ETC concert',3,5,2,4"})
    void Backstage(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn);
        assertEquals(qualityOut, app.items[0].quality);
    }
    
    
   
    @ParameterizedTest(name="{0} {1} {2} {3} {4}")
    @CsvSource(value= {"'Sulfuras, Hand of Ragnaros',0,80,0,0",
    		"'Sulfuras, Hand of Ragnaros',-1,80,0,0"})
    void Sulfuras(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn);
        assertEquals(qualityOut, app.items[0].quality);
    }
    
    @ParameterizedTest(name="{0} {1} {2} {3} {4}")
    @CsvSource(value= {"'+5 Dexterity Vest',10,20,0,0"})
    void Dexterity(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("+5 Dexterity Vest", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn);
        assertEquals(qualityOut, app.items[0].quality);
    }
    
    @ParameterizedTest(name="{0} {1} {2} {3} {4}")
    @CsvSource(value= {"'Conjured Mana Cake',3,6,0,0"})
    void Conjured(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured Mana Cake", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn);
        assertEquals(qualityOut, app.items[0].quality);
    }
    
    @ParameterizedTest(name="{0} {1} {2} {3} {4}")
    @CsvSource(value= {"'Elixir of the Mongoose',5,7,0,0"})
    void Elixir(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Elixir of the Mongoose", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn);
        assertEquals(qualityOut, app.items[0].quality);
    }
  
    
    
    
   
    	
    


    

}
