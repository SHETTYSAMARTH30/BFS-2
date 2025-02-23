//Time complexity:- O(n)
//Space complexity:- O(n) :- leaf node has n/2 nodes

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        
        //Perform Breadth first search
        //if x and y belong to same parent return false
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        boolean xFound = false;
        boolean yFound = false;

        while(!q.isEmpty()) {

            int len = q.size();

            for(int i = 0; i < len; i++) {

                TreeNode curr = q.poll();

                //check if x, y is found
                if(curr.val == x)
                    xFound = true;
                
                if(curr.val == y)
                    yFound = true;

                //Since we have parent right now, check whether x and y does not belong to same parent
                if(curr.left != null && curr.right != null) {

                    //x and y should belong to different parent
                    if(curr.left.val == x && curr.right.val == y)
                        return false;
                    
                    if(curr.left.val == y && curr.right.val == x)
                        return false;
                }

                //add children to queue
                if(curr.left != null)
                    q.add(curr.left);

                if(curr.right != null)
                    q.add(curr.right);
            }

            //since we already performed parent check beforehand. We need to check levels
            //if we found both x and y at same level return true
            if(xFound && yFound)
                return true;
            
            //This means only one element was found at this level
            if(xFound || yFound)
                return false;
        }

        //if we do not find both x and y
        return false;
    }
}

/* DFS

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    List<Integer> lev = new ArrayList<>();
    List<TreeNode> par = new ArrayList<>();
    
    public boolean isCousins(TreeNode root, int x, int y) {
        
        findCousin(root, null, 0, x, y);
        
        if(lev.get(0) == lev.get(1) && par.get(0) != par.get(1))
            return true;
        else
            return false;
        
    }
    
    public void findCousin(TreeNode t, TreeNode prev, int levels, int x, int y){
        
        if(t == null)
            return;
        
        else{
            
            if(t.val == x || t.val == y){
                lev.add(levels);
                par.add(prev);
            }
            
            findCousin(t.left, t, levels+1, x, y);
            findCousin(t.right, t, levels+1, x, y);
        }
    }
}
*