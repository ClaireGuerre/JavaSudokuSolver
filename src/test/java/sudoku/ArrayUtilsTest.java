package sudoku;

import co.sudoku.ArrayUtils;
import junit.framework.*;

public class ArrayUtilsTest extends TestCase {

    public void testSort() throws Exception {
        char[] input = {'9', '5', '5', '7', '8', '1', '0'};

        char[] expected = {'0', '1', '5', '5', '7', '8', '9'};

        char[] output = ArrayUtils.sort(input);

        assertEquals(input.length, output.length);

        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], output[i]);
        }
    }
}
