package mai.administracaousuarios.project.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI customAPI() {
        return new OpenAPI().info(new Info().title("Administracao Usuarios")
                .version("1.2")
                .description("Administrar o cadastro, autenticação, atualização e exclusão de usuarios/empresas")
                .license(new License().name("Licence 1.2").url("license_1.2")));
    }
}
