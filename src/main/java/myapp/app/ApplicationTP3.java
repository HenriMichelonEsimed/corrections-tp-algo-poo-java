package myapp.app;

import java.io.FileReader;
import java.io.IOException;

import myapp.collections.EndOfListException;
import myapp.collections.Iterator;
import myapp.searchengine.Index;
import myapp.searchengine.TreeNode;
import myapp.searchengine.WordWeigth;

public class ApplicationTP3 {

    private void displayFiles(TreeNode node) {
        Iterator<WordWeigth> it = new Iterator<>(node.getFilesList());
        while (it.hasNext()) {
            try {
                System.out.format("%s\n", it.next());
            } catch (EndOfListException e) {
                break;
            }
        }
        System.out.println();
    }

    public void exo2(String path) throws IOException, EndOfListException {
        Index index = new Index();
        index.build(path);
        index.walk(node -> {
            System.out.format("%s : ", node.getWord());
            displayFiles(node);
        });
        
    }

    public void exo3(String path, String word) throws IOException, EndOfListException {
        Index index = new Index();
        index.build(path);
        TreeNode node = index.find(word);
        if (node == null) {
            System.out.format("%s not found\n", word);
        } else {
            System.out.format("%s found:\n", word);
            displayFiles(node);
        }
    }
   
    
}
