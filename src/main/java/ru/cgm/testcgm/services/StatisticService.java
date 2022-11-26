package ru.cgm.testcgm.services;

import org.springframework.stereotype.Service;
import ru.cgm.testcgm.stat.CharStatistics;
import ru.cgm.testcgm.stat.QueriesStatistics;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class StatisticService {
    private final List<Map<Character,CharStatistics>> queriesStat=Collections.synchronizedList(new ArrayList<>());

    public Map<Character, CharStatistics> getCharsStatistics(@NotNull String stringForAnalysis){
        Map<Character,CharStatistics> charStatMap = new HashMap<>();
        char prev=0;
        int maxLen=1;
        CharStatistics stat = null;
        for(char c:stringForAnalysis.toCharArray()){
            if (prev!=c){
                maxLen=1;
                if (stat!=null){
                    charStatMap.put(prev,stat);
                }
                stat = charStatMap.get(c);
            }
            if (stat == null) {
                stat = new CharStatistics(1, 1);
            } else {
                stat.setTotalCount(stat.getTotalCount() + 1);
                if(c==prev){
                    maxLen++;
                    if (maxLen > stat.getMaxLen()){
                        stat.setMaxLen(maxLen);
                    }
                }
            }
            prev = c;

        }
        charStatMap.put(prev,stat);
        queriesStat.add(charStatMap);
        return charStatMap;
    }

        public Map<Character, QueriesStatistics> gerQueriesStatistics(){
        Map<Character, QueriesStatistics> totalStat = new HashMap<>();
        Map<Character,CharStatistics> iMap=null;
        for(int i=0;i<queriesStat.size();i++)

        {
            iMap=queriesStat.get(i);
            for(Character iChar:iMap.keySet()){
                CharStatistics iCharStatistics = iMap.get(iChar);
                QueriesStatistics stat = totalStat.get(iChar);
                if(stat==null){
                    stat=new QueriesStatistics(1,iCharStatistics.getTotalCount(),iCharStatistics.getMaxLen());
                }else{
                    stat.setTotalQueries(stat.getTotalQueries()+1);
                    // not average yet!!!! only sum!!
                    stat.setAverageInStrings(stat.getAverageInStrings()+iCharStatistics.getTotalCount());
                    stat.setAverageMaxSequence(stat.getAverageMaxSequence()+iCharStatistics.getMaxLen());
                }
                totalStat.put(iChar,stat);
            }
        }
        //Averaging
        for (Character ch:totalStat.keySet()) {
            QueriesStatistics st = totalStat.get(ch);
            st.setAverageInStrings(st.getAverageInStrings()/st.getTotalQueries());
            st.setAverageMaxSequence(st.getAverageMaxSequence()/st.getTotalQueries());
            totalStat.put(ch,st);
        }
         return totalStat;
    }
}
