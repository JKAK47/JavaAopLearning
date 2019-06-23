package org.vincent.binarysearch;

/**
 * @author PengRong
 * @package org.vincent.binarysearch
 * @ClassName AbsBinaryTree.java
 * @date 2019/6/22 - 18:51
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */
public abstract class AbsBinaryTree {
    public abstract Node find(int key);// 查找指定节点

    public abstract boolean update(int key, int value);

    public abstract void insert(int key, int value); // 插入节点

    public abstract boolean delete(int key); // 删除指定节点

    public abstract Node getDirectPostNode(Node delNode); // 得到待删除节点的直接后继节点

    public abstract void preOrder(Node rootNode); // 先序遍历树

    /**
     * 先序遍历基于栈
     *
     * @param rootNode
     */
    public abstract void preOrderWithStack(Node rootNode);

    public abstract void inOrder(Node rootNode); // 中序遍历树

    /**
     * 中序遍历树 基于栈
     *
     * @param rootNode
     */
    public abstract void inOrderWithStack(Node rootNode); //

    /**
     * 层级排序
     *
     * @param rootNode
     */
    public abstract void levelOrder(Node rootNode);

    /**
     * 后续遍历
     *
     * @param rootNode
     */
    public abstract void postOrder(Node rootNode); // 后序遍历树

    /**
     * 后序遍历树 基于栈
     *
     * @param rootNode
     */
    public abstract void postOrderWithStack(Node rootNode); //
}
