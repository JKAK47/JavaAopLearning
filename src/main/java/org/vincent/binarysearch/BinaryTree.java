package org.vincent.binarysearch;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * @author PengRong
 * @package org.vincent.binarysearch
 * @ClassName BinaryTree.java
 * @date 2019/6/22 - 18:52
 * @ProjectName JavaAopLearning
 * @Description: 二叉排序树
 */
public class BinaryTree extends AbsBinaryTree {
    /**
     * 根节点
     */
    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // 二叉排序树查找节点
    // 找到和key相等则返回相应节点，否则返回 null。
    @Override
    public Node find(int key) {
        // TODO Auto-generated method stub
        Node currentNode = this.root;
        // currentNode.key和 key不等才需要循环
        while ((currentNode != null) && (currentNode.key != key)) {
            if (key < currentNode.key) {
                currentNode = currentNode.leftChild;
            } else if (key > currentNode.key) {
                currentNode = currentNode.rightChild;
            }
        }
        return currentNode;

    }

    /**
     * 二叉排序树 插入元素节点：如果key和原来的元素key一样，则insert 函数是更新对应的 value 值
     *
     * @param key
     * @param value
     */
    @Override
    public void insert(int key, int value) {
        // TODO Auto-generated method stub
        if (this.root == null) {
            this.root = new Node(key, value);
            return;
        }
        Node currentNode = this.root;
        Node parentNode = this.root;// 指向currentNode节点的父节点
        boolean isLeftChild = true;
        // 寻找插入位置
        while (currentNode != null) {
            parentNode = currentNode;
            if (key < currentNode.key) {
                currentNode = currentNode.leftChild;
                isLeftChild = true;
            } else if (key > currentNode.key) {
                currentNode = currentNode.rightChild;
                isLeftChild = false;
            } else {
                // 插入的节点key和二叉树中节点key相等无需插入
                // parentNode 和 currentNode两个引用指向相同Node对象，引用变量相等，只需要更改value
                break;
            }
        }
        // 插入节点
        if (parentNode != currentNode) {
            Node newNode = new Node(key, value);
            if (isLeftChild) {
                parentNode.leftChild = newNode;
            } else {
                parentNode.rightChild = newNode;
            }

        } else {
            // 如果待插入节点和二叉树中节点一样；则只要更改值
            currentNode.setValue(value);
        }
    }

    @Override
    public boolean delete(int key) {
        // TODO Auto-generated method stub
        Node currentNode = this.root;// 用来保存待删除节点
        Node parentNode = this.root;// 用来保存待删除节点的父亲节点
        boolean isLeftChild = true;// 用来保存待删除节点是父亲节点的左孩子还是右孩子
        // 寻找删除节点并记录删除节点的父节点以及他是父节点的左孩子还是右孩子
        while ((currentNode != null) && (currentNode.key != key)) {
            parentNode = currentNode;
            if (key < currentNode.key) {
                currentNode = currentNode.leftChild;
                isLeftChild = true;
            } else {
                currentNode = currentNode.rightChild;
                isLeftChild = false;
            }
        }
        if (currentNode == null)
            return false;// 没找到待删除节点
        // 要删除的节点为叶子节点，删除的第一种情况
        if ((currentNode.leftChild == null)
                && (currentNode.rightChild == null)) {
            if (currentNode == this.root) {
                this.root = null;
            } else if (isLeftChild) {
                parentNode.leftChild = null;
            } else {
                parentNode.rightChild = null;
            }
            // 要删除的节点只有左孩子 第二种情况
        } else if ((currentNode.rightChild == null)
                && (currentNode.leftChild != null)) {
            if (currentNode == this.root) {
                this.root = currentNode.leftChild;
            } else if (isLeftChild) {
                parentNode.leftChild = currentNode.leftChild;
            } else {
                parentNode.rightChild = currentNode.leftChild;
            }
            // 要删除的节点只有右孩子 第三种情况
        } else if ((currentNode.leftChild == null)
                && (currentNode.rightChild != null)) {
            if (currentNode == this.root) {
                this.root = currentNode.rightChild;
            } else if (isLeftChild) {
                parentNode.leftChild = currentNode.rightChild;
            } else {
                parentNode.rightChild = currentNode.rightChild;
            }
        } // 最后一种情况，待删除节点既有左子树又有右子树
        else {
            // 将待删除节点的右子树最小节点赋值给删除节点的key,value，那么删除后新的二叉树也是二叉排序树
            // 思路：删除右子树中key值最小的节点，并返回，然后用这个节点的值赋值删除节点的key和value
            // 右子树中key最小的节点一定不含左子树,所以删除这个key最小的节点一定是属于叶子节点或者只有右子树的节点
            Node directPostNode = this.getDirectPostNode(currentNode);
            currentNode.key = directPostNode.key;
            currentNode.value = directPostNode.value;
        }

        return true;
    }

