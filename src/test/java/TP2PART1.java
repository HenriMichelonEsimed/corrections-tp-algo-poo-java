import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import myapp.collections.EndOfListException;
import myapp.collections.IteratorOfStockable;
import myapp.collections.ListOfStockable;
import myapp.collections.Stockable;

public class TP2PART1 {

    public static class StringData implements Stockable {
        final String message;
        StringData(String message) {
            this.message = message;
        }
        @Override
        public String toString() {
            return  message;
        }
        @Override
        public boolean equals(Object obj) {
            if (message == null) return false;
            return message.equals(((StringData)obj).message);
        }
    }

    @Test
    public void addFirst() {
        ListOfStockable list = new ListOfStockable();
        Assertions.assertArrayEquals(new StringData[] { }, 
            list.toArray());
        list.addFirst(new StringData("Hello"));
        Assertions.assertArrayEquals(new StringData[] {
            new StringData("Hello")}, 
            list.toArray());
        list.addFirst(new StringData("World"));
        Assertions.assertArrayEquals(new StringData[] {
            new StringData("World"),
            new StringData("Hello")}, 
            list.toArray());
        list.addFirst(new StringData("!"));
        Assertions.assertArrayEquals(new StringData[] {
            new StringData("!"),
            new StringData("World"),
            new StringData("Hello")}, 
            list.toArray());
    }

    @Test
    public void add() {
        ListOfStockable list = new ListOfStockable();
        Assertions.assertArrayEquals(new StringData[] { }, 
            list.toArray());
        list.add(new StringData("Hello"));
        Assertions.assertArrayEquals(new StringData[] {
            new StringData("Hello")}, 
            list.toArray());
        list.add(new StringData("World"));
        Assertions.assertArrayEquals(new StringData[] {
            new StringData("Hello"),
            new StringData("World")}, 
            list.toArray());
        list.add(new StringData("!"));
        Assertions.assertArrayEquals(new StringData[] {
            new StringData("Hello"),
            new StringData("World"),
            new StringData("!")}, 
            list.toArray());
    }

    @Test
    public void delete() {
        ListOfStockable list = new ListOfStockable();
        list.add(new StringData("Hello"));
        list.add(new StringData("World"));
        list.add(new StringData("!"));
        list.delete(new StringData("World"));
        Assertions.assertArrayEquals(new StringData[] {
            new StringData("Hello"),
            new StringData("!")}, 
            list.toArray());
        list.delete(new StringData("World"));
        Assertions.assertArrayEquals(new StringData[] {
            new StringData("Hello"),
            new StringData("!")}, 
            list.toArray());
        list.delete(new StringData("!"));
        Assertions.assertArrayEquals(new StringData[] {
            new StringData("Hello")}, 
            list.toArray());
        list.add(new StringData("World"));
        list.delete(new StringData("Hello"));
        Assertions.assertArrayEquals(new StringData[] {
            new StringData("World")}, 
            list.toArray());
            list.delete(new StringData("World"));
        Assertions.assertArrayEquals(new StringData[] {}, list.toArray());
    }

    @Test
    public void contains() {
        ListOfStockable list = new ListOfStockable();
        Assertions.assertEquals(false, list.contains(new StringData("Hello")));
        list.add(new StringData("Hello"));
        Assertions.assertEquals(true, list.contains(new StringData("Hello")));
        Assertions.assertEquals(false, list.contains(new StringData("World")));
        Assertions.assertEquals(false, list.contains(new StringData("!")));
        list.add(new StringData("World"));
        Assertions.assertEquals(true, list.contains(new StringData("Hello")));
        Assertions.assertEquals(true, list.contains(new StringData("World")));
        Assertions.assertEquals(false, list.contains(new StringData("!")));
        list.add(new StringData("!"));
        Assertions.assertEquals(true, list.contains(new StringData("Hello")));
        Assertions.assertEquals(true, list.contains(new StringData("World")));
        Assertions.assertEquals(true, list.contains(new StringData("!")));
    }

    @Test
    public void iterator() throws EndOfListException {
        ListOfStockable list = new ListOfStockable();
        IteratorOfStockable it = new IteratorOfStockable(list);
        Assertions.assertEquals(false, it.hasNext());
        it.restart();
        Assertions.assertEquals(false, it.hasNext());
        list.add(new StringData("Hello"));
        it.restart();
        Assertions.assertEquals(true, it.hasNext());
        StringData data = (StringData)it.next();
        Assertions.assertEquals(false, it.hasNext());
        Assertions.assertEquals(new StringData("Hello"), data);
        list.add(new StringData("World"));
        list.add(new StringData("!"));
        it.restart();
        Assertions.assertEquals(true, it.hasNext());
        Assertions.assertEquals(new StringData("Hello"), it.next());
        Assertions.assertEquals(true, it.hasNext());
        Assertions.assertEquals(new StringData("World"), it.next());
        Assertions.assertEquals(true, it.hasNext());
        Assertions.assertEquals(new StringData("!"), it.next());
        Assertions.assertEquals(false, it.hasNext());
    }
    
}
