package junit;

import app.mine.Main;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class UnitTest {
    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Test
    public void FourTimesFour() throws Exception {
        systemInMock.provideText("4\n");
        Main.startUnitTest();

        systemInMock.provideText("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16");
        Main.startUnitTest();

        assertEquals("Please, now enter values of the first matrix (A)", log.getLog());

        assertEquals("Enter your size of matrix B (e.g. 4 means it will be 4x4)",log.getLog());



/*
        systemInMock.provideText("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16");
        assertEquals("Please, now enter values of the first matrix (A)", ma.MatrixA());

        assertEquals("Please, now enter values of the first matrix (A)",log.getLog());
        systemInMock.provideText("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16");

        assertEquals("Enter your size of matrix B (e.g. 4 means it will be 4x4)",log.getLog());
        systemInMock.provideText("4\n");

        assertEquals("Please, now enter values of the first matrix (B)",log.getLog());
        systemInMock.provideText("1\n2\n2\n3\n4\n5\n6\n7\n8\n9\n20\n22\n22\n23\n24\n25\n26");

*/
    }


}