package com.cbx.springapi

import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.RequestHandler
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*
import java.util.HashSet




@Configuration
//@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api() : Docket{
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
//        val DEFAULT_CONTACT = Contact("tester", "cbx.com", "tester@cbx.com")
//        val DEFAULT_PRODUCES_AND_CONSUMES: Set<String> = HashSet(
//            Arrays.asList(
//                "application/json",
//                "application/xml"
//            )
//        )
//        return Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(ApiInfo("cbx-api", "self learning spring cloud", "0.0.1", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", listOf()))
//            .produces(DEFAULT_PRODUCES_AND_CONSUMES)
//            .consumes(DEFAULT_PRODUCES_AND_CONSUMES)

    }
}
