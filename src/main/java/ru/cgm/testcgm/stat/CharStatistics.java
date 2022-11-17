package ru.cgm.testcgm.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CharStatistics {
    private int totalCount;
    private int maxLen;
    @Override
    public boolean equals(Object other){
        if (other == this) {
             return true;
        }
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        if (totalCount==((CharStatistics) other).totalCount
        && maxLen==((CharStatistics) other).maxLen
        ){
            return true;
        }else {
            return false;
        }
    }
}
