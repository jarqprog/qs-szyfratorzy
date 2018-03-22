package tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataToolTest {

    @Test
    public void isWhitespaceRemoved(){
        String expected = "lubie placki i ty też";
        String actual = "lubie  placki                i ty  też ";
        actual = DataTool.removeWhitespacesFromString(actual);
        assertEquals(expected,actual);
    }

    @Test
    public void isStringShortened(){
        String expected = "lubie...";
        String actual = "lubie placki i ty też";
        int cutIndex = 5;
        actual = DataTool.getShortText(cutIndex, actual);
        assertEquals(expected, actual);

    }


}