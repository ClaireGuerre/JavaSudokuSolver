package co.sudoku;

public class Sudoku {
    char[][] grid;

    public Sudoku(char[][] g) {
        this.grid = g;
    }

    public Sudoku copy() {
        char[][] copy = new char[grid.length][grid[0].length];
        for (int l = 0; l < grid.length; l++) {
            for (int col = 0; col < grid[l].length; col++) {
                copy[l][col] = grid[l][col];
            }
        }
        return new Sudoku(copy);
    }

    public char[] getLine(int index) {
        return grid[index];
    }

    public char[] getColumn(int index) {
        char[] column = new char[grid.length];
        for(int i = 0; i < column.length; i++) {
            column[i] = grid[i][index];
        }
        return column;
    }

    public char[] getFlattenedSquare(int line, int column) {
        int minX = line - line % 3;
        int minY = column - column % 3;
        int index = 0;
        char[] flattenedSquare = new char[9];
        for (int i = minX; i < minX + 3; i++) {
            for (int j = minY; j < minY + 3; j++) {
                if (index < 9) {
                    flattenedSquare[index] = grid[i][j];
                }
                index++;
            }
        }
        return flattenedSquare;
    }

    public Boolean isValid() {
        for (char[] line: grid) {
            for (char c: line) {
                if (c == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (char[] line: grid) {
            for (char e: line) {
                sb.append(e);
                sb.append(" ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public boolean verify() {
        for (char[] line : grid) {
            boolean isLineValid = ArrayUtils.hasDuplicate(line);
            if (!isLineValid) {
                return false;
            }
        }
        for (int i = 0; i < grid[0].length; i++) {
            char[] column = new char[grid.length];
            for (int j = 0; j < grid.length; j++) {
                column[j] = grid[i][j];
            }
            boolean isColumnValid = ArrayUtils.hasDuplicate(column);
            if (!isColumnValid) {
                return false;
            }
        }
        int[] mins = {0, 3, 6};
        for (int minX: mins) {
            for (int minY: mins) {
                char[] flattenedSquare = new char[9];
                int index = 0;
                for (int i = minX; i < minX + 3; i++) {
                    for (int j = minY; j < minY + 3; j++) {
                        flattenedSquare[index] = grid[i][j];
                        index++;
                    }
                }
                boolean isFlattenedSquareValid = ArrayUtils.hasDuplicate(flattenedSquare);
                if (!isFlattenedSquareValid) {
                    return false;
                }
            }
        }
        return true;
    }
}
