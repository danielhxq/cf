package com.cf;

import org.junit.Assert;
import org.junit.Test;

public class BitTest {

	private final static int WIDTH = 7;
	private final static int HEIGHT = 6;

	@Test
	public void winTestFirstRowVertical() {
		Disc disc1 = new Disc();
		disc1.play(0);//1
		System.out.println(disc1.isPlayable(0));
		disc1.play(1);//2
		disc1.play(0);//1

		disc1.play(1);//2
		disc1.play(0);//1
		disc1.play(1);//2
		disc1.play(1);//1
		disc1.play(1);//2
		disc1.play(1);//1
		disc1.play(2);//2
		disc1.play(3);//1
		disc1.play(4);//2
		disc1.play(5);//1
		disc1.play(6);//2
		disc1.play(6);//1
		disc1.play(3);//2
//		disc1.play(0);//1
		
		disc1.printBoard();
		
		Assert.assertEquals(true, disc1.canWin(0));
		disc1.printBoard();
	}
	
	@Test
	public void winTestFirstRowDiagonal() {
		Disc disc1 = new Disc();
		disc1.play(3);//1
		disc1.play(2);//2
		disc1.play(1);//1

		disc1.play(0);//2
		disc1.play(2);//1
		disc1.play(6);//2
		disc1.play(0);//1
		disc1.play(1);//2
		disc1.play(0);//1
		disc1.play(5);//2
		disc1.play(0);//1
		disc1.play(5);//2
		disc1.printBoard();
//		disc1.play(1);//1
		
		Assert.assertEquals(true, disc1.canWin(1));
		disc1.play(1);//1
		disc1.printBoard();
	}
	
	@Test
	public void winTestFirstRowHorizontal() {
		Disc disc1 = new Disc();
		disc1.play(3);//1
		disc1.play(6);//2
		disc1.play(2);//1

		disc1.play(6);//2
		disc1.play(1);//1
		disc1.play(6);//2
//		disc1.play(0);//1
//		disc1.play(1);//2
//		disc1.play(0);//1
//		disc1.play(5);//2
//		disc1.play(0);//1
//		disc1.play(5);//2
		disc1.printBoard();
//		disc1.play(1);//1
		
		Assert.assertEquals(true, disc1.canWin(0));
		disc1.play(1);//1
		disc1.printBoard();
	}
	
	@Test
	public void testFull() {
		long discStatus = 0;
		discStatus |= (1L << 0);
		discStatus |= (1L << 1);
		discStatus |= (1L << 2);
		discStatus |= (1L << 3);
		discStatus |= (1L << 4);
		discStatus |= (1L << 5);
		discStatus |= (1L << 6);
		Assert.assertEquals(true, discStatus == 0x7F);
	}
}
