import org.dlpk.IntegerClassifier;
import org.junit.Test;
import static org.junit.Assert.*;


public class ClassifierTest {
    @Test
    public void testRaro() {
        IntegerClassifier classifier = new IntegerClassifier(IntegerClassifier.RARO_VAL);
       assertEquals( classifier.toString(), "RARO" );
       classifier.imprimir();
    }

    @Test
    public void testAlto() {
        IntegerClassifier classifier = new IntegerClassifier(IntegerClassifier.MEDIO_VAL + 1);
        assertEquals( classifier.toString(), "ALTO" );
        classifier.imprimir();
    }

    @Test
    public void testMedio() {
        IntegerClassifier classifier = new IntegerClassifier(IntegerClassifier.MEDIO_VAL);
        assertEquals( classifier.toString(), "MEDIO" );
        classifier.imprimir();
    }


}
