package com.booleanuk.core.order;

import com.booleanuk.core.Bagel;
import com.booleanuk.core.Coffee;
import com.booleanuk.core.Filling;
import com.booleanuk.core.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestInventory {

    @Test
    public void containsitems(){
        com.booleanuk.core.Inventory newInventory = new Inventory();

        Bagel newBagel = new Bagel("Onion", 0.49, new ArrayList<>());
        Bagel newBagel2 = new Bagel("Honey", 0.49, new ArrayList<Filling>());
        boolean response = newInventory.contains(newBagel);
        Assertions.assertTrue(response);
        response = newInventory.contains(newBagel2);
        Assertions.assertFalse(response);



        Coffee newCoffee = new Coffee("White", 1.19) ;
        Coffee newCoffee2 = new Coffee("Latte Macchiato", 1.19) ;
        response = newInventory.contains(newCoffee);
        Assertions.assertTrue(response);
        response = newInventory.contains(newCoffee2);
        Assertions.assertFalse(response);


        Filling newFilling = new Filling("Egg", 0.12);
        Filling newFilling2 = new Filling("Nutella", 0.12);
        response = newInventory.contains(newFilling);
        Assertions.assertTrue(response);
        response = newInventory.contains(newFilling2);
        Assertions.assertFalse(response);





    }
}