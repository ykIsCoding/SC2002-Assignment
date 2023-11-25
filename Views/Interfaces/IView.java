package Views.Interfaces;

/**
 * This is the interface for all the View classes to ensure that the view classes have the functions
 */
public interface IView {
    /**
     * The render function outputs what is shown to the user and also sets up the business logic of getting an input from the user.
     */
    void render();

    /**
     * The handle input function takes in an integer based on what the users enter and controls what the application does based on the choice
     * @param selection is the integer input by the user
     */
    void handleInput(int selection);
}
