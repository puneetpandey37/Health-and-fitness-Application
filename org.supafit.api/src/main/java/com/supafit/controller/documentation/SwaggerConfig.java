package com.supafit.controller.documentation;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.JacksonScalaSupport;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.configuration.SpringSwaggerModelConfig;
import com.mangofactory.swagger.configuration.SwaggerGlobalSettings;
import com.mangofactory.swagger.core.DefaultSwaggerPathProvider;
import com.mangofactory.swagger.core.SwaggerApiResourceListing;
import com.mangofactory.swagger.core.SwaggerPathProvider;
import com.mangofactory.swagger.scanners.ApiListingReferenceScanner;
import com.wordnik.swagger.model.ApiInfo;
import com.wordnik.swagger.model.AuthorizationScope;
import com.wordnik.swagger.model.AuthorizationType;
import com.wordnik.swagger.model.GrantType;
import com.wordnik.swagger.model.ImplicitGrant;
import com.wordnik.swagger.model.LoginEndpoint;

@Configuration
@ComponentScan(basePackages = "com.mangofactory.swagger")
public class SwaggerConfig {

	public static final List<String> DEFAULT_INCLUDE_PATTERNS = Arrays.asList(
			"/locations/.*", "/exercises/.*", "/exercises", "/users/.*",
			"/users", "/questions/.*", "/questions", "/programs",
			"/programs/.*", "/plans", "/plans/.*", "/foods/.*", 
			"/foods", "/signup/.*", "/signup", "/signin/.*", "/signin",
			"/meals/.*", "/meals", "/goals/.*", "/goals",
			"/medicalconditions/.*", "/medicalconditions",
			"/summary/.*","/summary",
			"/coaches/.*", "/coaches",
			"/workouts/.*", "/workouts" );
	// public static final String SWAGGER_GROUP = "org.supafit.api";
	public static final String SWAGGER_GROUP = "supafit-api";

	@Value("http://10.20.6.46:8080")
//	@Value("http://54.187.141.143:8080")
	private String docsLocation;

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;
	@Autowired
	private SpringSwaggerModelConfig springSwaggerModelConfig;

	/**
	 * Adds the jackson scala module to the MappingJackson2HttpMessageConverter
	 * registered with spring Swagger core models are scala so we need to be
	 * able to convert to JSON Also registers some custom serializers needed to
	 * transform swagger models to swagger-ui required json format
	 */
	@Bean
	public JacksonScalaSupport jacksonScalaSupport() {
		JacksonScalaSupport jacksonScalaSupport = new JacksonScalaSupport();
		// Set to false to disable
		jacksonScalaSupport.setRegisterScalaModule(true);
		return jacksonScalaSupport;
	}

	/**
	 * Global swagger settings
	 */
	@Bean
	public SwaggerGlobalSettings swaggerGlobalSettings() {
		SwaggerGlobalSettings swaggerGlobalSettings = new SwaggerGlobalSettings();
		swaggerGlobalSettings.setGlobalResponseMessages(springSwaggerConfig
				.defaultResponseMessages());
		swaggerGlobalSettings.setIgnorableParameterTypes(springSwaggerConfig
				.defaultIgnorableParameterTypes());
		swaggerGlobalSettings.setParameterDataTypes(springSwaggerModelConfig
				.defaultParameterDataTypes());
		return swaggerGlobalSettings;
	}

