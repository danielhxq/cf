package com.cf;

import org.apache.log4j.Logger;

import com.google.common.primitives.UnsignedLong;

public class Disc {

	private final static Logger LOGGER = Logger.getLogger(Disc.class);

	private final static int WIDTH = 7;
	private final static int HEIGHT = 6;

	private long mask = 0L;
	private long currentPosition = 0L;
	private boolean firstPlayer = false;
	private int discStatus = 0;
	private final static int FULL = 0x7F;
	private final static char[] disc = new char[(HEIGHT + 1) * WIDTH];

	public boolean isPlayable(int col) {
		long playable = (mask & topCheck(col));
		if (playable == 0L) {
			return true;
		}
		discStatus |= (1L << col);
		return false;
	}

	private long topCheck(int col) {
		return (1L << (HEIGHT - 1)) << (col * (HEIGHT + 1));
	}

	private long bottomCheck(int col) {
		return 1L << (col * (HEIGHT + 1));
	}

	private long columnCheck(int col) {
		return ((1L << HEIGHT) - 1) << (col * (HEIGHT + 1));
	}

	public void play(int col) {
		firstPlayer = !firstPlayer;
		currentPosition ^= mask;
//		int location = Long.SIZE
//				- Long.numberOfLeadingZeros((currentPosition | (mask + bottomCheck(col))) & columnCheck(col)) - 1;
		 int location = convert((currentPosition | (mask + bottomCheck(col)))
		 & columnCheck(col)).length() - 1;

		disc[rotate(location)] = firstPlayer ? 'G' : 'R';
		mask = mask | (mask + bottomCheck(col));
	}

	private int rotate(int index) {
		int tmp = index;
		int row = tmp / WIDTH;
		int col = tmp % WIDTH;
		return (WIDTH - 1 - col) * (WIDTH) + row;
	}

	public boolean canWin(int col) {
		long pos = currentPosition;
		pos = pos | ((mask + bottomCheck(col)) & columnCheck(col));
		return this.alignment(pos);
	}

	private boolean alignment(long pos) {
		long m = pos & (pos >> (HEIGHT + 1));

		if ((m & (m >> (2 * (HEIGHT + 1)))) > 0L) {
			return true;
		}
		// System.out.println((m & (m >> (2 * (HEIGHT + 1)))));

		m = pos & (pos >> HEIGHT);
		if ((m & (m >> (2 * HEIGHT))) > 0L) {
			return true;
		}
		// System.out.println((m & (m >> (2 * HEIGHT))));

		m = pos & (pos >> (HEIGHT + 2));
		if ((m & (m >> (2 * (HEIGHT + 2)))) > 0L) {
			return true;
		}
		// System.out.println((m & (m >> (2 * (HEIGHT + 2)))));
		m = pos & (pos >> 1L);
		if ((m & (m >> 2L)) > 0L) {
			return true;
		}

		// System.out.println((m & (m >> 2L)));
		return false;
	}

	public boolean isFull() {
		return FULL == discStatus;
	}

	public void printBoard() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < WIDTH; i++) {
			sb.append('|');
			for (int j = 0; j < HEIGHT + 1; j++) {
				sb.append(" ");
				sb.append(disc[j + ((HEIGHT + 1) * i)] == '\0' ? " " : disc[j + ((HEIGHT + 1) * i)]);
				sb.append(" ");
				sb.append('|');
			}
			sb.append(System.getProperty("line.separator"));
		}
		System.out.println(sb.toString());
	}

	@Deprecated
	public String convert(long i) {
		UnsignedLong tmp = UnsignedLong.valueOf(i);
		StringBuilder sBuilder = new StringBuilder();

		do {
			UnsignedLong t = tmp.mod(UnsignedLong.fromLongBits(2L));
			tmp = tmp.dividedBy(UnsignedLong.fromLongBits(2L));
			sBuilder.append(t.intValue());
		} while (tmp.compareTo(UnsignedLong.ZERO) > 0);
		return sBuilder.toString();

	}
}
