//Time complexity:- O(n)
//Space complexit:- O(n)
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
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        //We can do using Breath first search
        if(root == null)
            return result;
        
        //Store all the nodes for BFS
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {

            //Since we need levels/distinction we keep track of length
            int len = q.size();

            for(int i = 0; i < len; i++) {

                //Start popping elements one by one
                TreeNode curr = q.poll();

                //explore its neighbors
                if(curr.left != null)
                    q.add(curr.left);
                
                if(curr.right != null)
                    q.add(curr.right);

                //If the node is last element in that level, then it is on right side
                if(i == len - 1)
                    result.add(curr.val);
    
            }
        }
        
        return result;
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
    public List<Integer> rightSideView(TreeNode root) {
        
        //Depth first search
        List<Integer> result = new ArrayList<>();

        //we will perform level order traversal but only add the last element on that level
        dfs(root, 0, result);
        return result;
    }

    public void dfs(TreeNode root, int level, List<Integer> result) {

        if(root == null)
            return;
        
        //If the value at that level is present already then no need to add left values
        if(result.size() == level) {
            result.add(root.val);
        }

        dfs(root.right, level + 1, result);
        dfs(root.left, level + 1, result);
    }
}

//TC :- O(n)
//SC :- O(h) , stack space
*/