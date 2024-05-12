package devpetir.springbootwebfluxmongodbthymeleafforex_currencydemo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class FixerService {
    
    private final WebClient webClient;

    public FixerService() {
        this.webClient = WebClient.create("https://api.apilayer.com/fixer");
    }

    public Mono<String> getCurrentRate(String startDate, String endDate) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/timeseries")
                    .queryParam("start_date", startDate)
                    .queryParam("end_date", endDate)
                    .queryParam("apikey", "nu4qUAjNLKNJHFPxypLOGa174R06OtlM")
                    .build())
                .retrieve()
                .bodyToMono(String.class);
    }

}