package edu.wpi.cs.csp;

import junit.framework.TestCase;

/**
 * Test cases for Item class.
 *
 * @author Aditya Nivarthi
 */
public class ItemTest extends TestCase {

    public void testItem() {
        Item item = new Item(null, "item1");
        assertEquals(item.getBag(), null);
        assertEquals(item.getName(), "item1");
    }

    public void testItemBag() {
        Item item = new Item(new Bag(10), "item1");
        assertEquals(item.getBag().getMaxSize(), 10);
    }

    public void testItemEquals() {
        Item item = new Item(null, "item1");
        Item item2 = new Item(null, "item1");
        assertEquals(true, item.equals(item2));
    }
}