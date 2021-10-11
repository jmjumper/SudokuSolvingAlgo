public class Controller {

    private int[][] grid;

    public Controller() {
        createGrid();
    }

    private void createGrid() {
        grid = new int[9][9];
        grid[0] = new int[]{0, 0, 5, 4, 8, 0, 0, 6, 7};
        grid[1] = new int[]{8, 3, 0, 0, 6, 9, 5, 0, 0};
        grid[2] = new int[]{7, 0, 6, 5, 0, 0, 4, 0, 8};
        grid[3] = new int[]{0, 7, 0, 9, 0, 6, 0, 5, 2};
        grid[4] = new int[]{6, 0, 3, 0, 7, 2, 1, 9, 0};
        grid[5] = new int[]{0, 2, 9, 1, 0, 0, 8, 0, 0};
        grid[6] = new int[]{3, 8, 0, 0, 5, 7, 0, 0, 9};
        grid[7] = new int[]{0, 0, 7, 3, 0, 4, 2, 8, 0};
        grid[8] = new int[]{5, 0, 2, 6, 0, 0, 7, 0, 3};
    }

    public int[][] getGrid() {
        return grid;
    }

    public static void main(String[] args) {
        Controller run = new Controller();
        GUI gui = new GUI(run.getGrid());
    }

}
