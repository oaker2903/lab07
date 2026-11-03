package it.unibo.functional.api;

/**
 * This class models a function of a single parameter as an Object.
 *
 * @param <I> the input type
 * @param <O> the output type
 */
public interface Function<I, O> {

    /**
     * Calls the function.
     *
     * @param input the input of the function
     * @return the result of the function evaluation on the provided input
     */
    O call(I input);

    /**
     * Returns the identity function, which returns the same object that is provided as input.
     *
     * @param <T> the input (and output) type of the function
     * @return A new {@code Function} that implements the identity function with the provided type
     */

    /*
    Si implementi il metodo `identity()`, che deve restituire la funzione identià
    (ossia, che restituisce l'input passato in ingresso senza modifica alcuna)
    tramite una classe anonima.
    devi farci un return di una Function che restituisce gli stessi parametri
    */
    static <T> Function<T, T> identity() {  //una funziona che prende in ingresso un T e restituisce un T --> sotto forma di classe anonima, new Function<T,T> { ... }
        return new Function<T,T>() {
                public T call(final T input) {
                    return input;
            }
        };
    }
}
