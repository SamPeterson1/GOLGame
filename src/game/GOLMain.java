package game;

import javax.swing.JOptionPane;

public class GOLMain {
	public static void main(String args[]) {
		GOLEventQueue queue = new GOLEventQueue();
		GOLEvent event;
		GOLFrame frame = new GOLFrame();
		GOLBoard board = new GOLBoard();
		GOLCanvas canvas = new GOLCanvas();
		Tutorial tutorial = new Tutorial();
		AI ai = new AI();
		ai.setBoard(board);
		boolean HasFinalWinner = false;
		frame.addCanvas(canvas);
		canvas.addEventQueue(queue);
		canvas.setBoard(board);
		canvas.setSplash(true);
		board.setWinPoint(2);
		board.setWinMode(board.FIRST_TO_SCORE);
		canvas.setTutorial(tutorial);
		ai.init();
		while(true) {
			if(!canvas.inSplash() & !canvas.inSelection()) {
				Object[] options = {"ok"};
				/*
				if(board.isWinner(1) & !board.isDraw() & !tutorial.isActive()) {
					System.out.println("WINNER");
					board.incrementWin(1);
					if(board.shouldExit() == 1){
						canvas.setSplash(true);
						HasFinalWinner = true;
						JOptionPane.showOptionDialog(null, "BLUE WINS!!!", "WINNER", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
						board.reset();
						board.generateBoard(System.currentTimeMillis());
					}
					if(!HasFinalWinner) {
						JOptionPane.showOptionDialog(null, "Blue Wins This Round!", "WINNER", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
						board.reset();
						board.generateBoard(System.currentTimeMillis());
					}
				}
				if(board.isWinner(2) & !board.isDraw() & !tutorial.isActive()) {
					board.incrementWin(2);
					System.out.println("WINNER");
					if(board.shouldExit() == 2){
						canvas.setSplash(true);
						HasFinalWinner = true;
						JOptionPane.showOptionDialog(null, "RED WINS!!!", "WINNER", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
						board.reset();
						board.generateBoard(System.currentTimeMillis());
					}
					if(!HasFinalWinner) {
						JOptionPane.showOptionDialog(null, "Red Wins This Round!", "WINNER", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
						board.reset();
						board.generateBoard(System.currentTimeMillis());
					}
				} 
				if(board.isDraw() & !tutorial.isActive()) {
					JOptionPane.showOptionDialog(null, "DRAW", "DRAW", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					canvas.setSplash(true);
				}
				*/
				if(queue.isEventToProcess()) {
					event = queue.getEvent();
					if(event.getType() == GOLEvent.EVENT_KEY_PRESS & event.getKeyChar() == 'f') {
						board.mirrorBoard();
					} else if(event.getType() == GOLEvent.EVENT_MOUSE_BUTTON_PRESS & event.isMouseLeftButton()) {
						if(board.getBoard(event.getMouseX()/(100/(board.getSize()/10)), event.getMouseY()/(100/(board.getSize()/10))) == 1 & event.getMouseX()/(100/(board.getSize()/10)) < board.getSize() & event.getMouseY()/(100/(board.getSize()/10)) < board.getSize() || board.getBoard(event.getMouseX()/(100/(board.getSize()/10)),event.getMouseY()/(100/(board.getSize()/10))) == 2 & event.getMouseX()/ (100/(board.getSize()/10)) < board.getSize() & event.getMouseY()/(100/(board.getSize()/10)) < board.getSize()) {
							board.kill(event.getMouseX()/(100/(board.getSize()/10)), event.getMouseY()/(100/(board.getSize()/10)), true, false,false);
							board.resetKillCount();
						}else if(event.getMouseX() >= 1075 & event.getMouseX() <= 1402 & event.getMouseY() >= 600 & event.getMouseY() <= 754) {
							if(board.endTurn(true)) {
								board.setCycle(true);
							}
						}else if(event.getMouseX()/ (100/(board.getSize()/10)) < board.getSize() & event.getMouseY()/(100/(board.getSize()/10)) < board.getSize()){
							board.revive(event.getMouseX()/(100/(board.getSize()/10)), event.getMouseY()/(100/(board.getSize()/10)), board.getplayer(), true, false);
							board.resetKillCount();
							board.returnDebt();
							board.returnDebt();
						}else if(event.getMouseX() >= 1050 & event.getMouseX() <= 1441 & event.getMouseY() >= 800 & event.getMouseY() <= 929) {
							canvas.setSplash(true);
						} else if(event.getMouseX() >= 1100 & event.getMouseX() <= 1166 & event.getMouseY() >= 500 & event.getMouseY() <= 562 & board.isActive(1)) {
							board.undo();
						} else if(event.getMouseX() >= 1350 & event.getMouseX() <= 1416 & event.getMouseY() >= 500 & event.getMouseY() <= 562 & board.isActive(2)) {
							board.undo();
						}
					} else if(event.getType() == GOLEvent.EVENT_KEY_PRESS & event.getKeyChar() == 'z') {
						board.undo();
					} else if(event.getType() == GOLEvent.EVENT_KEY_PRESS & event.getKeyChar() == 'a') {
						ai.makeMove();
					}
				} 
				board.uptateDebt();
				board.predictLife();
				canvas.draw();
			} else if(!canvas.inSelection()){
				if(queue.isEventToProcess()) {
					event = queue.getEvent();
					if(event.getType() == GOLEvent.EVENT_MOUSE_BUTTON_PRESS & event.isMouseLeftButton()) {
						if(event.getMouseX() >= 700 & event.getMouseX() <= 850 & event.getMouseY() >= 300 & event.getMouseY() <= 450) {
							canvas.setSplash(false);
							canvas.setSelection(true);
							tutorial.deActivate();
						} else if(event.getMouseX() >= 630 & event.getMouseX() <= 933 & event.getMouseY() >= 500 & event.getMouseY() <= 589) {
							tutorial.deActivate();
							tutorial.activate();
							board.generateBoard(System.currentTimeMillis());
							canvas.setSplash(false);
						}
					}
				}
				canvas.draw();
			} else {
				canvas.increaseVal();
				canvas.decreaseVal();
				if(queue.isEventToProcess()) {
					event = queue.getEvent();
					if(event.getType() == GOLEvent.EVENT_MOUSE_BUTTON_PRESS & event.isMouseLeftButton()) {
						if(event.getMouseX() >= 300 & event.getMouseX() <= 600 & event.getMouseY() >= 300 & event.getMouseY() <= 400) {
							if(board.getMode() < 4) {
							board.setWinMode(board.getMode() + 1);
							} else {
								board.setWinMode(1);
							}
						} else if(event.getMouseX() >= 1050 & event.getMouseX() <= 1177 & board.getMode() == 4) {
							if(event.getMouseY() >= 230 & event.getMouseY() <= 342) {
								canvas.increaseVal();
							} else if(event.getMouseY() >= 343 & event.getMouseY() <= 455) {
								canvas.decreaseVal();
							}
						} else if(event.getMouseX() >= 600 & event.getMouseX() <= 950 & event.getMouseY() >= 800 & event.getMouseY() <= 906) {
							canvas.setSplash(false);
							canvas.setSelection(false);
							board.generateBoard(System.currentTimeMillis());
						} else if(event.getMouseX() >= 800 & event.getMouseX() <= 1000 & event.getMouseY() >= 500 & event.getMouseY() <= 600) {
							if(board.getSize() == 10) {
								board.setSize(20);
							} else {
								board.setSize(10);
							}
						}
					}
				}
				canvas.draw();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
