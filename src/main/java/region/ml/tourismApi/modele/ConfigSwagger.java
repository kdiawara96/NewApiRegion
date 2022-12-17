package region.ml.tourismApi.modele;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ConfigSwagger {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any()).build().pathMapping("/")
                .apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    @Bean
    public ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("This is my first API in ODC, the role of this API is region management!").version("1.0").license("(C) Copyright Test")
                .description("Une API qui permet de gérer l'ensemble de ses régions. A travers l'apirest l'administrateur " +
                        "de l'agence de tourisme doit pouvoir d'ajouter une " +
                        "région en fonctions d'un pays, coderegion, nom, domaine activité " +
                        "de la région, sa superficie ,les chiffres de la populations en" +
                        " fonction d'une année donnée, la langue majoritairement parlée.*" +
                        " En plus , il doit pouvoir modifier une région, la supprimer et " +
                        "avoir le liste globale des régions sans pays, et avec pays. Dans ce cas si" +
                        ", chaque région doit avoir comme pays \"Mali\", bien sur avec la possibilité " +
                        "d'ajouter d'autres pays.");
        return builder.build();
    }
}
