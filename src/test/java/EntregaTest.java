import org.dlpk.model.Entrega;
import org.dlpk.model.frete.TIPO_FRETE;
import org.dlpk.model.valueobject.Destinario;
import org.dlpk.model.valueobject.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntregaTest {

    @Test
    void pesoNegativo() {
        Assertions.assertThrows( IllegalArgumentException.class, () ->
                new Entrega(new Endereco("Teste"), -1, TIPO_FRETE.PADRAO, new Destinario("Macaco Albino")) );
    }

}
