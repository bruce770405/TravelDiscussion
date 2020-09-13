package javaserver;

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
public class SwaggerWebMvcConfig {
	@Bean
	public Docket alipayApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("旅遊討論版Controller")
				.apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("javaserver.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("TravelDiscussion")
				.description("文章管理後台")
				.termsOfServiceUrl("http://localhost")
				.version("1.0")
				.build();
	}

}