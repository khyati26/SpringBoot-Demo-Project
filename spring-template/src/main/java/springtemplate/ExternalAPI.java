package springtemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ExternalAPI {

    @Autowired
    private RestTemplate restTemplate;

    public List<Manager> getManagersList() throws APIException {
        List<Manager> managerList = null;
        try {
            URI getManagerUrl = new URI("https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers");
            ResponseEntity<Manager[]> response = this.restTemplate.getForEntity(getManagerUrl, Manager[].class);
            managerList = new ArrayList<>(Arrays.asList(response.getBody()));
        } catch (URISyntaxException ex) {
            throw new APIException("Can not load manager from aws", ex);
        }
        return managerList;
    }
}
