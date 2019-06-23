package org.vincent.binarysearch;

/**
 * @author PengRong
 * @package org.vincent.binarysearch
 * @ClassName MainTest.java
 * @date 2019/6/22 - 18:53
 * @ProjectName JavaAopLearning 二叉排序树测试
 * @Description: TODO
 */
public class MainTest {
    public static void main(String[] args) {
        int a;
        int[] b =new int[2];
        // TODO Auto-generated method stub
        BinaryTree tree = new BinaryTree();
        tree.insert(6, 6);// 插入操作,构造图一所示的二叉树
        tree.insert(3, 3);
        tree.insert(14, 14);
        tree.insert(16, 16);
        tree.insert(10, 10);
        tree.insert(9, 9);
        tree.insert(13, 13);
        tree.insert(11, 11);
        tree.insert(12, 12);
        System.out.println("删除前先序遍历结果，结果是一个根据key的递增有序序列");
        tree.preOrder(tree.getRoot());
        System.out.println("--------preOrderWithStack--------");
        tree.preOrderWithStack(tree.getRoot());
        System.out.println("删除前中序遍历结果，结果是一个根据key的递增有序序列");
        tree.inOrder(tree.getRoot());// 中序遍历操作
        System.out.println("------inOrderWithStack-------");
        tree.inOrderWithStack(tree.getRoot());// 中序遍历操作
        System.out.println("删除前后续遍历结果，结果是一个根据key的递增有序序列");
        tree.postOrder(tree.getRoot());// 后续遍历操作
        System.out.println("------postOrderWithStack-------");
        tree = new BinaryTree();
        tree.insert(6, 6);// 插入操作,构造图一所示的二叉树
        tree.insert(3, 3);
        tree.insert(14, 14);
        tree.postOrderWithStack(tree.getRoot());// 后续遍历操作
        System.out.println("--------levelOrder--------");
       tree.levelOrder(tree.getRoot());

       /* tree.update(12, 200);
        System.out.println("更新节点值中序遍历结果  key=12的值");
        tree.inOrder(tree.getRoot());
        System.out.println("删除节点10之后遍历结果");

        tree.delete(10);// 删除操作
        tree.inOrder(tree.getRoot());*/
    }
}
