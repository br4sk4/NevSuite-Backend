package net.naffets.nevsuite.backend.framework.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author br4sk4 / created on 15.10.2017
 */
@Service
public class BackendComponentService {

    @Autowired
    private DiscoveryClient discoveryClient;

    public String respond() {
        return "May the force be with you!";
    }

    public List<String> services() {
        return discoveryClient.getServices();
    }

    public List<String> instances(String service) {
        return discoveryClient.getInstances(service).stream().map(serviceInstance -> serviceInstance.getUri().toString()).collect(Collectors.toList());
    }

}
