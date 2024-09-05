package myapp.collections;

public class ListOfStockable {
    ListElementOfStockable head = null;
 
    public void add(Stockable data) {
        ListElementOfStockable newElement = new ListElementOfStockable(data);
        if (head == null) { // empty list
            head = newElement;
        } else {
            // search for the end of the list
            ListElementOfStockable loopElement = head;
            while (loopElement.getNextElement() != null) {
                loopElement = loopElement.getNextElement();
            }
            // add new element at the end of the list
            loopElement.setNextElement(newElement);
        }
    }

    public void addFirst(Stockable data) {
        ListElementOfStockable newElement = new ListElementOfStockable(data);
        if (head == null) { // empty list
            head = newElement;
        } else {
            // add new elemement at the start of the list (replace 'head')
            ListElementOfStockable nextElement = head;
            head = newElement;
            newElement.setNextElement(nextElement);
        }
    }

    public boolean contains(Stockable data) {
        ListElementOfStockable loopElement = head;
        while (loopElement != null) {
            if (loopElement.getData().equals(data)) {
                return true;
            }
            loopElement = loopElement.getNextElement();
        }
        return false;
    }

    public void delete(Stockable data) {
        ListElementOfStockable loopElement = head;
        ListElementOfStockable previousElement = null;
        while (loopElement != null) {
            if (loopElement.getData().equals(data)) {
                if (previousElement == null) { // element to delete is the first element (head)
                    head = head.getNextElement();
                } else { // remove element by dropping it from the chain
                    previousElement.setNextElement(loopElement.getNextElement());
                }
                return;
            }
            previousElement = loopElement;
            loopElement = loopElement.getNextElement();
        }
    }

    public Stockable[] toArray() {
        // count number of elements
        int count = 0;
        ListElementOfStockable loopElement = head;
        while (loopElement != null) {
            count = count + 1;
            loopElement = loopElement.getNextElement();
        }
        // create array from list content
        Stockable[] array = new Stockable[count];
        int index = 0;
        loopElement = head;
        while (loopElement != null) {
            array[index] = loopElement.getData();
            index = index + 1;
            loopElement = loopElement.getNextElement();
        }
        return array;
    }
}
