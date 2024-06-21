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
	@CsvSource(value= {"'Aged Brie',3,5,2,6","'Aged Brie',5,0,4,1",
			"'Aged Brie',-1,2,-2,4","'Aged Brie',0,5,-1,7"}) 
    void AgedBrie(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn);
        assertEquals(qualityOut, app.items[0].quality);
    }
    
 
    
    @ParameterizedTest(name="{0} {1} {2} {3} {4}")
    @CsvSource(value= {"Backstage passes to a TAFKAL80ETC concert,9,5,8,7",
    		"Backstage passes to a TAFKAL80ETC concert,4,2,3,5","Backstage passes to a TAFKAL80ETC concert,-3,2,-4,0"})
    void Backstage(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn,"sellIn");
        assertEquals(qualityOut, app.items[0].quality,"quality");
    }
    
    
   
    @ParameterizedTest(name="{0} {1} {2} {3} {4}")
    @CsvSource(value= {"'Sulfuras, Hand of Ragnaros',10,80,10,80",
    		"'Sulfuras, Hand of Ragnaros',-1,80,-1,80"})
    void Sulfuras(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn);
        assertEquals(qualityOut, app.items[0].quality);
    }
    
    @ParameterizedTest(name="{0} {1} {2} {3} {4}")
    @CsvSource(value= {"'+5 Dexterity Vest',10,20,9,19",
    		"'+5 Dexterity Vest',-1,5,-2,3"})
    void Dexterity(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("+5 Dexterity Vest", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn);
        assertEquals(qualityOut, app.items[0].quality);
    }
    
    @ParameterizedTest(name="{0} {1} {2} {3} {4}")
    @CsvSource(value= {"'Conjured Mana Cake',3,6,2,4",
    		"'Conjured Mana Cake',-2,5,-3,1"})
    void Conjured(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured Mana Cake", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn,"sellIn");
        assertEquals(qualityOut, app.items[0].quality,"quality");
    }
    
    @ParameterizedTest(name="{0} {1} {2} {3} {4}")
    @CsvSource(value= {"'Elixir of the Mongoose',5,7,4,6",
    		"'Elixir of the Mongoose',-1,4,-2,2"})
    void Elixir(String nombre,int sellIn, int quality, int sellOut, int qualityOut) {
        Item[] items = new Item[] { new Item(nombre, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Elixir of the Mongoose", app.items[0].name);
        assertEquals(sellOut, app.items[0].sellIn);
        assertEquals(qualityOut, app.items[0].quality);
    }
  
    
    
    
   
    	
    


    

}
