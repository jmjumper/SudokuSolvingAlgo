import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {

    final private int WIDTH = 520, HEIGHT = 700, SODOKU_SIZE = 700;
    private JFrame jFrame;
    private JPanel subPanel;
    private BoardPanel sudokuPanel;
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem newSudokuMenuItem;
    private JButton calculateButton, finish;
    private Solve solve;
    private int[][] newSudokuInput, grid;
    private JTextField[][] numerousTextFields;

    public GUI(int[][] gGrid) {
        jFrame = new JFrame("Soduku - AI");
        grid = gGrid;
        solve = new Solve(grid);
        calculateButton = new JButton("Click to solve!");
        subPanel = new JPanel();
        sudokuPanel = new BoardPanel(gGrid);
        jMenuBar = new JMenuBar();
        gui_initialisation();
    }

    private void gui_initialisation() {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);

        jMenu = new JMenu("Neu...");
        newSudokuMenuItem = new JMenuItem("Neues Sudoku...");
        jMenu.add(newSudokuMenuItem);
        jMenuBar.add(jMenu);

        calculateButton.addActionListener(this);
        newSudokuMenuItem.addActionListener(this);

        subPanel.setLayout(new BorderLayout());
        subPanel.add(sudokuPanel, BorderLayout.CENTER);
        subPanel.add(calculateButton, BorderLayout.SOUTH);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        jFrame.setJMenuBar(jMenuBar);
        jFrame.add(subPanel);
        jFrame.setVisible(true);
    }

    private void newSudokuWindow() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame popUpFrame = new JFrame("New Sudoku");
                popUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(10, 9));

                numerousTextFields = new JTextField[9][9];

                finish = new JButton("Erstellen");
                finish.addActionListener(GUI.this);

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        numerousTextFields[i][j] = new JTextField();
                        panel.add(numerousTextFields[i][j]);
                    }
                }
                panel.add(finish);
                popUpFrame.getContentPane().add(BorderLayout.CENTER, panel);
                popUpFrame.pack();

                popUpFrame.setPreferredSize(new Dimension(100, 200));
                popUpFrame.setResizable(false);
                popUpFrame.setLocationRelativeTo(null);
                popUpFrame.setVisible(true);
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            solve.setGrid(grid);
            solve.startSolving();
            int[][] solved_grid = solve.getGrid();
            solve.printGrid(solved_grid);
            sudokuPanel.setGrid(grid);
            sudokuPanel.repaint();
            subPanel.add(sudokuPanel, BorderLayout.CENTER);
            subPanel.repaint();
        } else if (e.getSource() == newSudokuMenuItem) {
            newSudokuWindow();
        } else if (e.getSource() == finish) {
            newSudokuInput = new int[9][9];
            try {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        String textFromTextfield = numerousTextFields[i][j].getText();
                        int inputToInt = Integer.parseInt(textFromTextfield);
                        newSudokuInput[i][j] = inputToInt;
                    }
                }
            } catch (Exception error) {
                System.out.println("Fehler: Möglicherweise fehlen Eingaben. Freie Felder werden mit '0' gekenntzeichnet.");
            }
            grid = newSudokuInput;

            sudokuPanel.setGrid(grid);
            sudokuPanel.repaint();

            subPanel.revalidate();
            subPanel.repaint();
            jFrame.revalidate();
            jFrame.repaint();
        }

    }
}
