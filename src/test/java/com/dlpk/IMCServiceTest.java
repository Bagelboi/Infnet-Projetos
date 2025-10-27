package com.dlpk;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IMCServiceTest {

    @Test
    void testCalculoIMCComMock() {
        IMCService imcService = mock(IMCService.class);
        when(imcService.calcularIMC(80.0, 1.80)).thenReturn(24.69); // define the return value

        double imc = imcService.calcularIMC(80, 1.80);
        assertThat(imc).isBetween(24.0, 25.0);

    }

}
