package ru.cgm.testcgm;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cgm.testcgm.services.StatisticService;
import ru.cgm.testcgm.stat.CharStatistics;

import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping("/stat")
public class StatisticController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticController( StatisticService statisticService){
        this.statisticService=statisticService;
    }

    @GetMapping("string-stat")
    @Operation(summary = "статистика по символам в строке")
    public Map<Character, CharStatistics> getStatCharacters(@Parameter(description = "строка для тестирования") @NotNull String stringForAnalysis){

        return statisticService.getCharsStatistics(stringForAnalysis);
    }

    @GetMapping("query-stat")
    @Operation(summary = "статистика по символам в строке")
    public Map getStatQueries(){

        return statisticService.gerQueriesStatistics();
    }

}
