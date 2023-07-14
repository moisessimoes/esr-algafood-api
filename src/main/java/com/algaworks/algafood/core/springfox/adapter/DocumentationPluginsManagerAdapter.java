package com.algaworks.algafood.core.springfox.adapter;

//@SuppressWarnings("deprecation")
//@Primary
//@Component
public class DocumentationPluginsManagerAdapter { //extends DocumentationPluginsManager {
	
	//19.5. Resolvendo conflito de dependências com Spring HATEOAS e SpringFox
	
//	@Autowired
//	@Qualifier("resourceGroupingStrategyRegistry")
//	private PluginRegistry<ResourceGroupingStrategy, DocumentationType> resourceGroupingStrategies;
//
//	@Autowired
//	@Qualifier("defaultsProviderPluginRegistry")
//	private PluginRegistry<DefaultsProviderPlugin, DocumentationType> defaultsProviders;
//
//	@Override
//	public ResourceGroupingStrategy resourceGroupingStrategy(DocumentationType documentationType) {
//		return resourceGroupingStrategies.getPluginOrDefaultFor(documentationType, new SpringGroupingStrategy());
//	}
//
//	@Override
//	public DocumentationContextBuilder createContextBuilder(DocumentationType documentationType, DefaultConfiguration defaultConfiguration) {
//		return defaultsProviders.getPluginOrDefaultFor(documentationType, defaultConfiguration)
//								.create(documentationType)
//								.withResourceGroupingStrategy(resourceGroupingStrategy(documentationType));
//	}
}