    // 获取到待删除节点的中序直接后继节点。将该后继节点从二叉树中删除并返回
    @Override
    public Node getDirectPostNode(Node delNode) {
        // TODO Auto-generated method stub
        // 方法作用为得到待删除节点的直接后继节点

        Node parentNode = delNode;// 用来保存待删除节点的直接后继节点的父亲节点
        Node direcrPostNode = delNode;// 用来保存待删除节点的直接后继节点
        Node currentNode = delNode.rightChild;// 待删除节点右子树
        while (currentNode != null) {
            parentNode = direcrPostNode;
            direcrPostNode = currentNode;
            currentNode = currentNode.leftChild;
        }
        if (direcrPostNode != delNode.rightChild) {// 如果直接后继节点不是待删除节点右孩子节点，
            // 直接后继节点一定是他父母节点的左孩子节点
            parentNode.leftChild = direcrPostNode.rightChild;// 后继节点的父节点指向后继节点的右孩子
            direcrPostNode.rightChild = null;// 直接后继节点右孩子为空
        } else {
            // 如果直接后继节点是待删除节点右孩子节点，那就说明待删除节点上右子树的根节点只有右子树。
            parentNode.rightChild = direcrPostNode.rightChild;
            direcrPostNode.rightChild = null;
        }
        return direcrPostNode;// 返回此直接后继节点
    }

    /**
     * 基于递归的先序遍历
     *
     * @param rootNode
     */
    @Override
    public void preOrder(Node rootNode) {
        // TODO Auto-generated method stub
        if (rootNode != null) {
            System.out.println(rootNode.key + " " + rootNode.value);
            this.preOrder(rootNode.leftChild);
            this.preOrder(rootNode.rightChild);
        }
    }

    /**
     * 基于栈的先序遍历
     *
     * @param rootNode
     */
    @Override
    public void preOrderWithStack(Node rootNode) {
        Stack<Node> stack = new Stack<>();
        Node currentNode = rootNode;
        /** root 根元素 不为null 并且 栈不为空
         * */
        while (currentNode != null || !stack.isEmpty()) {
            // 先输出根节点 ，迭代访问 节点左孩子节点，并将左孩子节点入栈
            if (currentNode != null) {
                System.out.println("key = " + currentNode.key + " value = " + currentNode.value);
                stack.add(currentNode);
                currentNode = currentNode.leftChild;
            }
            // 加入遍历到最终叶子节点了,栈不为空
            // 这个时候弹出一个栈元素，取它 右孩子节点
            if (Objects.isNull(currentNode) && !stack.isEmpty()) {
                Node pop = stack.pop();
                currentNode = pop.rightChild;
            }
        }
    }

    /**
     * 中序遍历 基于栈， 先左子树， 根节点 ，右子树
     *
     * @param rootNode
     */
    @Override
    public void inOrderWithStack(Node rootNode) {
        Stack<Node> stack = new Stack<>();
        Node currentNode = rootNode;
        /** root 根元素 不为null 并且 栈不为空
         * */
        while (currentNode != null || !stack.isEmpty()) {
            /** 一直遍历左子树*/
            if (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.leftChild;
                continue;
            }
            /** 先输出根节点，然后一直遍历右子树 */
            if (currentNode == null && !stack.isEmpty()) {
                Node pop = stack.pop();
                System.out.println("key = " + pop.getKey() + " value = " + pop.getValue());
                currentNode = pop.getRightChild();
            }
        }
    }

    /**
     * 层级排序
     *
     * @param rootNode
     */
    public void levelOrder(Node rootNode) {
        if (Objects.isNull(rootNode)) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(rootNode);
        while (!queue.isEmpty()) {
            /** 输出当前层级元素*/
            Node poll = queue.poll();
            System.out.println("key = " + poll.getKey() + " value = " + poll.getValue());
            /** 该节点 左子节点入队列*/
            if (Objects.nonNull(poll.getLeftChild())) {
                queue.offer(poll.getLeftChild());
            }
            /** 该节点 右子节点入队列*/
            if (Objects.nonNull(poll.getRightChild())) {
                queue.offer(poll.getRightChild());
            }
        }
    }

    @Override
    public void inOrder(Node rootNode) {
        // TODO Auto-generated method stub
        if (rootNode != null) {
            this.inOrder(rootNode.leftChild);
            System.out.println(rootNode.key + " " + rootNode.value);
            this.inOrder(rootNode.rightChild);
        }
    }

    /**
     * 后续遍历 基于栈遍历元素;
     *
     * @param rootNode
     */
    @Override
    public void postOrderWithStack(Node rootNode) {
        Stack<Node> stack = new Stack<>();/** 缓存当前遍历元素的栈，用于回溯。*/
        Node currentNode = rootNode;/** 记录当前遍历元素 */
        Node preVisitor = null;/** 记录当前访问输出的元素 */
        while (currentNode != null || !stack.isEmpty()) {
            /** 左子树*/
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }
            /** 左子树为空后，弹出当前左节点的父节点  */
            currentNode = stack.pop();
            /** 右子树 */
            if (currentNode.getRightChild() != null
                    && preVisitor != currentNode.getRightChild()) {/** 放置右子树访问完后，再次去访问右子树 。 */
                stack.push(currentNode);
                currentNode = currentNode.getRightChild();
                continue;
            }
            /** 根节点 */
            System.out.println("key = " + currentNode.getKey() + " value = " + currentNode.getValue());
            preVisitor = currentNode;/** 通过记录当前访问的元素*/
            currentNode = null;
        }
        System.out.println("");
    }


    @Override
    public void postOrder(Node rootNode) {
        // TODO Auto-generated method stub
        if (rootNode != null) {
            this.postOrder(rootNode.leftChild);
            this.postOrder(rootNode.rightChild);
            System.out.println(rootNode.key + " " + rootNode.value);
        }
    }


    /**
     * // 基于二叉排序树查找find查找节点，然后通过Node的setValue将新值赋值过去。
     */
    @Override
    public boolean update(int key, int value) {
        // TODO Auto-generated method stub
        Node node = this.find(key);
        if (!Objects.isNull(node)) {
            node.setValue(value);
        }
        return true;
    }
}
