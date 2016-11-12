/**
 * 
 */
package avlTree;

/**
 * @author grahamw0
 *
 */
public class AvlTree {
  private AvlNode root;

  public AvlTree() {
    root = null;
  }

  /**
   * Starts the recursive insert method
   * 
   * @param data
   */
  public void insert(Comparable data) {
    AvlNode node = new AvlNode(data);
    insertAVL(root, node);
  }

  /**
   * Recursive insert method FINISH THIS AND REMOVE
   * 
   * @param node1 The node currently being compared (should start w/ root)
   * @param node2 The node to be inserted
   */
  private void insertAVL(AvlNode node1, AvlNode node2) {
    if (node1 == null) {
      root = node2;
    } else {

    }
  }
  
  private void recursiveBalance(AvlNode node) {
    setBalance(node);
    int balance = node.getBalance();
    
    if(balance == -2) {
      if(height(node.getLeft().getLeft()) >= height(node.getLeft().getRight())) {
        node = rotateRight(node);
      } else {
        node = doubleRotateLeftRight(node);
      }
    } else if (balance == 2) {
      if(height(node.getRight().getRight()) >= height(node.getRight().getLeft())) {
        node = rotateLeft(node);
      } else {
        node = doubleRotateRightLeft(node);
      }
    }
    
    if(node.getParent() != null) {
      recursiveBalance(node.getParent());
    } else {
      this.root = node;
      System.out.println("Balance Finish"); // TODO: Remove test statement
    }
  }
  
  private AvlNode rotateLeft(AvlNode node) {
    AvlNode v = node.getRight();
    v.setParent(node.getParent());
    node.setRight(v.getLeft());
    
    if(node.getRight() != null)
      node.getRight().setParent(node);
    
    v.setLeft(node);
    node.setParent(v);
    
    if(v.getParent() != null) {
      if(v.getParent().getRight() == node) {
        v.getParent().setRight(v);
      } else if (v.getParent().getLeft() == node) {
        v.getParent().setLeft(v);
      }
    }
    setBalance(node);
    setBalance(v);
    
    return v;
  }
  
  private AvlNode rotateRight(AvlNode node) {
    AvlNode v = node.getLeft();
    v.setParent(node.getParent());
    node.setLeft(v.getRight());
    
    if(node.getLeft() != null)
      node.getLeft().setParent(node);
    
    v.setRight(node);
    node.setParent(v);
    
    if(v.getParent() != null) {
      if(v.getParent().getRight() == node) {
        v.getParent().setRight(v);
      } else if (v.getParent().getLeft() == node) {
        v.getParent().setLeft(v);
      }
    }
    setBalance(node);
    setBalance(v);
    
    return v;
  }
  
  private AvlNode doubleRotateLeftRight(AvlNode node) {
    node.setLeft(rotateLeft(node.getLeft()));
    return rotateRight(node);
  }
  
  private AvlNode doubleRotateRightLeft(AvlNode node) {
    node.setRight(rotateRight(node.getRight()));
    return rotateLeft(node);
  }



  /****** Helper Functions ***********/
  private AvlNode successor(AvlNode q) {
    if (q.getRight() != null) {
      AvlNode r = q.getRight();
      while (r.getLeft() != null) {
        r = r.getLeft();
      }
      return r;
    } else {
      AvlNode p = q.getParent();
      while (p != null && q == p.getRight()) {
        q = p;
        p = q.getParent();
      }
      return p;
    }
  }
  
  private int height(AvlNode node) {
    if (node == null)
      return -1;
    if (node.getLeft() == null && node.getRight() == null)
      return 0;
    if (node.getLeft() == null)
      return 1 + height(node.getRight());
    if (node.getRight() == null)
      return 1 + height(node.getLeft());
    return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
  }
  
  private void setBalance(AvlNode node) {
    node.setBalance(height(node.getRight()) - height(node.getLeft()));
  }
  
  public void inOrderPrint() {
    inOrderPrintRecur(root);
  }
  
  private void inOrderPrintRecur(AvlNode node) {
    if (node == null)
      return;
    inOrderPrintRecur(node.getLeft());
    System.out.println(node);
    inOrderPrintRecur(node.getRight());
  }

}