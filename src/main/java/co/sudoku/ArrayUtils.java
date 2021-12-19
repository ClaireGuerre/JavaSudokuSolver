package co.sudoku;

public class ArrayUtils {
    public static char[] sort(char[] grid) {
        if (grid.length == 1) {
            return grid;
        }
        int mid = grid.length/2;
        char[] left = new char[mid];
        for (int i = 0; i < mid; i++) {
            left[i] = grid[i];
        }
        char[] right = new char[grid.length - mid];
        int index = 0;
        for (int i = mid; i < grid.length; i++) {
            right[index] = grid[i];
            index++;
        }
        char[] sortedLeft = sort(left);
        char[] sortedRight = sort(right);
        return merge(sortedLeft, sortedRight);
    }

    private static char[] merge(char[] left, char[] right) {
        char[] result = new char[left.length + right.length];
        int resultIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        while (resultIndex < result.length) {
            char cLeft = (char) -1;
            char cRight = (char) -1;
            if (leftIndex < left.length) {
                cLeft = left[leftIndex];
            }
            if (rightIndex < right.length){
                cRight = right[rightIndex];
            }
            if (cLeft < cRight) {
                result[resultIndex] = cLeft;
                leftIndex++;
            } else  {
                result[resultIndex] = cRight;
                rightIndex++;
            }
            resultIndex++;
        }
        return result;
    }

    public static boolean contains(char toFind, char[] input) {
        int index = 0;
        while (index < input.length) {
            if (input[index] == toFind) {
                return true;
            }
            index++;
        }
        return false;
    }

    public static boolean hasDuplicate(char[] grid) {
        char[] sortedGrid = ArrayUtils.sort(grid);
        int index = 0;
        while (index < grid.length - 1) {
            if (sortedGrid[index] == sortedGrid[index + 1]) {
                return false;
            }
            index++;
        }
        return true;
    }
}
