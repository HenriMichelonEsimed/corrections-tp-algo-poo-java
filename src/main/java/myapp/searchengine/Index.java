package myapp.searchengine;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.function.Consumer;

import myapp.collections.EndOfListException;
import myapp.collections.Iterator;
import myapp.tools.FileReader;
import myapp.tools.TextFileTools;

public class Index {

    private final Tree tree = new Tree();

    public void build(String path) throws IOException, EndOfListException {
        TextFileTools.walkDirectory(path, fileName -> {
            FileReader fileReader = new FileReader(fileName.toString(), Charset.forName("Windows-1252"));
            Iterator<String> wordIt = new Iterator<>(TextFileTools.readFile(fileReader));
            while (wordIt.hasNext()) {
                try {
                    tree.insert(wordIt.next(), fileName.toString());
                } catch (EndOfListException e) {
                    break;
                }
            }
        });
    }

    private class FindWord implements Consumer<TreeNode> {
        private TreeNode result = null;
        private String word;

        public FindWord(String word) {
            this.word = word;
        }

        @Override
        public void accept(TreeNode node) {
            if (word.compareToIgnoreCase(node.getWord()) == 0) {
                result = node;
            }
        }

    }

    public TreeNode find(String word) {
        FindWord findWord = new FindWord(word);
        walk(findWord);
        return findWord.result;
    }

    public void walk(Consumer<TreeNode> process) {
        tree.walk(process);
    }

}
