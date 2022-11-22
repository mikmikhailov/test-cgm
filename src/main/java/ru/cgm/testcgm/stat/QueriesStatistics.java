package ru.cgm.testcgm.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QueriesStatistics {
    private float totalQueries;
    private float averageInStrings;
    private float averageMaxSequence;
}
