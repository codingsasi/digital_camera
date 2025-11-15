public class AVLTree<T extends Comparable<T>> {
    Node<T> root;

    public static class Node<T> {
        T key;
        int height;
        Node<T> left;
        Node<T> right;

        Node(T key) {
            this.key = key;
            this.height = 1;
        }
    }

    // Get height of node
    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Get balance factor of node
    private int getBalanceFactor(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Update height of node
    private void updateHeight(Node<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // Right rotation
    private Node<T> rightRotate(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Left rotation
    private Node<T> leftRotate(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Insert a key into the AVL tree
    public void insert(T key) {
        root = insert(root, key);
    }

    private Node<T> insert(Node<T> node, T key) {
        // 1. Perform normal BST insert
        if (node == null) {
            return new Node<>(key);
        }

        int comparison = key.compareTo(node.key);
        if (comparison < 0) {
            node.left = insert(node.left, key);
        } else if (comparison > 0) {
            node.right = insert(node.right, key);
        } else {
            // Duplicate keys not allowed
            return node;
        }

        // 2. Update height of this ancestor node
        updateHeight(node);

        // 3. Get balance factor
        int balance = getBalanceFactor(node);

        // 4. If unbalanced, perform rotations

        // Left Left Case
        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return unchanged node
        return node;
    }

    // Inorder traversal
    public void inorder() {
        inorder(root);
    }

    private void inorder(Node<T> node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    @Override
    public String toString() {
        if (root == null) {
            return "Empty tree";
        }

        StringBuilder sb = new StringBuilder();
        // Print root
        sb.append(root.key).append("\n");

        // Print children
        if (root.left != null) {
            printTree(sb, "", root.left, root.right == null);
        }
        if (root.right != null) {
            printTree(sb, "", root.right, true);
        }

        return sb.toString();
    }

    private void printTree(StringBuilder sb, String prefix, Node<T> node, boolean isLast) {
        if (node == null) {
            return;
        }

        // Print current node with balance factor
        sb.append(prefix);
        sb.append(isLast ? "└── " : "├── ");
        sb.append(node.key);
        sb.append(" (BF: ").append(getBalanceFactor(node)).append(")");
        sb.append("\n");

        // Print children with updated prefix
        String childPrefix = prefix + (isLast ? "    " : "│   ");

        if (node.left != null) {
            printTree(sb, childPrefix, node.left, node.right == null);
        }
        if (node.right != null) {
            printTree(sb, childPrefix, node.right, true);
        }
    }
}

