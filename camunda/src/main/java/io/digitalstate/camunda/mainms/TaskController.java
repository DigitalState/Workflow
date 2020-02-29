package io.digitalstate.camunda.mainms;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/custom")
public class TaskController {

    private HistoryService historyService;

    public TaskController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping(value = "/task-search", produces= MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<HistoricTaskInstance>> searchByTaskIds(@RequestParam("taskIdIn") @Size(min = 1, max = 100) List<UUID> taskIds){
        List<HistoricTaskInstance> instances = historyService.createNativeHistoricTaskInstanceQuery()
                .sql("SELECT * FROM act_hi_taskinst WHERE ID_ IN (" +
                        taskIds.stream().map(UUID::toString).collect(Collectors.joining("', '", "'", "'"))
                        + ")")
                .list();

        return ResponseEntity.ok(instances);
    }
}
