package com.cf;

import org.junit.Assert;
import org.junit.Test;

public class RotateTest {

	private final static int WIDTH = 7;
	private final static int HEIGHT = 6;

	@Test
	public void rotate() {
		int tmp = 14;
		int row = tmp / WIDTH;
		int col = tmp % WIDTH;
		Assert.assertEquals(44, (WIDTH - 1 - col) * (WIDTH) + row);
	}
}
