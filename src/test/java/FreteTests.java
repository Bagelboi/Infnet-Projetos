import org.dlpk.model.frete.*;
import org.dlpk.model.valueobject.Destinario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FreteTests {
    ICalculadoraFrete calculadoraFrete;

    @Test
    public void fretePadrao() {
        calculadoraFrete = new FretePadrao();
        assertTrue(calculadoraFrete.tipoFreteCompativel(TIPO_FRETE.PADRAO));
        assertEquals(calculadoraFrete.calcular(1), 1.2);
    }

    @Test
    public void freteExpresso() {
        calculadoraFrete = new FreteExpresso();
        assertTrue(calculadoraFrete.tipoFreteCompativel(TIPO_FRETE.EXPRESSO));
        assertEquals(calculadoraFrete.calcular(1), 11.5);
    }

    @Test
    public void freteEconomico() {
        calculadoraFrete = new FreteEconomico();
        assertTrue(calculadoraFrete.tipoFreteCompativel(TIPO_FRETE.ECONOMICO));
        assertEquals(calculadoraFrete.calcular(5), 0.5);
        assertEquals(calculadoraFrete.calcular(1), 0);
    }

    @Test
    public void fretePromocional() {
        calculadoraFrete = new FretePromocional( new FretePadrao() );
        assertTrue(calculadoraFrete.tipoFreteCompativel(TIPO_FRETE.PADRAO));
        assertEquals( calculadoraFrete.calcular( 12 ), 13.2 );
        assertThrows(FretePromocional.PromocaoPesoBaixoDemaisException.class, () -> {
             calculadoraFrete.calcular(1);
        });
    }
}
