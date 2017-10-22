package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class GOLCanvas extends Canvas{
	GOLBoard board = new GOLBoard();
	Tutorial tutorial = new Tutorial();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image exit = new ImageIcon(getClass().getResource("/assets/Exit.png")).getImage();
	Image splash = new ImageIcon(getClass().getResource("/assets/SplashScreen.png")).getImage();
	Image play = new ImageIcon(getClass().getResource("/assets/Play.png")).getImage();
	Image upDown = new ImageIcon(getClass().getResource("/assets/Up-DownArrow.png")).getImage();
	Image generate = new ImageIcon(getClass().getResource("/assets/Generate.png")).getImage();
	Image tutorial1 = new ImageIcon(getClass().getResource("/assets/Tutorial.png")).getImage();
	private boolean inSplashScreen = false;
	private boolean reset = false;
	private boolean selection = false;
	private int val = 1;
	public GOLCanvas() {
		this.setBackground(Color.GRAY);
	}
	public void draw() {
		BufferedImage image;
		Graphics g;
		image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		g = image.getGraphics();	
		Font font = new Font("Helvectica", Font.BOLD, 50);
		g.setFont(font);
		if(!inSplashScreen & !selection) {
			if(reset) {
				g.setColor(Color.GRAY);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				reset = false;
			}
			for(int i = 0; i < board.getSize(); i ++) {
				for(int j = 0; j < board.getSize(); j ++) {
					switch(board.getBoard(i, j)) {
						case 0:
							g.setColor(Color.BLACK);
							if(board.getSize() == 10) {
								g.fillRect(i * 100 + 5, j * 100 + 5, 90, 90);
							} else {
								g.fillRect(i * 50 + 5, j * 50 + 5, 45, 45);
							}
							break;
						case 1:
							g.setColor(Color.BLUE);
							if(board.getSize() == 10) {
								g.fillRect(i * 100 + 5, j * 100 + 5, 90, 90);
							} else {
								g.fillRect(i * 50 + 5, j * 50 + 5, 45, 45);
							}
							break;
						case 2:
							g.setColor(Color.RED);
							if(board.getSize() == 10) {
								g.fillRect(i * 100 + 5, j * 100 + 5, 90, 90);
							} else {
								g.fillRect(i * 50 + 5, j * 50 + 5, 45, 45);
							}
							break;
					}
					switch(board.getPrediction(i, j)) {
					case 0:
						g.setColor(Color.BLACK);
						if(board.getSize() == 10) {
							g.fillRect(i * 100 + 40, j * 100 + 40, 20, 20);
						} else {
							g.fillRect(i * 50 + 20, j * 50 + 20, 10, 10);
						}
						break;
					case 1:
						g.setColor(Color.BLUE);
						if(board.getSize() == 10) {
							g.fillRect(i * 100 + 40, j * 100 + 40, 20, 20);
						} else {
							g.fillRect(i * 50 + 20, j * 50 + 20, 10, 10);
						}
						break;
					case 2:
						g.setColor(Color.RED);
						if(board.getSize() == 10) {
							g.fillRect(i * 100 + 40, j * 100 + 40, 20, 20);
						} else {
							g.fillRect(i * 50 + 20, j * 50 + 20, 10, 10);
						}
						break;
				}
					/*
					Integer[] q = board.getNeighbor(i, j, false);
					Font fon1 = new Font("Helvectica", Font.BOLD, 50);
					g.setFont(fon1);
					g.setColor(Color.green);
					g.drawString(q[0].toString(), i * 100 + 20, j * 100 + 40);
					g.drawString(q[1].toString(), i * 100 + 50, j * 100 + 40);
					g.drawString(q[3].toString(), i * 100 + 30, j * 100 + 60);
					*/
				}
			}
			if(board.getplayer() == 1) {
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.GRAY);
			}
			g.fillRect(1150, 200, 500, 100);
			if(board.getplayer() == 2) {
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.GRAY);
			}
			g.fillRect(1150, 400, 500, 100);
			g.setColor(Color.BLACK);
			g.drawString("SCOREBOARD", 1070, 100);
			g.setColor(Color.BLUE);
			g.fillRect(1050, 200, 75, 75);
			g.setColor(Color.RED);
			g.fillRect(1050, 400, 75, 75);
			g.setColor(Color.BLACK);
			g.drawString("x" + "   " + board.getTiles(1),1150, 250);
			g.drawString("x" + "   " + board.getTiles(2), 1150, 450);
			g.drawImage(tutorial.getImage(), 1100, 600, null);
			g.drawImage(exit, 1050, 800, null);
			g.drawImage(board.getImage(1), 1100, 500, null);
			g.drawImage(board.getImage(2), 1350, 500, null);
			Font small = new Font("Helvectica", Font.PLAIN, 22);
			g.setColor(Color.GRAY);
			g.fillRect(1000, 920, 800, 800);
			g.setColor(Color.BLACK);
			g.setFont(small);
			if(tutorial.isActive()) {
				g.drawString(tutorial.runTutorial(), 1000, 960);
			}
			g.setFont(font);
		} else if(!selection){
			if(reset) {
				g.setColor(Color.GRAY);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				reset = false;
			}
			g.drawImage(splash, 0, 0, 1500, 1020, null);
			g.drawImage(play, 700, 300, null);
			g.drawImage(tutorial1, 630, 500, null);
		} else {
			if(reset) {
				g.setColor(Color.GRAY);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				reset = false;
			}
			g.fillRect(300,300,400,100);
			g.setColor(Color.BLACK);
			g.setFont(font);
			switch(board.getMode()) {
				case 1:
					g.drawString("Single Win", 300, 350);
					break;
				case 2:
					g.drawString("Best of Three", 300, 350);
					break;
				case 3:
					g.drawString("Best of Five", 300, 350);
					break;
				case 4:
					g.drawString("First to score", 300, 350);
					break;
			}
			if(board.getMode() == 4) {
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.LIGHT_GRAY);
			}
			g.fillRect(800, 300, 200, 100);
			g.drawImage(upDown, 1050, 230, null);
			Integer i = val;
			g.setColor(Color.BLACK);
			g.drawString(i.toString(), 800, 360);
			g.drawImage(generate, 600, 800, null);
			g.setColor(Color.WHITE);
			g.fillRect(800, 500, 200, 100);
			String st = board.getSize() + " x " + board.getSize();
			g.setColor(Color.BLACK);
			g.drawString(st, 800, 550);
			
		}
		g.dispose();
		g = this.getGraphics();
		g.drawImage(image, 0, 0, null); 
	}
	public void addEventQueue(GOLEventQueue queue)  {

		this.addKeyListener(queue);
		this.addMouseListener(queue);
		this.addMouseMotionListener(queue);

		return;
	}
	public boolean inSplash() {
		return this.inSplashScreen;
	}
	public boolean inSelection() {
		return this.selection;
	}
	public void setSelection(boolean selection) {
		if(this.selection != selection) {
			reset = true;
			board.reset();
		}
		this.selection = selection;
	}
	public void setSplash(boolean inSplashScreen) {
		if(this.inSplashScreen != inSplashScreen) {
			reset = true;
			board.reset();
		}
		this.inSplashScreen = inSplashScreen;
	}
	public void setBoard(GOLBoard board) {
		this.board = board;
	}
	public void increaseVal() {
		this.val ++;
		board.setWinPoint(val);
	}
	public void decreaseVal() {
		if(this.val > 1) {
			this.val--;
			board.setWinPoint(val);
		}
	}
	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
		tutorial.setBoard(board);
	}
	/*
	╭━━━━╮               
	╰┃ ┣▇━▇                
	 ┃ ┃  ╰━▅╮ 
	 ╰┳╯ ╰━━┳╯        
	  ╰╮ ┳━━╯          
	 ▕▔▋ ╰╮╭━╮   
	╱▔╲▋╰━┻┻╮╲╱▔▔▔╲ 
	▏  ▔▔▔▔▔▔▔  O O┃ 
	╲╱▔╲▂▂▂▂╱▔╲▂▂▂╱
	 ▏╳▕▇▇▕ ▏╳▕▇▇▕
	 ╲▂╱╲▂╱ ╲▂╱╲﻿
	*/
}
