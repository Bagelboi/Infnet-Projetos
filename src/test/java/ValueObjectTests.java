import org.dlpk.model.valueobject.Destinario;
import org.dlpk.model.valueobject.Endereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ValueObjectTests {
    @Test
    public void enderecoVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Endereco("   ");
        });
    }

    @Test
    public void destinarioFormatoCerto() {
        Destinario dest = new Destinario("Daniel Gomes Lipkin");
        assertEquals( dest.getPrimeiroNome(), "Daniel" );
        assertEquals( dest.getSegundoNome(), "Gomes Lipkin" );
    }

    @Test
    public void destinarioVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Destinario("    ");
        });
    }
}
