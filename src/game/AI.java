package game;

public class AI {
	private GOLBoard board;
	public void setBoard(GOLBoard board) {
		this.board = board;
	}
	public void makeMove() {
		boolean b = false;
		System.out.println("RESET");
		for(int i = 0; i < board.getSize(); i ++) {
			if(b) {break;}
			for(int j = 0; j < board.getSize(); j++) {
				if(board.getBoard(j, i) != 0) {
					if(board.makeMove(i, j, 1, false)) {
						System.out.println("DABADABA");
						board.finish();
						b = true;
						break;
					}
				}
			}
		}
	}
}
