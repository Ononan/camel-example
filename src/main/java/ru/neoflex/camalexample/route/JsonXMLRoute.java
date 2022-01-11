package ru.neoflex.camalexample.route;


import lombok.RequiredArgsConstructor;
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

        rest("/order")
                .consumes("application/json")
                .post()
                    .type(OrderDTO.class)
                    .route().routeId("json-order-to-xml")
                    .setHeader("fileName").simple("${body.orderID}")
                    .marshal().jacksonxml(true)
                    .log("body = \n${body}")
                    .to("file:output?fileName=${headers.fileName}.xml");

    }
}
