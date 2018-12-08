package net.naffets.nevsuite.backend.framework.domain.service;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author br4sk4 / created on 15.10.2017
 */
@Service
public class BackendComponentService {

    private DiscoveryClient discoveryClient;

    @Inject
    public BackendComponentService(
            DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public String respond() {
        return "May the force be with you!";
    }

    public List<String> services() {
        return discoveryClient.getServices().stream()
                .filter(service -> !service.equals("frontend-provider"))
                .collect(Collectors.toList());
    }

    public List<String> instances(String service) {
        return discoveryClient.getInstances(service).stream()
                .map(serviceInstance -> serviceInstance.getUri().toString())
                .collect(Collectors.toList());
    }

}
