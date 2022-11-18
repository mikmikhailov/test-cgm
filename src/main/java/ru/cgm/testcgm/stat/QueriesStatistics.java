package ru.cgm.testcgm.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QueriesStatistics {
    private int totalQueries;
    private int averageInStrings;
    private int averageMaxSequence;
}
