package org.mule.transport.cicsStreaming.config;

import org.mule.config.spring.factories.OutboundEndpointFactoryBean;
import org.mule.config.spring.parsers.specific.ServiceDefinitionParser;
import org.mule.config.spring.parsers.specific.TransformerDefinitionParser;
import org.mule.config.spring.parsers.specific.endpoint.TransportEndpointDefinitionParser;
import org.mule.config.spring.parsers.specific.endpoint.TransportGlobalEndpointDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import org.mule.transport.cics.CicsService;
import org.mule.transport.cics.transformers.LoggingTransformer;
import org.mule.transport.cicsStreaming.transformers.*;

public class CicsNamespaceHandler extends NamespaceHandlerSupport {
	public void init() {

		registerBeanDefinitionParser("service", new ServiceDefinitionParser(CicsService.class));

		// register the parser for endpoints with cics protocol.
		String protocol = "cics-streaming";
		String[] requiredAttributes = {};
		registerBeanDefinitionParser("cics-endpoint",new TransportGlobalEndpointDefinitionParser(protocol , requiredAttributes));
		registerBeanDefinitionParser("cics-outbound-endpoint", new TransportEndpointDefinitionParser(protocol , OutboundEndpointFactoryBean.class , requiredAttributes));

		// register the parser for endpoints with stubtest protocol.
		protocol = "stubtest-streaming";
		registerBeanDefinitionParser("dummy-response-endpoint", new TransportGlobalEndpointDefinitionParser(protocol , requiredAttributes));

        // register the transformers
		registerBeanDefinitionParser("log-transformer" , new TransformerDefinitionParser(LoggingTransformer.class));
		registerBeanDefinitionParser("copybook-to-xml" , new TransformerDefinitionParser(CopyBookToXml.class));
		registerBeanDefinitionParser("xml-to-copybook" , new TransformerDefinitionParser(XmlToCopyBook.class));
		registerBeanDefinitionParser("soap-to-xml" , new TransformerDefinitionParser(SoapToXml.class));
		registerBeanDefinitionParser("xml-to-soap" , new TransformerDefinitionParser(XmlToSoap.class));
		registerBeanDefinitionParser("rest-to-xml" , new TransformerDefinitionParser(RestToXml.class));
		registerBeanDefinitionParser("xml-to-rest" , new TransformerDefinitionParser(XmlToRest.class));
		registerBeanDefinitionParser("xml-inbound-transformer" , new TransformerDefinitionParser(XmlInboundTransformer.class));
		registerBeanDefinitionParser("exception-to-fault-message" , new TransformerDefinitionParser(ExceptionToFaultMessage.class));
		registerBeanDefinitionParser("bytearray-to-xml" , new TransformerDefinitionParser(ByteArrayToXml.class));
		registerBeanDefinitionParser("ogis-copybook-outgoing-handler", new TransformerDefinitionParser(OGISCopyBookOutgoingHandler.class));
		registerBeanDefinitionParser("ogis-copybook-incoming-handler", new TransformerDefinitionParser(OGISCopyBookIncomingHandler.class));
		registerBeanDefinitionParser("ogis-copybook-to-xml" , new TransformerDefinitionParser(OGISCopyBookToXml2.class));
		registerBeanDefinitionParser("ogis-copybook-incoming-handler" , new TransformerDefinitionParser(OGISCopyBookIncomingHandler.class));
		registerBeanDefinitionParser("ogis-copybook-outgoing-handler" , new TransformerDefinitionParser(OGISCopyBookOutgoingHandler.class));
		registerBeanDefinitionParser("ogis-copybook-to-xml" , new TransformerDefinitionParser(OGISCopyBookToXml2.class));
	}
}
