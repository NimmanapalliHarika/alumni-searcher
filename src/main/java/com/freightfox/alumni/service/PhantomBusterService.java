package com.freightfox.alumni.service;

import com.freightfox.alumni.dto.SearchRequest;
import com.freightfox.alumni.entity.AlumniProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.*;

@Service
public class PhantomBusterService {
    private final RestClient restClient = RestClient.create();
    @Value("${phantombuster.api-key:}") private String apiKey;
    @Value("${phantombuster.agent-id:}") private String agentId;

    /*
     * PhantomBuster agents have different input schemas and output formats.
     * Configure an agent for your search workflow, then map its output here.
     * We deliberately fail clearly when credentials are absent rather than
     * claiming that sample data came from LinkedIn.
     */
    public List<AlumniProfile> search(SearchRequest request) {
        if (apiKey.isBlank() || agentId.isBlank()) {
            throw new IllegalStateException(
                "PhantomBuster is not configured. Set PHANTOMBUSTER_API_KEY and PHANTOMBUSTER_AGENT_ID.");
        }

        Map<String, Object> arguments = new LinkedHashMap<>();
        arguments.put("university", request.university());
        arguments.put("designation", request.designation());
        if (request.passoutYear() != null) arguments.put("passoutYear", request.passoutYear());

        Map<?, ?> launchResponse = restClient.post()
            .uri("https://api.phantombuster.com/api/v2/agents/launch")
            .header("X-Phantombuster-Key-1", apiKey)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Map.of("id", agentId, "argument", arguments))
            .retrieve()
            .body(Map.class);

        // Launch is asynchronous; retrieve the agent's output/result and map
        // it to AlumniProfile based on the configured Phantom's output schema.
        // Do not assume launch response itself contains profile records.
        throw new IllegalStateException("PhantomBuster agent launched. Implement output retrieval and mapping for this agent's configured result format. Launch response: "
                + String.valueOf(launchResponse));
    }
}
