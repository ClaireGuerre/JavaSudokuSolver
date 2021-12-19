package co.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuSolver {

    private static final char[] allValues = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};


    private static Node<Sudoku> getSolutionsTree(Node<Sudoku> current) {
        Sudoku sudoku = current.value;
        int i = 0;
        while (i < sudoku.grid.length) {
            int j = 0;
            while (j < sudoku.grid[i].length) {
                if (sudoku.grid[i][j] == '.') {
                    Set<Character> possibleValues = new HashSet<>();
                    for (char c: allValues) {
                        char[] line = sudoku.getLine(i);
                        char[] column = sudoku.getColumn(j);
                        char[] flattenedSquare = sudoku.getFlattenedSquare(i, j);
                        boolean isDuplicated = ArrayUtils.contains(c, line) || ArrayUtils.contains(c, column) || ArrayUtils.contains(c, flattenedSquare);
                        if(!isDuplicated) {
                            possibleValues.add(c);
                        }
                    }
                    for (char c: possibleValues) {
                        Sudoku copy = sudoku.copy();
                        copy.grid[i][j] = c;
                        Node<Sudoku> child = new Node<>(copy);
                        current.addChild(getSolutionsTree(child));
                    }
                    return current;
                }
                j++;
            }
            i++;
        }

        return current;
    }



    public static Sudoku getSolution(char[][] grid) {
        Node<Sudoku> root = new Node<>(new Sudoku(grid));
        Node<Sudoku> solutionsTree = getSolutionsTree(root).getRoot();
        return solutionsTree.findValidChild(Sudoku::isValid);
    }



    public static void main(String[] args) {
       /* char[][] input = new char[][]
                {{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};*/

       /*  char[][] input = new char[][]
                 {{'.','.','9','7','4','8','.','.','.'},
                         {'7','.','.','.','.','.','.','.','.'},
                         {'.','2','.','1','.','9','.','.','.'},
                         {'.','.','7','.','.','.','2','4','.'},
                         {'.','6','4','.','1','.','5','9','.'},
                         {'.','9','8','.','.','.','3','.','.'},
                         {'.','.','.','8','.','3','.','2','.'},
                         {'.','.','.','.','.','.','.','.','6'},
                         {'.','.','.','2','7','5','9','.','.'}};*/

        /*char[][] input = new char[][]
                {{'.','.','9','7','4','8','.','.','.'},
                        {'7','.','.','6','.','2','.','.','.'},
                        {'.','2','.','1','.','9','.','.','.'},
                        {'.','.','7','9','8','6','2','4','1'},
                        {'2','6','4','3','1','7','5','9','8'},
                        {'1','9','8','5','2','4','3','6','7'},
                        {'.','.','.','8','6','3','.','2','.'},
                        {'.','.','.','4','9','1','.','.','6'},
                        {'.','.','.','2','7','5','9','.','.'}};*/

        char[][] input = new char[][]{{'.','.','.','.','.','7','.','.','9'},
                {'.','4','.','.','8','1','2','.','.'},
                {'.','.','.','9','.','.','.','1','.'},
                {'.','.','5','3','.','.','.','7','2'},
                {'2','9','3','.','.','.','.','5','.'},
                {'.','.','.','.','.','5','3','.','.'},
                {'8','.','.','.','2','3','.','.','.'},
                {'7','.','.','.','5','.','.','4','.'},
                {'5','3','1','.','7','.','.','.','.'}};

        Sudoku output = getSolution(input);
        System.out.println(output.print());

    }
}
