package myapp.searchengine;

import myapp.collections.List;

public class TreeNode {
    private String word;
    private TreeNode leftChild = null;
    private TreeNode rightChild = null;
    private final List<WordWeigth> filesList = new List<>();

    public TreeNode(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public List<WordWeigth> getFilesList() {
        return filesList;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }
    

}
