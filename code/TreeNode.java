public class TreeNode<T extends Number>
{
    ////////////////////////////////////////////////////////
    /**   DO NOT CHANGE/ALTER/REMOVE THESE FIELDS        **/
    /**   DO NOT ADD ANY OTHER FIELDS EITHER              */
    /**/    public TreeNode<T> NW;     /* northwest child */
    /**/    public TreeNode<T> NE;     /* northeast child */
    /**/    public TreeNode<T> SE;     /* southeast child */
    /**/    public TreeNode<T> SW;     /* southwest child */
    /**/    public T value;                             /**/
    ////////////////////////////////////////////////////////
    public TreeNode(T value) {
        this.NW = null;
        this.NE = null;
        this.SE = null;
        this.SW = null;
        this.value = value;
    }


    // Time-complexity: O(1)
    public boolean isLeaf()
    {
        return NW == null && NE == null && SE == null && SW == null;
    }

    // returns the value of the pixel as a string representation without adding any leading/trailing spaces or other characters
    // Time-complexity: O(1)
    public String toString()
    {
        if (isLeaf()) {
            return value.toString();
        } else {
            return "[]";
        }
    }
}
