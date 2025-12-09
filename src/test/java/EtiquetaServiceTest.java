import org.dlpk.model.Entrega;
import org.dlpk.model.frete.FretePadrao;
import org.dlpk.model.frete.TIPO_FRETE;
import org.dlpk.model.frete.TipoFreteIncompativelException;
import org.dlpk.model.valueobject.Destinario;
import org.dlpk.model.valueobject.Endereco;
import org.dlpk.model.valueobject.Etiqueta;
import org.dlpk.model.valueobject.Resumo;
import org.dlpk.service.EtiquetaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EtiquetaServiceTest {
    EtiquetaService etiquetaService;

    Entrega entregaHalfFilled(double peso, TIPO_FRETE tipoFrete) {
        //helper pra cria entregas tudo preenchido menos peso e tipoFrete
        return new Entrega(new Endereco("Endereço Teste 123"), peso, tipoFrete, new Destinario("Daniel Gomes Lipkin"));
    }

    @BeforeEach
    void setup() {
        etiquetaService = new EtiquetaService( new FretePadrao());
    }

    @Test
    void freteIncompativel() {
        Assertions.assertThrows(TipoFreteIncompativelException.class, () ->
                etiquetaService.calcularFrete( entregaHalfFilled(1.0, TIPO_FRETE.ECONOMICO) ));
        Assertions.assertThrows(TipoFreteIncompativelException.class, () ->
                etiquetaService.gerarEtiqueta( entregaHalfFilled(1.0, TIPO_FRETE.EXPRESSO) ));
    }

    @Test
    void freteCompativel() {
        Assertions.assertDoesNotThrow( () ->
                etiquetaService.calcularFrete( entregaHalfFilled(1.0, TIPO_FRETE.PADRAO) ));
    }

    @Test
    void resumoCorreto() {
        Entrega entrega = entregaHalfFilled(1.0, TIPO_FRETE.PADRAO);
        Resumo resumo = etiquetaService.gerarResumoEntrega(entrega);
        Assertions.assertEquals(resumo.destinario(), entrega.getDestinatario());
        Assertions.assertEquals(resumo.tipoFrete(), entrega.getTipoFrete());
        Assertions.assertEquals(resumo.valor(), etiquetaService.calcularFrete(entrega));
    }

    @Test
    void etiquetaCorreta() {
        Entrega entrega = entregaHalfFilled(1.0, TIPO_FRETE.PADRAO);
        Etiqueta etiqueta = etiquetaService.gerarEtiqueta(entrega);
        Assertions.assertEquals(etiqueta.destinario(), entrega.getDestinatario());
        Assertions.assertEquals(etiqueta.endereco(), entrega.getEndereco());
        Assertions.assertEquals(etiqueta.valor(), etiquetaService.calcularFrete(entrega));
    }

}

