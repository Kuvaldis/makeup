package kuvaldis.makeup.shared.module

import com.google.inject.Guice
import com.google.inject.Injector

/**
 * User: NFadin
 * Date: 28.01.14
 * Time: 12:45
 */
class SharedGuiceHolder {

    private static Injector injector

    private static final Object sync = new Object();

    public static getInjector() {
        synchronized (sync) {
            if (!injector) {
                injector = Guice.createInjector(new SharedModule())
            }
        }
        injector
    }
}
