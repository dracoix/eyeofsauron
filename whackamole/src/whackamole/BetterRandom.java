/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whackamole;

/**
 *
 * @author Expiscor
 */
public class BetterRandom {

    public static long rnd() {
        return mg2003(System.nanoTime() * System.currentTimeMillis());
    }

    private static long mg2003(long x) {
        x ^= x >>> 12;
        x ^= x << 25;
        x ^= x >>> 27;

        return x * 2685821657736338717L;
    }
}
