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
        //metodo data una stringa di testo, restituisce il `Month` che meglio la rappresenta
        Month fromString(final String name){    //dato un name che si avvicina ad un nome di un mese, cercare di ritornare quel mese
            Objects.requireNonNull(name);
            //lavorare con stringhe
            //primo rendo tutto maiuscolo

            final String upper = name.toUpperCase(Locale.ROOT);
            //ora ho il mese in maiuscolo e posso confrontare con gli enum
            Month found = null;
            for (final Month search : values()) {
                if(search.name().startsWith(upper)) {
                    if(found != null) {
                        throw new IllegalArgumentException("incorrect input month: " + name);
                    }
                    found = search;
                }
            }
            if(found == null){
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

    //creazione due classi innestate `SortByMonthOrder` e `SortByDate`


    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }
}
