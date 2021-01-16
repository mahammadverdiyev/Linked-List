import java.util.NoSuchElementException;

public final class MyList<T> {
	private int size = 0;

	private final class Node {
		public T data;
		public Node next;

		public Node(T data) {
			this.data = data;
			this.next = null;
			size += 1;
		}
	}

	public MyList() {
		this.head = null;
		this.tail = null;
	}

	private Node head;
	private Node tail;

	public boolean isEmpty() {
		return head == null;
	}

	public void addFirst(T data) {
		Node newNode = new Node(data);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
	}

	public void addLast(T data) {
		Node newNode = new Node(data);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
	}

	public void add(int index, T data) {
		if (isEmpty() || index == 0 || index < 0)
			addFirst(data);
		else if (index == size || index > size) {
			addLast(data);
		} else {
			Node newNode = new Node(data);

			Node currentNode = head;
			int currentIndex = 0;

			while (currentIndex != index - 1) {
				currentNode = currentNode.next;
				currentIndex += 1;
			}
			newNode.next = currentNode.next;
			currentNode.next = newNode;
		}
	}

	public void removeFirst() {
		if (isEmpty())
			return;
		if (head == tail) {
			head = tail = null;
			size = 0;
			return;
		}
		Node temp = head.next;
		head.next = null;
		head = temp;
		size -= 1;
	}

	public void remove(T data) {
		if (isEmpty())
			return;

		else {
			if (head == tail && head.data == data) {
				head = tail = null;
			} else {
				if (head.data == data)
					removeFirst();
				Node currentNode = head;
				while (currentNode.next != null) {
					if (currentNode.next.data == data) {
						Node temp = currentNode.next.next;
						currentNode.next.next = null;
						currentNode.next = temp;
						size -= 1;
						break;
					} else
						currentNode = currentNode.next;
				}
			}
		}
	}

	public void removeLast() {
		if (isEmpty())
			return;

		if (head == tail) {
			head = tail = null;
			size = 0;
			return;
		}
		Node currentNode = head;
		while (currentNode.next != tail) {
			currentNode = currentNode.next;
		}

		tail = currentNode;
		tail.next = null;

		size -= 1;
	}

	public void removeAt(int index) {
		if (isEmpty())
			return;
		else if (index < 0 || index == 0)
			removeFirst();
		else if (index == size - 1 || index > size - 1)
			removeLast();

		else {
			Node currentNode = head;
			int currentIndex = 0;
			while (currentIndex != index - 1) {
				currentNode = currentNode.next;
				currentIndex += 1;
			}
			Node temp = currentNode.next.next;
			currentNode.next.next = null;
			currentNode.next = temp;
			size -= 1;
		}
	}

	public void clear() {
		if (head == null)
			return;

		Node currentNode = head;
		while (currentNode.next != null) {
			Node temp = currentNode.next;
			currentNode.next = null;
			currentNode = temp;
		}
		head = tail = null;
		size = 0;
	}

	public void push(T data) {
		addFirst(data);
	}

	public T getFirst() {
		if (isEmpty())
			throw new NoSuchElementException();

		else
			return head.data;

	}

	public T getLast() {
		if (isEmpty())
			throw new NoSuchElementException();

		else
			return tail.data;
	}

	@Override
	public String toString() {
		if (isEmpty())
			return "[]";
		StringBuilder sb = new StringBuilder();

		sb = sb.append("[").append(head.data);

		Node currentNode = head.next;

		while (currentNode != null) {
			sb = sb.append(", ").append(currentNode.data);
			currentNode = currentNode.next;
		}
		sb = sb.append("]");

		return sb.toString();

	}

	public int indexOf(T data) {
		int index = 0;
		Node currentNode = head;
		while (currentNode != null) {
			if (currentNode.data == data)
				return index;
			else {
				currentNode = currentNode.next;
				index++;
			}
		}
		return -1;
	}

	public void removeDuplicates() {
		if (head == null || head == tail)
			return;

		Node currentNode = head;

		while (currentNode.next != null) {
			if (currentNode.data == currentNode.next.data) {
				Node temp = currentNode.next.next;
				currentNode.next.next = null;
				currentNode.next = temp;
			} else
				currentNode = currentNode.next;
		}
	}

	public boolean contains(T data) {
		return indexOf(data) != -1;
	}

	public int getSize() {
		return size;
	}

	public void reverse() {
		Node currentNode = head;

		Node previousNode = null;
		Node nextNode = null;

		while (currentNode != null) {
			nextNode = currentNode.next;
			currentNode.next = previousNode;

			previousNode = currentNode;
			currentNode = nextNode;
		}
		head = previousNode;
	}

	public void print() {
		System.out.println(this);
	}

	private void printReverse(Node current) {
		if (current == null)
			return;

		printReverse(current.next);

		System.out.print(current.data + " ");
	}

	public void printReverse() {
		Node current = head;
		printReverse(current);
	}

}
