package myapp.searchengine;

public class WordWeigth implements Comparable<WordWeigth> {
    private final String fileName;
    private int weight = 1;

    public WordWeigth(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", fileName, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WordWeigth other = (WordWeigth) obj;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        return true;
    }

    @Override
    public int compareTo(WordWeigth o) {
        return Integer.compare(o.weight, this.weight);
    }
}
