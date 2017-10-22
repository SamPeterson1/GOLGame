package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tutorial {
	private GOLBoard board;
	private int tutorialStage = 1;
	private boolean isActive = false;
	private Image finish = new ImageIcon(getClass().getResource("/assets/Finish.png")).getImage();
	private Image next = new ImageIcon(getClass().getResource("/assets/Next.png")).getImage();
	private Image currentImage = finish;
	private boolean b = false;
	private int[][] second = {
			{0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};
	private int[][] request = {
			{1,1,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{2,2,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
	public String runTutorial() {
		String retVal = null;
		board.disableUndoRedo();
		if(!b) {
			board.setBoard(request);
			b = true;
		}
		switch(tutorialStage) {
			case 1:
				System.out.println("HSBREYWHETY");
				board.disableEnable("revive", false);
				if(board.getTiles(1) + board.getTiles(2) == 13) {
					retVal = "Click a red/blue cell to kill it";
				} else {
					tutorialStage ++;
					retVal = "Click a red/blue cell to kill it";
					board.setCycle(false);
				}
				break;
			case 2:
				if(!board.hasCycledLife()) {
					retVal = "Click finish to end your turn";
				} else {
					board.setCycle(false);
					tutorialStage ++;
					retVal = "Click finish to end your turn";
				}
				break;
			case 3:
				board.disableEnable("kill", false);
				board.disableEnable("revive", true);
				if(board.getTiles(1) + board.getTiles(2) != 13) {
					retVal = "Click on a black cell to revive it";
				} else {
					tutorialStage ++;
					retVal = "Click on a black cell to revive it";
				}
				break;
			case 4:
				board.disableEnable("kill", true);
				board.disableEnable("revive", false);
				if(board.getDebt() != 0) {
					retVal = "Kill " + board.getDebt() + " red cells to revive this cell";
				} else {
					tutorialStage ++;
					retVal = "Kill 1 red cells to revive this cell"; 
				}
				break;
			case 5:
				this.currentImage = this.next;
				board.setAdmin(true);
				board.disableEnable("kill", true);
				board.disableEnable("revive", true);
				if(!board.hasCycledLife()) {
					retVal = "Squares are the most stable shapes";
				} else {
					board.setCycle(false);
					this.tutorialStage ++;
					retVal = "Squares are the most stable shapes";
				}
				break;
			case 6:
				if(!board.hasCycledLife()) {
					retVal = "Cells are born if they have 3 neighbors";
				} else {
					board.setCycle(false);
					this.tutorialStage ++;
					retVal = "Cells are born if they have 3 neighbors";
				}
				break;
			case 7:
				if(!board.hasCycledLife()) {
					retVal = "Cells die if they have more than 3 neighbors";
				} else {
					board.setCycle(false);
					this.tutorialStage ++;
					retVal = "Cells die if they have more than 3 neighbors";
				}
				break;
			case 8:
				if(!board.hasCycledLife()) {
					retVal = "Cells die if they have less than 2 neighbors";
				} else {
					board.setCycle(false);
					this.tutorialStage ++;
					retVal = "Cells die if they have less than 2 neighbors";
					b = false;
				}
				break;
			case 9:
				if(!b) {
					b = true;
					board.setBoard(second);
				}
				this.currentImage = this.finish;
				board.setAdmin(false);
				board.setTurn(1);
				int k = 0;
				for(int y = 7; y < 9; y ++) {
					for(int x = 6; x < 8; x ++) {
						if(board.getBoard(x, y) != 0) {
							k ++;
						}
					}
				}
				System.out.println(k);
				if(k == 4) {
					retVal = "Kill this red square(use overpopulation)";
				} else if(k == 2){
					this.tutorialStage ++;
					retVal = "Kill this red square(use overpopulation)";
				} else {
					retVal = "Kill this red square(use overpopulation)";
					board.setBoard(second);
				}
				break;
			default:
				retVal = "Tutorial over: Click exit";
		}
		return retVal;
	}
	public void setBoard(GOLBoard board) {
		this.board = board;
	}
	public boolean isActive() {
		return this.isActive;
	}
	public Image getImage() {
		return this.currentImage;
	}
	public void activate() {
		b = false;
		this.isActive = true;
	}
	public void deActivate() {
		this.isActive = false;
		this.tutorialStage = 1;
	}
}
