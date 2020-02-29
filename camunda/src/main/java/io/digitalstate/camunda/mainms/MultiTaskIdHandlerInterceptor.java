package io.digitalstate.camunda.mainms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.HistoryService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Component
public class MultiTaskIdHandlerInterceptor implements HandlerInterceptor {

    private ObjectMapper mapper;
    private HistoryService historyService;

    public MultiTaskIdHandlerInterceptor(ObjectMapper mapper,
                                         HistoryService historyService) {
        this.mapper = mapper;
        this.historyService = historyService;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("TEST123");
        if (request.getMethod().equals(HttpMethod.POST.toString())) {
            JsonNode node = mapper.readTree(request.getReader());

            if (node.hasNonNull("taskIdIn")) {
                List<String> taskIds = node.findValuesAsText("taskIdIn");

                return false;
            } else {
                return true;
            }
        } else if (request.getMethod().equals(HttpMethod.GET.toString()) && request.getQueryString().contains("taskIdIn")) {
            response.sendRedirect("/custom/task-search?" + request.getQueryString());

            return false;
        } else {
            return true;
        }
    }
}
