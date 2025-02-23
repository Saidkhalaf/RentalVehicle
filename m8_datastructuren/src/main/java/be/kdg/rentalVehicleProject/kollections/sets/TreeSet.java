package be.kdg.rentalVehicleProject.kollections.sets;


import be.kdg.rentalVehicleProject.kollections.lists.ArrayList;
import be.kdg.rentalVehicleProject.kollections.lists.List;

public class TreeSet<T extends Comparable<T>> implements Set<T> {
    private static class TreeNode<V extends Comparable<V>> {
        private V value;
        private TreeNode<V> left;
        private TreeNode<V> right;

        public TreeNode(V value) {
            this.value = value;
        }
    }

    private TreeNode<T> root;
    private int size = 0;

    @Override
    public void add(T value) {
        if (this.root == null) {
            this.root = new TreeNode<T>(value);
            size++;
        } else {
            add(root, value);
        }
    }

    private void add(TreeNode<T> parent, T value) {
        if (contains(value)) return;
        if (value.compareTo(parent.value) < 0) {
            if (parent.left == null)
                parent.left = new TreeNode<>(value);
            else
                add(parent.left, value);
        } else {
            if (parent.right == null)
                parent.right = new TreeNode<>(value);
            else
                add(parent.right, value);
        }

    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        addToList(list, root);
        return list;
    }

    //inorder!
    private void addToList(List<T> list, TreeNode<T> node) {
        if (node.left!=null) {
            addToList(list, node.left);
        }
        list.add(node.value);
        if (node.right!=null) {
            addToList(list, node.right);
        }
    }


    @Override
    public boolean remove(T element) {
        if (root == null) return false;
        TreeNode<T> node = root;
        while (node != null && (node.left != null || node.right != null)) {
            if (node.left != null) {
                if (node.left.value.equals(element)) {
                    TreeNode<T> nodeLeft = node.left.left;
                    TreeNode<T> nodeRight = node.left.right;
                    node.left = null;
                    add(node, nodeLeft.value);
                    add(node, nodeRight.value);
                    return true;
                } else if (node.left.value.compareTo(element) < 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            if (node.right != null) {
                if (node.right.value.equals(element)) {
                    TreeNode<T> nodeLeft = node.right.left;
                    TreeNode<T> nodeRight = node.right.right;
                    node.right = null;
                    add(node, nodeLeft.value);
                    add(node, nodeRight.value);
                    return true;
                } else if (node.right.value.compareTo(element) < 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(TreeNode<T> node, T element) {
        if (node==null) return false;
        if (node.value.equals(element)) return true;
        return contains(node.left, element)||contains(node.right, element);
    }

    @Override
    public int size() {
        return size;
    }
}
