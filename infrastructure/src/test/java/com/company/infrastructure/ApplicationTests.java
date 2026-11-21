package com.company.infrastructure;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // Carga todos el contexto de la aplicación, incluyendo beans, configuración y dependencias. Esto permite realizar pruebas de integración y verificar que los componentes funcionen correctamente juntos.
@Disabled // Deshabilita temporalmente la ejecución de esta clase de prueba. Esto puede ser útil si la prueba aún no está lista o si se desea evitar su ejecución durante el desarrollo.
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
