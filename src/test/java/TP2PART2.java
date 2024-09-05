import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import myapp.collections.EndOfListException;
import myapp.collections.Iterator;
import myapp.collections.List;
import myapp.tools.FileReader;
import myapp.tools.TextFileTools;

public class TP2PART2 {
   
    @Test
    public void exo2() throws EndOfListException {
        List<String> list = new List<String>();
        Iterator<String> it = new Iterator<String>(list);
        Assertions.assertEquals(false, it.hasNext());
        it.restart();
        Assertions.assertEquals(false, it.hasNext());
        list.add("Hello");
        it.restart();
        Assertions.assertEquals(true, it.hasNext());
        String data = it.next();
        Assertions.assertEquals(false, it.hasNext());
        Assertions.assertEquals("Hello", data);
        list.add("World");
        list.add("!");
        it.restart();
        Assertions.assertEquals(true, it.hasNext());
        Assertions.assertEquals("Hello", it.next());
        Assertions.assertEquals(true, it.hasNext());
        Assertions.assertEquals("World", it.next());
        Assertions.assertEquals(true, it.hasNext());
        Assertions.assertEquals("!", it.next());
        Assertions.assertEquals(false, it.hasNext());
    }

    @Test
    public void exo3() throws EndOfListException {
        List<String> list = TextFileTools.readFile(FileReader.fromString("Hello,\n\r World !"));
        Iterator<String> it = new Iterator<String>(list);
        Assertions.assertEquals(true, it.hasNext());
        Assertions.assertEquals("Hello", it.next());
        Assertions.assertEquals(true, it.hasNext());
        Assertions.assertEquals("World", it.next());
        Assertions.assertEquals(true, it.hasNext());
        Assertions.assertEquals("!", it.next());
        Assertions.assertEquals(false, it.hasNext());
    }
   
}
