package andrewtorski.casette.app.presenter;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
public interface Presenter {
    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();
}
