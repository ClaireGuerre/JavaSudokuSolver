package co.sudoku;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class Node<T> {
    T value;
    Node<T> parent;
    Set<Node<T>> children = new HashSet<>();

    public Node(T t) {
        this.value = t;
        this.parent = null;
    }

    public Node<T> addChild(Node<T> n) {
        n.parent = this;
        children.add(n);
        return n;
    }

    public T findValidChild(Function<T, Boolean> isValid) {
        if (isValid.apply(value)) {
            return value;
        } else {
            for (Node<T> node: children) {
                T res = node.findValidChild(isValid);
                if (res != null) {
                    return res;
                }
            }
            return null;
        }
    }

    public Node<T> getRoot() {
        Node<T> root = this;
        while (root.parent != null) {
            root = root.parent;
        }
        return root;
    }
}
