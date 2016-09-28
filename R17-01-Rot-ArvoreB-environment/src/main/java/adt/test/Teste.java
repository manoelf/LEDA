package adt.test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class Teste {

	@Test
	public void test() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(10);
		list.add(0,12);
		list.add(10);
		System.out.println(list.toString());
		System.out.println(list.get(0));
		System.out.println(list.get(1));
	}

}
