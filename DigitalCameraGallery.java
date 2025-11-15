/**
 * Digital Camera Gallery Application
 * Uses AVL tree to maintain an in-memory index of photo filenames
 */
public class DigitalCameraGallery {
    public static void main(String[] args) {
        // Create AVL tree with Integer type
        AVLTree<Integer> tree = new AVLTree<>();

        // Input sequence from assignment
        int[] inputs = {30, 10, 20, 50, 40, 5, 4, 60, 70, 24, 34, 33};

        System.out.println("Inserting values in order: ");
        for (int value : inputs) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.println();

        // Insert each value
        System.out.println("Building AVL tree...");
        for (int value : inputs) {
            tree.insert(value);
        }

        System.out.println();
        System.out.println("Inorder Traversal (sorted order):");
        System.out.println("--------------------------------");
        tree.inorder();
        System.out.println();
        System.out.println();

        // Print tree structure with balance factors
        System.out.println("Tree Structure (with Balance Factors):");
        System.out.println("--------------------------------------");
        System.out.println(tree);
    }
}

