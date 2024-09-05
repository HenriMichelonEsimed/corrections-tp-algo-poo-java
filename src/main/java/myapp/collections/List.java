package myapp.collections;

import java.util.Comparator;

public class List<T> {
    ListElement<T> head = null;
 
    public void add(T data) {
        ListElement<T> newElement = new ListElement<T>(data);
        if (head == null) { // empty list
            head = newElement;
        } else {
            // search for the end of the list
            ListElement<T> loopElement = head;
            while (loopElement.getNextElement() != null) {
                loopElement = loopElement.getNextElement();
            }
            // add new element at the end of the list
            loopElement.setNextElement(newElement);
            newElement.setPreviousElement(loopElement);
        }
    }

    public void addFirst(T data) {
        ListElement<T> newElement = new ListElement<T>(data);
        if (head == null) { // empty list
            head = newElement;
        } else {
            // add new elemement at the start of the list (replace 'head')
            ListElement<T> nextElement = head;
            head = newElement;
            newElement.setNextElement(nextElement);
            nextElement.setPreviousElement(newElement);
        }
    }

    public boolean contains(T data) {
        ListElement<T> loopElement = head;
        while (loopElement != null) {
            if (loopElement.getData().equals(data)) {
                return true;
            }
            loopElement = loopElement.getNextElement();
        }
        return false;
    }

    public T findFirst(T data) {
        ListElement<T> loopElement = head;
        while (loopElement != null) {
            if (loopElement.getData().equals(data)) {
                return loopElement.getData();
            }
            loopElement = loopElement.getNextElement();
        }
        return null;
    }

    public void delete(T data) {
        ListElement<T> loopElement = head;
        ListElement<T> previousElement = null;
        while (loopElement != null) {
            if (loopElement.getData().equals(data)) {
                if (previousElement == null) { // element to delete is the first element (head)
                    head = head.getNextElement();
                    head.setPreviousElement(null);
                } else { // remove element by dropping it from the chain
                    previousElement.setNextElement(loopElement.getNextElement());
                    loopElement.getNextElement().setPreviousElement(previousElement);
                }
                return;
            }
            previousElement = loopElement;
            loopElement = loopElement.getNextElement();
        }
    }

    
    public void sort(Comparator<? super T> comparator) {
        ListElement<T> loopElement = head;
        while (loopElement.getNextElement() != null) {
            ListElement<T> min = loopElement;
            ListElement<T> innerLoopElement = loopElement.getNextElement();
            while (innerLoopElement != null) {
                if (comparator.compare(innerLoopElement.getData(), min.getData()) < 0) {
                    min = innerLoopElement;
                }
                innerLoopElement = innerLoopElement.getNextElement();
            }
            if (min != loopElement) {
                ListElement<T> prevCurrent = loopElement.getPreviousElement();
                ListElement<T> nextMin = min.getNextElement();
                ListElement<T> prevMin = min.getPreviousElement();
                min.setNextElement(loopElement);
                min.setPreviousElement(prevCurrent);
                loopElement.setPreviousElement(min);
                prevMin.setNextElement(nextMin);
                if (nextMin != null) {
                    nextMin.setPreviousElement(prevMin);
                }
                if (prevCurrent == null) {
                    head = min;
                } else {
                    prevCurrent.setNextElement(min);
                    loopElement.setPreviousElement(min);
                }

            } else {
                loopElement = loopElement.getNextElement();
            }
        }
    }

}
