package guru.springframework.msscbreweryclient.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;

@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class CustomerClient {

	private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
	private String apihost;
	private final RestTemplate restTemplate;
	
	public CustomerClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
    public CustomerDto getCustomerById(UUID uuid){
        return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + uuid.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
    }

    public void updateCustomer(UUID uuid, CustomerDto customerDto){
        restTemplate.put(apihost + CUSTOMER_PATH_V1 + "/" + uuid.toString(), customerDto);
    }

    public void deleteCustomer(UUID uuid){
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + "/" + uuid );
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
