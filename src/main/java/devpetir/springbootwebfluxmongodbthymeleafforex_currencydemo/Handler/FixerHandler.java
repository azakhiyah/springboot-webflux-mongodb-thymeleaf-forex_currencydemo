package devpetir.springbootwebfluxmongodbthymeleafforex_currencydemo.Handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import devpetir.springbootwebfluxmongodbthymeleafforex_currencydemo.service.FixerService;
import reactor.core.publisher.Mono;

@Component
public class FixerHandler {

    private final FixerService fixerService;
    private final ObjectMapper objectMapper;

    public FixerHandler(FixerService fixerService, ObjectMapper objectMapper) {
        this.fixerService = fixerService;
        this.objectMapper = objectMapper;
    }

    public Mono<ServerResponse> getCurrentRate(ServerRequest request) {
        // if startDate and endDate in hardCode
        // String startDate = request.queryParam("start_date").orElse("2024-01-01"); // Default to a specific date if missing
        // String endDate = request.queryParam("end_date").orElse("2024-01-02");     // Default to a specific date if missing
        // if startDate and endDate as parameter in url
        String startDate = request.pathVariable("startDate");
        String endDate = request.pathVariable("endDate");
        int page = Integer.parseInt(request.queryParam("page").orElse("1"));
        int size = 10; // Number of items per page

        Mono<String> rateData = fixerService.getCurrentRate(startDate, endDate);

        // return ServerResponse.ok()
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .body(rateData,String.class);
        return rateData.flatMap(data -> {
            try {
                JsonNode jsonNode = objectMapper.readTree(data);
                JsonNode ratesNode = jsonNode.path("rates").path(startDate);

                Map<String, Double> rates = new HashMap<>();
                Iterator<Map.Entry<String, JsonNode>> fields = ratesNode.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> field = fields.next();
                    rates.put(field.getKey(), field.getValue().asDouble());
                }

                //return ServerResponse.ok().render("rates", Map.of("rates", rates));

                //Implement pagination
                int totalItems = rates.size();
                int totalPages = (int) Math.ceil((double) totalItems / size);
                int fromIndex = (page - 1) * size;

                Map<String, Double> pagedRates = rates.entrySet().stream()
                        .skip(fromIndex)
                        .limit(size)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                return ServerResponse.ok().render("rates", Map.of(
                        "rates", pagedRates,
                        "currentPage", page,
                        "totalPages", totalPages
                ));
            } catch (Exception e) {
                return ServerResponse.status(500).bodyValue("Error processing rates data");
            }
        });
        
    } 

    
}
