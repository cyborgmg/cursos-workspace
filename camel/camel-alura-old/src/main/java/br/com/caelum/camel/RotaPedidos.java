package br.com.caelum.camel;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;

import java.time.Duration;

public class RotaPedidos {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addComponent("activemq", ActiveMQComponent.activeMQComponent("tcp://localhost:61616"));
		
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				
				errorHandler(
						deadLetterChannel("file:erro")
						.logExhaustedMessageHistory(true)
						.maximumRedeliveries(3)
						.redeliveryDelay(2000)
						.onRedelivery(new Processor() {
								@Override
								public void process(Exchange exchange) throws Exception {
									// TODO Auto-generated method stub
									int counter = (int) exchange.getIn().getHeader(Exchange.REDELIVERY_COUNTER);
									int max = (int) exchange.getIn().getHeader(Exchange.REDELIVERY_MAX_COUNTER);
									System.out.println("Redelivery - " + counter + "/" + max );
								}
							})
						);
				
				from("activemq:queue:pedidos")
				.routeId("rota-pedidos")
				.to("validator:pedido.xsd")
				.multicast()
					.to("direct:soap")
					.to("direct:http");
				
				from("direct:http")
						.routeId("rota-http")
						.setProperty("pedidoId",xpath("/pedido/id/text()"))
						.setProperty("clienteId",xpath("/pedido/pagamento/email-titular/text()"))
						.setProperty("ebookId",xpath("/pedido/itens/item/livro/codigo/text()"))
						.split().xpath("/pedido/itens/item")
						.filter()
							.xpath("/item/formato[text()='EBOOK']")
						.marshal().xmljson()
						.log("${id} - ${body}")
						.setHeader(Exchange.HTTP_METHOD,HttpMethods.GET)
						.setHeader(Exchange.HTTP_QUERY,simple("ebookId=${property.ebookId}&pedidoId=${property.pedidoId}&clienteId=${property.clienteId}"))
						.to("http4://localhost:8080/webservices/ebook/item");
				
				from("direct:soap")
					.routeId("rota-soap")
					.to("xslt:pedido-para-soap.xslt")
					.log("${body}")
					.setHeader(Exchange.CONTENT_TYPE, constant("text/xml"))
				.to("http4://localhost:8080/webservices/financeiro");
				
			}
		});

		context.start();

		Thread.sleep(Duration.ofSeconds(20).toMillis());

		context.stop();

	}	
}
