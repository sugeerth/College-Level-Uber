/*
 * MusicOnWebApp.java
 */

package musiconweb;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class MusicOnWebApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new MusicOnWebView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of MusicOnWebApp
     */
    public static MusicOnWebApp getApplication() {
        return Application.getInstance(MusicOnWebApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        System.gc();
        long start_time=System.currentTimeMillis();
        System.out.println(start_time);
        launch(MusicOnWebApp.class, args);
       long end_time=System.currentTimeMillis();
       long time=(end_time-start_time)/1000000000;
       System.out.println(time);

    }
}
