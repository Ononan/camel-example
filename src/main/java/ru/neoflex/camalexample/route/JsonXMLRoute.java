package ru.neoflex.camalexample.route;


import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import ru.neoflex.camalexample.dto.OrderDTO;

@Component
@RequiredArgsConstructor
public class JsonXMLRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true);

        onException(UnrecognizedPropertyException.class)
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
                .setBody().constant("Invalid data");

        rest("/order")
                .post()
                    .consumes("application/json")
                    .type(OrderDTO.class)
                    .route().routeId("json-order-to-xml")
                    .setHeader(Exchange.FILE_NAME).simple("${body.orderID}.xml")
                    .marshal().jacksonxml(true)
                    .log("body = \n${body}")
                    .to("file:output")
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));

    }
}
