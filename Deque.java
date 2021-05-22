public class Deque<T> {

    private DoubleNode head = null;
    private DoubleNode tail = null;
    int size = 0;


    class DoubleNode {
        private T value;
        private DoubleNode next;
        private DoubleNode prev;

        public DoubleNode(T value) {
            this.value = value;
            next = null;
            prev = null;
        }

        public void setPrev(DoubleNode prev) {
            this.prev = prev;
        }

        public DoubleNode getPrev() {
            return this.prev;
        }

        public T getValue() {
            return this.value;
        }

        public void setNext(DoubleNode next) {
            this.next = next;
        }

        public DoubleNode getNext() {
            return this.next;
        }

        public DoubleNode getNode(int index) {
            if (index < size / 2) {
                DoubleNode current = head;
                for (int i = 0; i < index; i++) {
                    current = current.getNext();
                }
                return current;
            } else {
                DoubleNode current = tail;
                for (int i = size - 1; i >= index; i--) {
                    current = current.getPrev();
                }
                return current;
            }
        }


    }

    public void insertAtBeginning(T value) {
        DoubleNode node = new DoubleNode(value);

        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        }
        size++;
    }


    public void insertAtEnd(T value) {
        DoubleNode node = new DoubleNode(value);
        if (tail == null)
            insertAtBeginning(node.getValue());

        else {
            DoubleNode current = node.getNode(size);
            current.setNext(node);
            node.setPrev(current);
            tail = node;
        }
        size++;
    }

    public T removeFromBeginning() {
        if (head == null) {
            System.out.println("NONE");
        }

        DoubleNode oldNode = head;
        T value = head.getValue();
        head = oldNode.getNext();

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            oldNode.getNext().setPrev(null);
            size--;
        }

        return value;
    }

    public T removeFromEnd() {
        if (tail == null) {
            System.out.println("NONE");
        }

        DoubleNode oldNode = tail;
        T value = tail.getValue();
        DoubleNode prevNode = oldNode.getPrev();

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = prevNode;
            prevNode.setNext(null);
            size--;
        }

        return value;
    }

    public void insertBefore(T value, DoubleNode node) {

        DoubleNode i_B = new DoubleNode(value);
        DoubleNode current = node;
        DoubleNode previous = current.getPrev();

        if (head.getPrev() == null)
            insertAtBeginning(value);
        else {
            i_B.setPrev(previous);
            i_B.setNext(current);
            i_B.getPrev().setNext(i_B);
            current.setPrev(i_B);
        }
        size++;
    }

    public void insertAfter(T value, DoubleNode node) {

        DoubleNode i_A = new DoubleNode(value);
        DoubleNode current = node;
        DoubleNode next = current.getNext();

        if (head.getPrev() == null) {
            insertAtBeginning(value);
        } else {
            i_A.setNext(next);
            i_A.setPrev(current);
            i_A.getNext().setPrev(i_A);
            current.setNext(i_A);
        }
        size++;
    }

    public void remove(DoubleNode node) {

        DoubleNode current = node;

        if (current.getPrev() == null) {
            removeFromBeginning();
        } else if (current.getNext() == null) {
            removeFromEnd();
        } else {
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }
    }

    public void moveToFront(DoubleNode node) {

        DoubleNode current = node;
        T value = current.getValue();

        if (current.getPrev() != null) {
            remove(current);
            insertBefore(value, head);
        }
    }

    public void moveToEnd(DoubleNode node) {

        DoubleNode current = node;
        T value = current.getValue();

        if (current.getNext() != null) {
            remove(current);
            insertAfter(value, tail);
        }
    }
}


