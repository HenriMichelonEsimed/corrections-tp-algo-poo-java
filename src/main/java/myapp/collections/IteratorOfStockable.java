package myapp.collections;

public class IteratorOfStockable {
    private final ListOfStockable list;
    private ListElementOfStockable current = null;

    public IteratorOfStockable(ListOfStockable list) {
        assert(list != null);
        this.list = list;
        restart();
    }

    public void restart() {
        current = list.head;
    }

    public Stockable next() throws EndOfListException {
        if (current == null) throw new EndOfListException();
        Stockable data = current.getData();
        current = current.getNextElement();
        return data;
    }

    public boolean hasNext() {
        return current != null;
    }
}
