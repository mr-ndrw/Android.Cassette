package andrewtorski.global;

import android.content.Context;

/**
 * Provides various different values - constant and variables - which would be otherwise
 * inaccessible without breaking the architecture.
 */
public final class GlobalValues {

    /**
     * Current context in the application scope.
     * The main reasoning for this field here is that it provides DbOpenHelper with Context which is
     * required to instantiate DbOpenHelper and subsequently open a connection to the SQLite
     * database.
     */
    private static Context context;

    /**
     * Private default constructor which disallows creation of instances of this classes.
     */
    private GlobalValues() {
    }


    public static void setContext(Context providedContext) {
        if (providedContext == null) {
            throw new NullPointerException("context is null");
        }
        /*
            Learned lesson for future:
                Don't name parameter objects the same as the static fields of a class.

                What was wrong:
                (...) setContext(Context context){
                    context = context...
                }

                Above didn't accomplish anything, it was assigning either the static field 'context'
                to itself or the parameter 'context' to itself. Either way didn't set the static field
                'context' to reference any object whatsoever and it ended up being null.

         */
        context = providedContext;
    }


    public static Context getContext() {
        return context;
    }

}
