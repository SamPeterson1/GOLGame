package game;

public class PatternFilter {
	int[][] filter = null;
	public PatternFilter(int[][] filter) {
		this.filter = filter;
	}
	public int[] checkForPattern(int size, int[][] board) {
		int retval[] = {0,0,0};
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
					for(int i = -2; i < 3; i ++) {
						for(int j = -2; j < 3; j ++) {
							int x = i + a;
							int y = j + b;
							if(x >= 0 & x < size & y >= 0 & y < size) {
								if(board[y][x] == filter[j + 2][i + 2]) {
									retval[0] ++;
								}
							} else {
								retval[0] ++;
							}
						}
					}
					if(retval[0] == 25) {
						b2 = true;
						break;
					}
				}
			}
			if(retval[0] == 25) {
				retval[0] = 1;
				break;
			} else {
				System.out.println("HI");
				filter = rotate90(filter);
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
}
