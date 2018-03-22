package com.soffid.iam.api;

import java.util.LinkedList;
import java.util.concurrent.Future;

public abstract class AsyncList<E> extends LinkedList<E> implements Future<LinkedList<E>> {
	private static final long serialVersionUID = 1L;
}
