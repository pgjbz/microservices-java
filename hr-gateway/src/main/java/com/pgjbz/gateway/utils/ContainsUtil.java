package com.pgjbz.gateway.utils;

public class ContainsUtil {

	private ContainsUtil(){}

	public static <T> boolean contains(T in, T ...check) {
		for(T item: check)
			if(item == in)
				return true;
		return false;
	}
}
