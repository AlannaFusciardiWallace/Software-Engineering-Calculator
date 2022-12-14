package com.example;

public class stack<E> {
	private StackItem<E> top;

	public stack() {
		top = null;
	}

	public void push(E x) {
		StackItem<E> newItem = new StackItem<E>(x);
		newItem.next = top;
		top = newItem;
	}

	public boolean empty() {
		return top == null;
	}

	public E peek() {
		return top.value;
	}

	public void pop() {
		top = top.next;
	}
}
