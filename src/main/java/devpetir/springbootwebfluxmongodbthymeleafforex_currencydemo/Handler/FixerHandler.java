package devpetir.springbootwebfluxmongodbthymeleafforex_currencydemo.Handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import devpetir.springbootwebfluxmongodbthymeleafforex_currencydemo.service.FixerService;
import reactor.core.publisher.Mono;

@Component
public class FixerHandler {

    private final FixerService fixerService;

    public FixerHandler(FixerService fixerService) {
        this.fixerService= fixerService;
    }

    public Mono<ServerResponse> getCurrentRate(ServerRequest request) {
        // if startDate and endDate in hardCode
        // String startDate = request.queryParam("start_date").orElse("2024-01-01"); // Default to a specific date if missing
        // String endDate = request.queryParam("end_date").orElse("2024-01-02");     // Default to a specific date if missing
        // if startDate and endDate as parameter in url
        String startDate = request.pathVariable("startDate");
        String endDate = request.pathVariable("endDate");

        Mono<String> rateData = fixerService.getCurrentRate(startDate, endDate);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(rateData,String.class);
        
    } 

    
}
