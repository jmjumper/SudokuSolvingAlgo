import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    static final int cols = 9, rows = 9, borderX = 20, borderY = 50, cellSize = 50;
    int[][] grid;

    public BoardPanel(int[][] gGrid) {
        grid = gGrid;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < rows + 1; i++) {
            if (i % 3 == 0) g2.setStroke(new BasicStroke(2));
            else g2.setStroke(new BasicStroke(1));
            g2.drawLine(borderX, borderY + i * cellSize, borderX + cols * cellSize, borderY + i * cellSize);
        }

        for (int i = 0; i < cols + 1; i++) {
            if (i % 3 == 0) g2.setStroke(new BasicStroke(2));
            else g2.setStroke(new BasicStroke(1));
            g2.drawLine(borderX + i * cellSize, borderY, borderX + i * cellSize, borderY + rows * cellSize);
        }

        g2.setFont(new Font("Arial", Font.BOLD, 12));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String colNum = Integer.toString(grid[i][j]);
                if (grid[i][j] == 0) colNum = " ";
                g2.drawString(colNum, borderX + j * cellSize + cellSize / 2, borderY + i * cellSize + cellSize / 2);
            }


        }

    }

}
