package ru.cgm.testcgm.services;

import org.springframework.stereotype.Service;
import ru.cgm.testcgm.stat.CharStatistics;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticService {

    private long counter=0;

    private long queryId=0;

    private static Map<Long,Map<Character,CharStatistics>> queriesStat=new HashMap<>();

    public Map<Character, CharStatistics> getCharsStatistics(@NotNull String stringForAnalysis){
        Map<Character,CharStatistics> statMap = new HashMap<>();

        char[] charArray =stringForAnalysis.toCharArray();

        for(int i=0; i < charArray.length; i++) {
            CharStatistics stat = statMap.get(charArray[i]);
            if (stat == null) {
                stat = new CharStatistics(1, 1);
            } else {
                stat.setTotalCount(stat.getTotalCount() + 1);
            }
            statMap.put(charArray[i], stat);
            if (i + 1 < charArray.length){
                char nextChar = charArray[i + 1];
                int tmpMaxLen=1;

            while (nextChar == charArray[i]) {
                stat.setTotalCount(stat.getTotalCount() + 1);
                tmpMaxLen++;
                if(tmpMaxLen>stat.getMaxLen()){
                    stat.setMaxLen(tmpMaxLen);
                }
                statMap.put(nextChar, stat);
                i++;
                if (i<charArray.length){
                    nextChar = charArray[i + 1];
                }
            }
        }
        }
        queriesStat.put(queryId++,statMap);
        return statMap;
    }
    public Map gerQueriesStatistics(){

        return null;
    }
}
