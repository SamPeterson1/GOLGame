package game;

import java.awt.Image;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;

public class GOLBoard {
	private Image undo = new ImageIcon(getClass().getResource("/assets/Undo.png")).getImage();
	private Image redo = new ImageIcon(getClass().getResource("/assets/Redo.png")).getImage();
	private Image grayUndo = new ImageIcon(getClass().getResource("/assets/GrayUndo.png")).getImage();
	private Image grayRedo = new ImageIcon(getClass().getResource("/assets/GrayRedo.png")).getImage();
	private Image currentImageUndo = grayUndo;
	private Image currentImageRedo = grayRedo;
	private int player = 1;
	private int debt = 0;
	private int killCount = 0;
	private int reviveCount = 0;
	private int[] debtCell = {0,0}; //x, y
	private int size = 10;
	public final int ONE_ROUND = 1;
	public final int BEST_OF_THREE = 2;
	public final int BEST_OF_FIVE = 3;
	public final int FIRST_TO_SCORE = 4;
	private boolean hasCycledLife;
	private boolean allowRevive = true;
	private boolean allowKilling = true;
	private boolean isAdmin = false;
	private int winPoint = 0;
	private int winMode = 0;
	private int winBlue = 0;
	private int winRed = 0 ;
	private int[] lastMove = {0,0,0,0}; //x y (kill/revive) color
	private int[][]board = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};
	private int[][] request = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};
	private int[][]predictions = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};
	public void setCycle(boolean hasCycledLife) {
		this.hasCycledLife = hasCycledLife;
	}
	public Image getImage(int i) {
		if(i == 1) {
			return this.currentImageUndo;
		} else {
			return this.currentImageRedo;
		}
	}
	public boolean hasCycledLife() {
		return this.hasCycledLife;
	}
	public void disableUndoRedo() {
		this.currentImageRedo = this.grayRedo;
		this.currentImageUndo = this.grayUndo;
	}
	public void incrementKillCount() {
		killCount ++;
	}
	public boolean isAdmin() {
		return this.isAdmin;
	}
	public int[][] get(){
		int[][] retVal = new int[20][20];
		for(int i = 0; i < 20; i ++) {
			retVal[i] = Arrays.copyOf(board[i], board[i].length);
		}
		return retVal;
	}
	public void resetKillCount() {
		this.killCount = 0;
		this.reviveCount = 0;
	}
	public void setTurn(int turn) {
		this.player = turn;
	}
	public void setBoard(int[][] board) {
			for(int i = 0; i < 20; i ++) {
				this.board[i] = Arrays.copyOf(board[i], board[i].length);
			}
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isWinner(int color) {
		int a = this.getTiles(1);
		int b = this.getTiles(2);
		if(b == 0 & color == 1 | a == 0 & color == 2) {
			return true;
		}
		return false;
	}
	public int getMode() {
		return this.winMode;
	}
	public void incrementWin(int color) {
		if(color == 1) {
			winBlue ++;
			return;
		} else {
			winRed ++;
		}
	}
	public void disableEnable(String str, boolean enable) {
		if(str.equals("revive")) {
			this.allowRevive = enable;
		} else {
			this.allowKilling = enable;
		}
	}
	public int getBoard(int x, int y) {
		if(x >= 0 & x < this.size & y >= 0 & y < this.size) {
			return board[y][x];
		}
		return 0;
	}
	public boolean finish(){
		int a = this.player;
		this.endTurn(false);
		return (a!= player);
	}
	public void setWinPoint(int winPoint) {
		this.winPoint = winPoint;
	}
	public int getTiles(int color) {
		int retVal = 0;
		for(int i = 0; i < this.size; i ++) {
			for(int j = 0; j < this.size; j ++) {
				if(board[i][j] == color) {
					retVal ++;
				}
			}
		}
		return retVal;
	}
	public void setWinMode(int mode) {
		this.winMode = mode;
	}
	public int shouldExit() {
		if(this.winMode == this.ONE_ROUND & winBlue >= 1) {
			return 1;
		} else if(this.winMode == this.ONE_ROUND & winRed >= 1) {
			return 2;
		} else if(this.winMode == this.BEST_OF_THREE & winBlue >= 2){
			return 1;
		} else if(this.winMode == this.BEST_OF_THREE & winRed >= 2) {
			return 2;
		} else if(this.winMode == this.BEST_OF_FIVE & winBlue >= 3) {
			return 1;
		} else if( this.winMode == this.BEST_OF_FIVE & winRed >= 3) {
			return 2;
		} else if(this.winMode == this.FIRST_TO_SCORE & winBlue >= winPoint) {
			System.out.println("WINNER CONfiRMED 1");
			return 1;
		} else if(this.winMode == this.FIRST_TO_SCORE & winRed >= winPoint) {
			System.out.println("WINNER CONfiRMED 2");
			return 2;
		} else {
			System.out.println(winRed + " " + winBlue);
		}
		return 0;
	}
	public int getPrediction(int x, int y) {
		return predictions[y][x];
	}
	public boolean endTurn(boolean isAdmin) {
		int[] ew = {0,0,0,0};
		if(debt == 0 & reviveCount == 1 | killCount == 1 & debt == 0 | isAdmin) {
			lastMove = ew.clone(); 
			this.cycleLife();
			if(player == 1) {
				player = 2;
			} else {
				player = 1;
			}
			this.currentImageUndo = this.grayUndo;
			this.currentImageRedo = this.grayRedo;
			debt = 0;
			killCount = 0;
			reviveCount = 0;
			int x[] = {0,0};
			debtCell = x;
			return true;
		}
		return false;
	}
	public void returnDebt() {
		if(debt != 0) {
			debt --;
		}
	}
	public int getDebt() {
		return this.debt;
	}
	public int getplayer() {
		return this.player;
	}
	public boolean kill(int x, int y, boolean isPlayer, boolean byAi, boolean isLegit) {
		if(byAi) {
			System.out.println("KILL" + x + " " + y);
		}
		//System.out.printf("allow killing: %b is player: %b debt: %d killCount: %d player: %d board: %d \n", allowKilling, isPlayer, debt, killCount, player, board[y][x]);
		if(this.allowKilling || !isPlayer) {
			if(debt == 0 & killCount == 0 || isLegit) {
				int color = this.getBoard(x, y);
				killCount ++;
				this.board[y][x] = 0;
				if(this.currentImageUndo == this.grayUndo & this.currentImageRedo == this.grayRedo) {
					this.currentImageUndo = this.undo;
				} else {
					this.currentImageUndo = this.undo;
					this.currentImageRedo = this.grayRedo;
				}
				lastMove[0] = x;
				lastMove[1] = y;
				lastMove[2] = 1;
				lastMove[3] = color;
				return true;
			}
			if(board[y][x] == player & killCount == 0 || isLegit) {
				//System.out.println("ACCEPTED");
				int color = this.getBoard(x, y);
				this.returnDebt();
				this.board[y][x] = 0;
				lastMove[0] = x;
				lastMove[1] = y;
				lastMove[2] = 1;
				lastMove[3] = color;
				return true;
			}
			if(!isPlayer) {
				this.board[y][x] = 0;
				return true;
			}
		}
		return false;
	}
	public boolean isDraw() {
		int a = this.getTiles(1);
		int b = this.getTiles(2);
		if(a == 0 & a == b) {
			return true;
		}
		return false;
	}
	public boolean revive(int x, int y, int color, boolean byPlayer, boolean legit) {
		if(this.allowRevive || !byPlayer || legit) {
			if(debt == 0 & board[y][x] == 0 & killCount == 0 & reviveCount == 0) {
				this.board[y][x] = color;
				this.debtCell[0] = x;
				this.debtCell[1] = y;
				this.debt += 2;
				if(this.currentImageUndo == this.grayUndo & this.currentImageRedo == this.grayRedo) {
					this.currentImageUndo = this.undo;
				} else {
					this.currentImageUndo = this.undo;
					this.currentImageRedo = this.grayRedo;
				}
				lastMove[0] = x;
				lastMove[1] = y;
				lastMove[2] = 2;
				lastMove[3] = color;
				reviveCount ++;
				return true;
			} else if(byPlayer == false) {
				this.board[y][x] = color;
				return true;
			}
		}
		return false;
	}
	public void setDebt() {
		this.debt = 2;
	}
	public void uptateDebt() {
		if(board[debtCell[1]][debtCell[0]] == 0) {
			debt = 0;
			reviveCount = 0;
		}
	}
	public int getSize() {
		return this.size;
	}
	public void undo() {
		if(currentImageUndo == undo) {
			currentImageUndo = grayUndo;
		} else if(currentImageUndo == grayUndo) {
			currentImageUndo = undo;
		}
		if(currentImageRedo == redo) {
			currentImageRedo = grayRedo;
		} else if(currentImageRedo == grayRedo) {
			currentImageRedo = redo;
		}
		if(lastMove[2] == 2) {
			this.kill(lastMove[0], lastMove[1], false, false, false);
			reviveCount = 0;
			lastMove[2] = 1;
		} else {
			this.revive(lastMove[0], lastMove[1], lastMove[3], false, false);
			killCount = 0;
			lastMove[2] = 2;
		}
	}
	public boolean isActive(int i) {
		if(i == 1) {
			if(currentImageUndo == undo) {
				return true;
			}
			return false;
		} else {
			if(currentImageRedo == redo) {
				return true;
			}
			return false;
		}
	}
	public void reset() {
		player = 1;
		debt = 0;
		killCount = 0;
		reviveCount = 0;
		debtCell[0] = 0;
		debtCell[1] = 0;
		for(int i = 0; i < this.size; i ++) {
			for(int j = 0; j < this.size; j ++) {
				board[i][j] = 0;
				predictions[i][j] = 0;
			}
		}
	}
	public void makeRevivePrediction(int x, int y, int color) {
		this.predictions[y][x] = color;
	}
	public void makeDeathPrediction(int x, int y) {
		this.predictions[y][x] = 0;
	}
	public Integer[] getNeighbor(int x, int y, boolean print) {
		Integer retval[] = {0,0,0,0};
		boolean center = false;
		for(int i = x - 1; i < x + 2; i ++) {
			for(int j = y - 1; j < y + 2; j ++) {
				center = false;
				if(i == x & j == y) {
					center = true;
				}
				if(i >= 0 & j >= 0 & center == false & i < this.size & j < this.size) {
					if(board[j][i] == 1) {
						retval[0] ++;
						retval[2] ++;
					}
					if(board[j][i] == 2) {
						retval[1] ++;
						retval[2] ++;
					}
				}
			}
		}
		if(retval[0] > retval[1]) {
			retval[3] = 1;
		}else if(retval[0] < retval[1]) {
			retval[3] = 2;
		}
		return retval.clone();
	}
	public void generateBoard(long seed) {
		Random rand = new Random(seed);
		int y = rand.nextInt((this.size - 1 - 0) + 1) + 0;
		int x = rand.nextInt((this.size/2 - 1 - 0) + 1) + 0;
		for(int i = 1; i < 3; i ++) {
			for(int j = 0; j < this.size * (this.size / 10) - 1 + (i - 1); j ++) {
				while(true) {
					if(board[y][x] == 0) {
						break;
					}
					y = rand.nextInt((this.size - 1 - 0) + 1) + 0;
					x = rand.nextInt((this.size/2 - 1 - 0) + 1) + 0;
				}
				board[y][x] = i;
			}
		}
		mirrorBoard();
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void mirrorBoard() {
		int[][] second = {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		};
		second = this.rotate180().clone();
		for(int i = 0; i < this.size; i ++) {
			for(int j = 0; j < this.size; j ++) {
				if(second[i][j] == 1) {
					second[i][j] = 2;
				} else if(second[i][j] == 2) {
					second[i][j] = 1;
				}
			}
		}
		for(int i = 0; i < this.size; i ++) {
			for(int j = this.size / 2; j < this.size; j ++) {
				board[i][j] = second[i][j];
			}
		}
	}
	public void predictLife() {
		for(int i = 0; i < this.size; i ++) {
			for(int j = 0; j < this.size; j ++) {
				predictions[i][j] = 0;
			}
		}
		int[][] neighbors = {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		};
		int[][] toBe = {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		};
		Integer[] q = {0,0,0,0};
		for(int i = 0; i < this.size; i ++) {
			for(int j = 0; j < this.size; j ++) {
				toBe[j][i] = this.getNeighbor(i, j, true)[3];
				q = this.getNeighbor(i, j, true).clone();
				neighbors[j][i] = q[2];
			}
		}
		for(int i = 0; i < this.size; i ++) {
			for(int j = 0; j < this.size; j ++) {
				this.makeRevivePrediction(i, j, board[j][i]);
				q = this.getNeighbor(i, j, true).clone();
				if(neighbors[j][i] > 3) {
					this.makeDeathPrediction(i, j);
				}
				else if(neighbors[j][i] < 2) {
					this.makeDeathPrediction(i, j);
				}
				else if(neighbors[j][i] == 3) {
					if(board[j][i] == 0) {
						this.makeRevivePrediction(i, j, toBe[j][i]);
					}
				}
			}
		}
	}
	/*REVIVE PREDICTED3 3
1
REVIVE PREDICTED4 3
REVIVE PREDICTED3 4
REVIVE PREDICTED4 4
2 2
*/
	public void cycleLife() {
		int[][] neighbors = {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		};
		int[][] toBe = {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		};
		Integer[] q = {0,0,0,0};
		for(int i = 0; i < this.size; i ++) {
			for(int j = 0; j < this.size; j ++) {
				toBe[j][i] = this.getNeighbor(i, j, true)[3];
				q = this.getNeighbor(i, j, true).clone();
				neighbors[j][i] = q[2];
			}
		}
		for(int i = 0; i < this.size; i ++) {
			for(int j = 0; j < this.size; j ++) {
				if(neighbors[j][i] > 3) {
					this.kill(i,j,false,false,false);
				}
				else if(neighbors[j][i] < 2) {
					this.kill(i, j, false,false,false);
				}
				else if(neighbors[j][i] == 3) {
					this.revive(i, j, toBe[j][i], false, false);
				}
			}
		}
	}
	public int[][] rotate180() {
		    final int M = this.size;
		    final int N = this.size;
		    int[][] ret = new int[N][M];
		    for (int r = 0; r < M; r++) {
		        for (int c = 0; c < N; c++) {
		            ret[c][M-1-r] = this.board[r][c];
		        }
		    }
		    final int O = ret.length;
		    final int P = ret[0].length;
		    int[][] ret2 = new int[N][M];
		    for (int r = 0; r < O; r++) {
		        for (int c = 0; c < P; c++) {
		            ret2[c][M-1-r] = ret[r][c];
		        }
		    }   

	    return ret2;
	}
}
