package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    /**
         * Configures the policy used to filter out the elements to iterate.
         * @param filter the predicate used to filter out the elements in the collection during the iteration.
         */
        private final T[] elements;

        private Predicate<T> filter;

        public IterableWithPolicyImpl(final T[] elements, final Predicate<T> predicate) {        //costrutte con filter
           this.elements = Arrays.copyOf(elements, elements.length);
           this.filter = predicate;
        }

        public IterableWithPolicyImpl( final T[] elements ){        //costruttore con copia difensiva
            this(
                elements,
                new Predicate<T>(){
                    @Override 
                    public boolean test(final T elem){
                        return true;        //modificato
                    }
                }
            );
        }

        public void setIterationPolicy(Predicate<T> filter){
            this.filter = filter;
        }

        @Override
        public Iterator<T> iterator() {     //creato iteratore custom sotto nella classe inner, da richiamare poi nel test
            return new IteratorWithPolicy();
        }

        //ora faccio la inner class
        private class IteratorWithPolicy implements Iterator<T>{

            private int current = 0;    //parte da inizio

            @Override 
            public boolean hasNext(){
                while (current < elements.length && !filter.test(elements[current])) {
                    current++;
                }
                return current < elements.length;
            }

            @Override 
            public T next(){
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                return elements[current++];
            }
        }
}
