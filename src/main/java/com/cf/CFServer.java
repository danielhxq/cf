package com.cf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class CFServer {

	private final static Logger LOGGER = Logger.getLogger(CFServer.class);

	public static void main(String[] args) {
		CFServer server = null;
		try {
			server = new CFServer();
			server.startUp();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startUp() throws NumberFormatException, IOException {
		Disc disc = new Disc();
		Player player1 = new Player("Daniel", disc);
		Player player2 = new Player("Mike", disc);
		BufferedReader br = null;
		System.out.println("WELCOME TO CONNECT FOUR!!!");
		LOGGER.info("Game Started...");
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				player1.play(br);
				if (player1.isWin()) {
					break;
				}

				player2.play(br);
				if (player2.isWin()) {
					break;
				}

				if (disc.isFull()) {
					System.out.print("Game drawn.");
					break;
				}
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}

	}

}
