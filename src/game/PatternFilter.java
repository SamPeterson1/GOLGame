package game;

public class PatternFilter {
	int[][] filter = null;
	private int[] dissapateLocation = new int[3];
	private int[][] dissapate = null;
	private int mode = 0;
	private GOLBoard board;
	public PatternFilter(int[][] filter, int dissapateX, int dissapateY, int mode, GOLBoard board) {
		this.filter = filter;
		dissapate = new int[filter.length][filter.length];
		dissapate[dissapateY][dissapateX] = 1;
		this.mode = mode;
		this.board = board;
	}
	public int[] checkForPattern(int size, int[][] board) {
		int retval[] = {0,0,0};
		int bound1 = -(filter.length/2);
		int bound2 = (filter.length/2) + 1;
		if(filter.length % 2 == 0) {
			bound2--;
		}
		boolean b2 = false;
		for(int c = 0; c < 4; c ++) {
			for(int a = 0; a < size; a ++) {
				if(b2) {
					break;
				}
				for(int b = 0; b < size; b ++) {
					if(b2) {
						break;
					}
					retval[0] = 0;
					for(int i = bound1; i < bound2; i ++) {
						for(int j = bound1; j < bound2; j ++) {
							int x = i + a;
							int y = j + b;
							if(x >= 0 & x < size & y >= 0 & y < size) {
								if(board[y][x] == filter[j - bound1][i - bound1] & this.board.getplayer() == 2|| filter[j - bound1][i - bound1] == 3 || board[y][x] == filter[j - bound1][i - bound1] * 2 & this.board.getplayer() == 1) {
									retval[0] ++;
									System.out.println("LOLOLOLOLOLOLOLOOLOOLOL" + retval[0]);
								}
							} else {
								retval[0] ++;
								System.out.println("LOLOLOLOLOLOLOLOOLOOLOL" + retval[0]);
							}
							if(retval[0] == ((bound2 - bound1) * (bound2 - bound1))) {
								b2 = true;
								retval[0] = 1;
								retval[1] = a;
								retval[2] = b;
								return retval;
							}
						}
					}
				}
			}
			if(retval[0] == ((bound2 - bound1) * (bound2 - bound1))) {
				retval[0] = 1;
				break;
			} else {
				filter = rotate90(filter);
				dissapate = rotate90(dissapate);
				retval[0] = 2;
			}
		}
		return retval;
	}
	public int[][] rotate90(int[][] matrix){
		final int M = matrix.length;
	    final int N = matrix.length;
	    int[][] ret = new int[N][M];
	    for (int r = 0; r < M; r++) {
	        for (int c = 0; c < N; c++) {
	            ret[c][M-1-r] = matrix[r][c];
	        }
	    }
	    return ret;
	}
	public int getMode() {
		return this.mode;
	}
	public int[] getCurrentFilterDissapationXY(int[] patternLocation) {
		System.out.println(patternLocation[0] + "LOP" + patternLocation[1]);
		int[] retval = {0,0};
		int bound1 = -(filter.length/2);
		int runtimes = 0;
		do{
			for(int i = 0; i < filter.length; i ++) {
				for(int j = 0; j < filter.length; j ++) {
					if(dissapate[j][i] == 1) {
						retval[0] = i;
						retval[1] = j;
					}
				}
			}
			if(runtimes >= 1) {
				System.out.println("HIII" + retval[0] + " " + retval[1]);
				dissapate = this.rotate90(dissapate);
			}
			runtimes ++;
			if(runtimes > 5) {
				break;
			}
		} while(!(retval[0] + patternLocation[0] + bound1+ patternLocation[0] + bound1 >= 0 & retval[0] + patternLocation[0] + bound1 < board.getSize() & retval[1] + patternLocation[1] + bound1>= 0 & retval[1] + patternLocation[1] + bound1< board.getSize()));
		retval[0] += patternLocation[0] + bound1;
		retval[1] += patternLocation[1] + bound1;
		return retval;
	}
}
