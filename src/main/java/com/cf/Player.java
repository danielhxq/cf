package com.cf;

import java.io.BufferedReader;
import java.io.IOException;

public class Player {
	
	private String playerName;
	private Disc disc;

	private boolean isWin = false;

	public Player(String playerName, Disc disc) {
		this.playerName = playerName;
		this.disc = disc;
	}

	public void play(BufferedReader br) throws IOException {
		while (true) {
			System.out.println("\n\nPlayer " + playerName + " play:");
			String val = br.readLine();
			if (!validate(val)) {
				System.out.println("Invalid symbol. Please submit integer (0 ~ 6).");
				continue;
			}
			int column = Integer.parseInt(val);
			if (disc.isPlayable(column)) {
				if (disc.canWin(column)) {
					disc.play(column);
					disc.printBoard();
					System.out.println("\n\nPlayer " + playerName + " wins!!!");
					isWin = true;
					break;
				} else {
					disc.play(column);
				}
				break;
			} else {
				System.out.println("Column " + column + " is full!!");
			}
		}
		disc.printBoard();
	}

	public boolean isWin() {
		return isWin;
	}

	private boolean validate(String in) {
		return in.matches("[0-6]");
	}
}
