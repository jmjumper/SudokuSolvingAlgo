
public class Solve {

    private int[][] grid;

    public Solve(int[][] gGrid) {
        grid = gGrid;
    }


    private boolean isMoveValid(int[][] grid, int row, int col, int number) {

        // Col- and row-check:
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == number) {
                //        System.out.println("COLUMN FOUND INVALID NUMBER");
                return false;
            }
            if (grid[i][col] == number) {
                //        System.out.println("ROW FOUND INVALID NUMBER");
                return false;
            }
        }

        // Cell-check:
        int cell_row = row - row % 3; // One cell exists of three times three numbers. Here, we get the corner of the cell.
        int cell_col = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (grid[cell_row + i][cell_col + j] == number) {
                    //             System.out.println("CELL FOUND INVALID NUMBER");
                    return false;
                }
            }
        }
        return true;
    }

    public void printGrid(int[][] gGrid) {
        for (int[] i : gGrid) {
            for (int j : i) {
                System.out.print(j + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean solve_not_working(int[][] gGrid, int row, int col) {
        grid = gGrid;
        if (col == 9) {
            if (row == 8) return true;
            row++;
            col = 0;
        }
        if (grid[row][col] > 0) solve_not_working(grid, row, col + 1);

        for (int number = 1; number < 9; number++) {
            if (isMoveValid(grid, row, col, number)) {
                grid[row][col] = number;
                if (solve_not_working(grid, row, col + 1)) return true;
            }
        }

        grid[row][col] = 0;

        return false;
    }

    private boolean solve_working(int[][] grid, int row, int col) {
        if (col == 9) {
            if (row == 8) {
                return true;
            }
            row++;
            col = 0;
        }

        if (grid[row][col] > 0) return solve_working(grid, row, col + 1);

        //System.out.println("In die Number-Schleife");
        for (int number = 1; number < 10; number++) {
            //    System.out.println(number);
            if (isMoveValid(grid, row, col, number)) {
                //        System.out.println("Valid move");
                grid[row][col] = number;
                if (solve_working(grid, row, col + 1)) {
                    return true;
                }
            }
            grid[row][col] = 0;
        }
        // System.out.println("Es wird abgebrochen");
        return false;
    }


    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void startSolving() {
        if (solve_working(grid, 0, 0)) printGrid(grid);
        else {
            System.out.println("FEHLER BEIM BERECHNEN");
            printGrid(grid);
        }
    }

}
