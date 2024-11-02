import java.util.Iterator;

public class QuadTreeImage<Pixel extends Number> implements Comparable<QuadTreeImage<Pixel>>, Iterable<TreeNode<Pixel>>
{
    ////////////////////////////////////////////////////////
    /**   DO NOT CHANGE/ALTER/REMOVE THESE FIELDS        **/
    /**/  private TreeNode<Pixel> root;                 /**/
    /**/  private int imageWidth;                       /**/
    ////////////////////////////////////////////////////////


    // do NOT edit this method
    public TreeNode<Pixel> getRoot()
    {
        return root;
    }

    // do NOT edit this method
    public int getSize()
    {
        return imageWidth;
    }

    // Time Complexity: O(n * log n) where n is the number of pixels
    // throws RuntimeException in case of invalid input or errors
    public QuadTreeImage(Pixel[][] array)
    {
        if (array == null || array.length == 0 || array[0].length == 0) {
            throw new RuntimeException("Not a valid image array");
        }
        this.imageWidth = array[0].length;
        this.root = buildQuadTree(array, 0, 0, imageWidth);
    }

    // Time Complexity: O(log n) where n is the number of pixels
    // throws IndexOutOfBoundsException in case of invalid input
    public Pixel getColor(int w, int h)
    {
        if (w < 0 || w >= imageWidth || h < 0 || h >= imageWidth) {
            throw new IndexOutOfBoundsException("Pixel coordinates are out of bounds");
        }
        return findColor(root, 0, 0, imageWidth, w, h);
    }

    // Time Complexity: O(log n) where n is the number of pixels
    // throws IndexOutOfBoundsException in case of invalid input
    public void setColor(int w, int h, Pixel v)
    {
        if (w < 0 || w >= imageWidth || h < 0 || h >= imageWidth) {
            throw new IndexOutOfBoundsException("Pixel coordinates (" + w + ", " + h + ") are out of bounds.");
        }
    }
    
    // Time Complexity: O(m) where m is the number of nodes in the tree
    public int countNodes()
    {
        return countNodesRecursive(root);
    }

    // Time-complexity: O(m) where m is the number of nodes in the tree
    public int compareTo(QuadTreeImage<Pixel> other)
    {
        int thisImageBrightness = this.brightness();
        int otherImageBrightness = other.brightness();

        return Integer.compare(thisImageBrightness, otherImageBrightness);
    }

    // Time-complexity: O(m) where m is the number of nodes in the tree
    public int brightness()
    {
        return brightnessRecursive(this.root, this.imageWidth);
    }

    // Time-complexity: O(m) where m is the number of nodes in the tree
    @Override
    public String toString()
    {
    
        String result = "";

        return result;
    }

    // do NOT edit this method
    @Override
    public Iterator<TreeNode<Pixel>> iterator()
    {
        return new QuadTreeImageIterator<>(this);
    }
    private TreeNode<Pixel> buildQuadTree(Pixel[][] array, int row, int col, int width) {
        if (width == 1) {
            return new TreeNode<>(array[col][row]);
        }

        int halfWidth = width / 2;
        TreeNode<Pixel> nw = buildQuadTree(array, row, col, halfWidth);
        TreeNode<Pixel> ne = buildQuadTree(array, row + halfWidth, col, halfWidth);
        TreeNode<Pixel> sw = buildQuadTree(array, row, col + halfWidth, halfWidth);
        TreeNode<Pixel> se = buildQuadTree(array, row + halfWidth, col + halfWidth, halfWidth);

        if (isHomogeneous(nw, ne, sw, se)) {
            return new TreeNode<>(nw.value);
        } else {
            TreeNode<Pixel> node = new TreeNode<>(null); // Inner nodes have no value
            node.NW = nw;
            node.NE = ne;
            node.SW = sw;
            node.SE = se;
            return node;
        }
    }

    private boolean isHomogeneous(TreeNode<Pixel> nw, TreeNode<Pixel> ne, TreeNode<Pixel> sw, TreeNode<Pixel> se) {
        Pixel value = nw.value;
        return nw.isLeaf() && ne.isLeaf() && sw.isLeaf() && se.isLeaf() &&
               value.equals(ne.value) && value.equals(sw.value) && value.equals(se.value);
    }

    private int countNodesRecursive(TreeNode<Pixel> node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodesRecursive(node.NW) + countNodesRecursive(node.NE) +
                   countNodesRecursive(node.SE) + countNodesRecursive(node.SW);
    }

    private int brightnessRecursive(TreeNode<Pixel> node, int width) {
        if (node == null) {
            return 0;
        }
        if (node.isLeaf()) {
            return node.value.intValue();
        }
        int halfWidth = width / 2;
        return brightnessRecursive(node.NW, halfWidth) + brightnessRecursive(node.NE, halfWidth) +
        brightnessRecursive(node.SE, halfWidth) + brightnessRecursive(node.SW, halfWidth);
    }

    private Pixel findColor(TreeNode<Pixel> node, int x, int y, int width, int i, int j) 
    {
        
        if (node.isLeaf()) {
            return node.value;
        }
    
        int halfWidth = width / 2;
        boolean inTopHalf = j < y + halfWidth;
        boolean inLeftHalf = i < x + halfWidth;
    
        if (inTopHalf) {
            if (inLeftHalf) {
                return findColor(node.NW, x, y, halfWidth, i, j);
            } else {
                return findColor(node.NE, x + halfWidth, y, halfWidth, i, j);
            }
        } else {
            if (inLeftHalf) {
                return findColor(node.SW, x, y + halfWidth, halfWidth, i, j);
            } else {
                return findColor(node.SE, x + halfWidth, y + halfWidth, halfWidth, i, j);
            }
        }
    }

}
