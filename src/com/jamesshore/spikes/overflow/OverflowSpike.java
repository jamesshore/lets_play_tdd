package com.jamesshore.spikes.overflow;

public class OverflowSpike {

	public static void main(String[] args) {
		double a = 9223372036854775807d;
		System.out.println(a + "");
		System.out.println(a * 10000);
		System.out.println(Long.MAX_VALUE);
	}

}
