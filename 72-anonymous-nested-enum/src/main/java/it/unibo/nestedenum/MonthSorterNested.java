package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    
    public enum Month {
        JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30),
        MAY(31), JUNE(30), JULY(31), AUGUST(31),
        SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

        private final int days;
        
        Month(final int days){
            this.days = days;
        }
        public int getDays(){
            return this.days;
        }

        static Month fromString(final String name) {    
            Objects.requireNonNull(name);
            final String upper = name.toUpperCase(Locale.ROOT);
            Month found = null;
            for (final Month search : values()) {
                if(search.name().startsWith(upper)) {      
                    if(found != null) {
                        throw new IllegalArgumentException("incorrect input month: " + name);
                    }
                    found = search;
                }
            }
            if(found == null) {
                throw new IllegalArgumentException("no month corresponding with: "+ name);
            }
            return found;
        }
    }
    /*
    * Mesi dell'anno e numero di giorni:
    *
    *  1  Gennaio    31
    *  2  Febbraio   28 (29 negli anni bisestili)
    *  3  Marzo      31
    *  4  Aprile     30
    *  5  Maggio     31
    *  6  Giugno     30
    *  7  Luglio     31
    *  8  Agosto     31
    *  9  Settembre  30
    * 10  Ottobre    31
    * 11  Novembre   30
    * 12  Dicembre   31
    */
    private static final class SortByMonthOrder implements Comparator<String> {
        @Override 
        public int compare(final String a, final String b){
            return Month.fromString(a).compareTo(Month.fromString(b));  
        }
    }
    private static final class SortByDate implements Comparator<String> {
        @Override 
        public int compare(final String a, final String b){
            return Integer.compare(Month.fromString(a).getDays(), Month.fromString(b).getDays());   
        }
    }   

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }
}
