package game;

import java.util.ArrayList;

public class AI {
	GOLBoard board;
	ArrayList<PatternFilter> filters = new ArrayList<PatternFilter>();
	public void init() {
		
	}
	public void makeMove() {
		int[] location = this.findRevive();
		Boolean madeMove = false;
		int[] location2 = this.findKill(false, -10, -10, -10, -10);
		for(PatternFilter filter: filters) {
			if(filter.checkForPattern(board.getSize(), board.get().clone())[0] == 1) {
				int[] locationPattern = {filter.checkForPattern(board.getSize(), board.get())[1], filter.checkForPattern(board.getSize(), board.get())[2]};
				if(filter.getMode() == GOLBoard.REVIVE) {
					int[] loc1 = this.findRevive();
					System.out.println(filter.getCurrentFilterDissapationXY(locationPattern)[0] + " lots of fun " + filter.getCurrentFilterDissapationXY(locationPattern)[1]);
					board.revive(filter.getCurrentFilterDissapationXY(locationPattern)[0], filter.getCurrentFilterDissapationXY(locationPattern)[1], board.getplayer(), true, false);
					board.resetKillCount();
					board.kill(loc1[3], loc1[4], true, true, false);
					board.resetKillCount();
					board.kill(loc1[5], loc1[6], true, true, false);
					madeMove = true;
				} else {
					board.kill(filter.getCurrentFilterDissapationXY(locationPattern)[0], filter.getCurrentFilterDissapationXY(locationPattern)[1], false, true, false);
					madeMove = true;
				}
			}
		}
		if(!madeMove & location[0] > location2[0]) {
			board.revive(location[1], location[2], board.getplayer(), true, false);
			board.kill(location[3], location[4], true, true, false);
			board.kill(location[5], location[6], true, true, false);
			System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL3");
			System.out.println("REVIVE");
		} else if(!madeMove){
			board.kill(location2[1], location2[2], true, true, false);
			System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL4");
		}
		//board.endTurn(false);
		//board.setCycle(true);
	}
	public void setBoard(GOLBoard board) {
		this.board = board;
	}
	public int[] findKill(boolean ownCell, int dontKillX, int dontKillY, int dontKillX2, int dontKillY2) {
		int[][] research = new int[board.getSize() * board.getSize()][3]; //contains the findings of each iteration of the loop
		for(int i = 0; i < board.getSize(); i ++) {
			for(int j = 0; j < board.getSize(); j ++) {
				//Checks to see if move is logical
				int[][] calculate = board.get().clone(); // a backup for the board after AI calculates many moves ahead
				if(i != dontKillX & j != dontKillY & board.getBoard(i, j) != board.getplayer() & board.getBoard(i, j) != 0 & !ownCell | i != dontKillX & j != dontKillY & i != dontKillX2 & j != dontKillY2 & board.getBoard(i, j) == board.getplayer() & board.getBoard(i, j) != 0 & ownCell ) {
					board.kill(i, j, true, false, false);
					//calculates many moves ahead
					for(int k = 0; k < 1; k ++) {
						board.cycleLife();
					}
					//reads the final yield of the move
					int tilesBlue = board.getTiles(1);
					int tilesRed = board.getTiles(2);
					int finalBenefit = 0;
					if(board.getplayer() == 1) {
						finalBenefit = tilesBlue - tilesRed;
					} else {
						finalBenefit = tilesRed - tilesBlue;
					}
					int linearLocation = j * board.getSize() + i; //the current location on a linear scale
					research[linearLocation][0] = finalBenefit;
					research[linearLocation][1] = i; //x
					research[linearLocation][2] = j; //y
					board.setBoard(calculate); // resets the board
					board.resetKillCount();
				} else {
					int linearLocation = j * board.getSize() + i; //the current location on a linear scale
					research[linearLocation][0] = -100000;
				}
			}
		}
		//finds the best findings
		int currentMax = -10000;
		int[] retval = new int[3];
		for(int i = 0; i < board.getSize() * board.getSize(); i ++) {
			if(research[i][0] > currentMax) {
				currentMax = research[i][0];
				System.out.println(currentMax);
				retval[1] = research[i][1];
				retval[2] = research[i][2];
			}
		}
		return retval;
	}
	public int[] findRevive() {
		int[][] research = new int[400][7]; //contains the findings of each iteration of the loop
		for(int i = 0; i < board.getSize(); i ++) {
			for(int j = 0; j < board.getSize(); j ++) {
				//Checks to see if move is logical
				int[][] calculate = board.get().clone();
				if(board.getBoard(i, j) == 0) {
					if(board.getBoard(i, j) == 0) {
					board.revive(i, j, board.getplayer(), true, false);
					//calculates many moves ahead
					for(int k = 0; k < 10; k ++) {
						board.cycleLife();
					}
					//reads the final yield of the move
					int tilesBlue = board.getTiles(1);
					int tilesRed = board.getTiles(2);
					int finalBenefit = 0;
					if(board.getplayer() == 1) {
						finalBenefit = tilesBlue - tilesRed;
					} else {
						finalBenefit = tilesRed - tilesBlue;
					}
					int linearLocation = j * board.getSize() + i; //the current location on a linear scale
					research[linearLocation][0] = finalBenefit;
					research[linearLocation][1] = i; //x
					research[linearLocation][2] = j; //y
					board.setBoard(calculate); // resets the board
					board.resetKillCount();
					}
				} else {
					int linearLocation = j * board.getSize() + i; //the current location on a linear scale
					research[linearLocation][0] = -100000;
				}
			}
		}
		int[] retval = new int[7];
		//finds the best findings
		int currentMax = -10000;
		for(int i = 0; i < 400; i ++) {
			if(research[i][0] > currentMax) {
				currentMax = research[i][0];
				retval[1] = research[i][1];
				retval[2] = research[i][2];
				int[] val = this.findKill(true, retval[1], retval[2], -10, -10);
				int[] val2 = this.findKill(true, retval[1], retval[2], val[1], val[2]);
				retval[3] = val[1]; //x
				retval[4] = val[2]; //y
				retval[5] = val2[1]; //x
				retval[6] = val2[2]; //y
			}
		}
		return retval;
	}
}