	/**
	 * API Info as it appears on the swagger-ui page
	 */
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("API Endpoints:",
				"Health and Wellness Mobile Application",
				"", "puneetpandey37@gmail.com",
				"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html");
		return apiInfo;
	}

	/**
	 * Configure a SwaggerApiResourceListing for each swagger instance within
	 * your app. e.g. 1. private 2. external apis Required to be a spring bean
	 * as spring will call the postConstruct method to bootstrap swagger
	 * scanning.
	 *
	 * @return
	 */
	@Bean
	public SwaggerApiResourceListing swaggerApiResourceListing() {
		// The group name is important and should match the group set on
		// ApiListingReferenceScanner
		// Note that swaggerCache() is by DefaultSwaggerController to serve the
		// swagger json
		SwaggerApiResourceListing swaggerApiResourceListing = new SwaggerApiResourceListing(
				springSwaggerConfig.swaggerCache(), SWAGGER_GROUP);

		// Set the required swagger settings
		swaggerApiResourceListing
				.setSwaggerGlobalSettings(swaggerGlobalSettings());

		// Use a custom path provider or
		// springSwaggerConfig.defaultSwaggerPathProvider()
		swaggerApiResourceListing.setSwaggerPathProvider(apiPathProvider());

		// Supply the API Info as it should appear on swagger-ui web page
		swaggerApiResourceListing.setApiInfo(apiInfo());

		// Global authorization - see the swagger documentation
		swaggerApiResourceListing.setAuthorizationTypes(authorizationTypes());

		// Every SwaggerApiResourceListing needs an ApiListingReferenceScanner
		// to scan the spring request mappings
		swaggerApiResourceListing
				.setApiListingReferenceScanner(apiListingReferenceScanner());
		return swaggerApiResourceListing;
	}

	@Bean
	/**
	 * The ApiListingReferenceScanner does most of the work.
	 * Scans the appropriate spring RequestMappingHandlerMappings
	 * Applies the correct absolute paths to the generated swagger resources
	 */
	public ApiListingReferenceScanner apiListingReferenceScanner() {
		ApiListingReferenceScanner apiListingReferenceScanner = new ApiListingReferenceScanner();

		// Picks up all of the registered spring RequestMappingHandlerMappings
		// for scanning
		apiListingReferenceScanner
				.setRequestMappingHandlerMapping(springSwaggerConfig
						.swaggerRequestMappingHandlerMappings());

		// Excludes any controllers with the supplied annotations
		apiListingReferenceScanner.setExcludeAnnotations(springSwaggerConfig
				.defaultExcludeAnnotations());

		//
		apiListingReferenceScanner
				.setResourceGroupingStrategy(springSwaggerConfig
						.defaultResourceGroupingStrategy());

		// Path provider used to generate the appropriate uri's
		apiListingReferenceScanner.setSwaggerPathProvider(apiPathProvider());

		// Must match the swagger group set on the SwaggerApiResourceListing
		apiListingReferenceScanner.setSwaggerGroup(SWAGGER_GROUP);

		// Only include paths that match the supplied regular expressions
		apiListingReferenceScanner.setIncludePatterns(DEFAULT_INCLUDE_PATTERNS);

		return apiListingReferenceScanner;
	}

	/**
	 * Example of a custom path provider
	 */
	@Bean
	public ApiPathProvider apiPathProvider() {
		ApiPathProvider apiPathProvider = new ApiPathProvider(docsLocation);
		apiPathProvider.setDefaultSwaggerPathProvider(springSwaggerConfig
				.defaultSwaggerPathProvider());
		return apiPathProvider;
	}

	private List<AuthorizationType> authorizationTypes() {
		ArrayList<AuthorizationType> authorizationTypes = new ArrayList<AuthorizationType>();

		List<AuthorizationScope> authorizationScopeList = newArrayList();
		authorizationScopeList.add(new AuthorizationScope("global",
				"access all"));

		List<GrantType> grantTypes = newArrayList();

		LoginEndpoint loginEndpoint = new LoginEndpoint(apiPathProvider()
				.getAppBasePath() + "/user/authenticate");
		grantTypes.add(new ImplicitGrant(loginEndpoint, "access_token"));

		return authorizationTypes;
	}
	
	/*private List<AuthorizationType> authorizationTypes() {
	    ArrayList<AuthorizationType> authorizationTypes = new ArrayList<AuthorizationType>();

	    List<AuthorizationScope> authorizationScopeList = newArrayList();
	    authorizationScopeList.add(new AuthorizationScope("global", "access all"));

	    List<GrantType> grantTypes = newArrayList();

	    LoginEndpoint loginEndpoint = new LoginEndpoint("http://petstore.swagger.wordnik.com/api/oauth/dialog");
	    grantTypes.add(new ImplicitGrant(loginEndpoint, "access_token"));

	    TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint("http://petstore.swagger.wordnik.com/oauth/requestToken", "client_id", "client_secret");
	    TokenEndpoint tokenEndpoint = new TokenEndpoint("http://petstore.swagger.wordnik.com/oauth/token", "auth_code");

	    AuthorizationCodeGrant authorizationCodeGrant = new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint);
	    grantTypes.add(authorizationCodeGrant);

	    OAuth oAuth = new OAuthBuilder()
	            .scopes(authorizationScopeList)
	            .grantTypes(grantTypes)
	            .build();

	    authorizationTypes.add(oAuth);
	    return authorizationTypes;
	  }*/
	

	@Bean
	public SwaggerPathProvider relativeSwaggerPathProvider() {
		return new ApiRelativeSwaggerPathProvider();
	}

	private class ApiRelativeSwaggerPathProvider extends
			DefaultSwaggerPathProvider {
		@Override
		public String getAppBasePath() {
			return "/";
		}

		@Override
		public String getSwaggerDocumentationBasePath() {
			return "/api-docs";
		}
	}
}