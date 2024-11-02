import java.util.Iterator;
import java.util.NoSuchElementException;

public class QuadTreeImageIterator<Pixel extends Number> implements Iterator<TreeNode<Pixel>>
{
    private TreeNode<Pixel>[] queue; 
    private int size = 0; 
    private int index = 0;
    // Time Complexity: O(1)
    public QuadTreeImageIterator(QuadTreeImage<Pixel> image)
    {
        System.out.print("null");

    }

    // Time Complexity: O(1)
    public boolean hasNext()
    {
        return index < size;
    }

    // Time Complexity: O(1) amortized
    public TreeNode<Pixel> next()
    {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in quadtree");
        }
        return queue[index++];
    }
}
