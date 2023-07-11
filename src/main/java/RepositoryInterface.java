import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public interface RepositoryInterface
{

    /**
     * SetLoadShapes takes the shapes from the loaded file
     * and sets the shapes stack above with those shapes
     *
     * @param: stack of objects to be loaded
     */
    public void setLoadShapes(Stack<Shape> newShapes);
    public void setLoadConnections(Stack<Shape> newConnections);

    /**
     * Sets the shape field to the value passed in
     *
     * @param shape to be used
     */
    public void setShape(String shape);

    /**
     * Adds a shape to the stack
     *
     * @param shape
     */
    public void addShape(Shape shape);

    /**
     * Sets the line field to the value passed in
     *
     * @param line
     */
    public void setLine(Line line);
    /**
     * Adds the line value to the stack
     *
     * @param line
     */
    public void addLine(Line line);

    /**
     * Sets the textbox of the shape to the value passed in
     *
     * @param textbox
     */
    public void setTextbox(String textbox);
    /**
     * Adds a shape to the connection stack that helps make lines
     *
     * @param shape
     */
    public void addC(Shape shape);

    /**
     * Gets the shape stack
     *
     * @return stack of shapes
     */
    public Stack<Shape> getShapes();

    /**
     * Gets the stack of lines
     *
     * @return stack of lines
     */
    public Stack<Line> getLines();
    public Stack<Shape> getConnection();

    /**
     * Gets the connection stack that helps build lines
     *
     * @return stack of shapes
     */
    public Stack<Shape> getC();

    /**
     * Clears the connection stack
     */
    public void clearC();

    /**
     * Gets the Textbox within a shape
     *
     * @return textbox of a shape
     */
    public String getTextbox();


    /**
     * Gets the shape stored
     *
     * @return shape object stored
     */
    public String getShape();

    /**
     * Puts all the objects into one stack
     */
    public void setAll();

    /**
     * Puts all objects into all stack
     *
     * @return stack of objects
     */
    public Stack<Object> getAll();

    /**
     * Retrieves the file path.
     *
     * @return The file path as a String.
     */
    String getFilePath();

    /**
     * Sets the loaded lines in the stack.
     *
     * @param loadedLines The stack of loaded lines.
     */
    void setLoadLines(Stack<Line> loadedLines);

    /**
     * Sets the chat text area.
     */
    void setChatTextArea();
    /**
     * Retrieves the chat text area.
     *
     * @return The chat text area as a JTextPane.
     */

    JTextPane getChatTextArea();

    /**
     * Sets the message text field.
     */
    void setMessageTextField();

    /**
     * Retrieves the message text field.
     *
     * @return The message text field as a JTextField.
     */
    JTextField getMessageTextField();
    /**
     * Increments the flowchart answer for the given outer and inner keys.
     *
     * @param outerKey The outer key.
     * @param innerKey The inner key.
     */
    void incrementFlowchartAns(String outerKey, String innerKey);
    /**
     * Retrieves the flowchart answers.
     *
     * @return The flowchart answers as a map of string keys to maps of string keys and integer values.
     */
    Map<String,Map<String, Integer>> getFlowchartAnswers();
    /**
     * Sets the username.
     *
     * @param username The username as a String.
     */
    void setUsername(String username);
    /**
     * Retrieves the username.
     *
     * @return The username as a String.
     */
    String getUsername();
    /**
     * Decreases the current question count.
     */
    public void decQuestion();
    /**
     * Increases the current question count.
     */
    public void incQuestion();
    /**
     * Retrieves the current question count.
     *
     * @return The current question count as an integer.
     */
    public int getQuestion();
    /**
     * Retrieves the user answers.
     *
     * @return The user answers as a map of string keys to lists of string values.
     */
    public Map<String, List<String>> getUserAns();
    /**
     * Sets the user answers for the specified key.
     *
     * @param key   The key for the user answers.
     * @param value The list of user answers.
     */
    public void setUserAns(String key, List<String> value);
    /**
     * Sets the main panel.
     *
     * @param mainPanel The main panel as a JPanel.
     */
    void setMainPanel(JPanel mainPanel);
    /**
     * Retrieves the main panel.
     *
     * @return The main panel as a JPanel.
     */
    JPanel getMainPanel();
    /**
     * Retrieves the overall progress.
     *
     * @return The overall progress as an integer.
     */
    int getOverallProgress();
    /**
     * Changes the panel to the specified string.
     *
     * @param s The name of the panel to be changed to.
     */
    void changePanel(String s);
    /**
     * Sets the CardLayout for the panel.
     *
     * @param cardLayout The CardLayout to be set.
     */
    void setCardLayout(CardLayout cardLayout);
    /**
     * Retrieves the CardLayout of the panel.
     *
     * @return The CardLayout of the panel.
     */
    CardLayout getCardLayout();
    /**
     * Retrieves the code or flow selection box.
     *
     * @return The code or flow selection box as a JComboBox of strings.
     */
    JComboBox<String> getCodeOrFlow();
    /**
     * Sets the code or flow selection box.
     *
     * @param codeOrFlow The code or flow selection box as a JComboBox of strings.
     */
    void setCodeOrFlow(JComboBox<String> codeOrFlow);
    /**
     * Sets the question box.
     *
     * @param questions The question box as a JComboBox of strings.
     */
    void setQuestionBox(JComboBox<String> questions);
    /**
     * Changes the question box based on the specified action.
     *
     * @param action The action to be performed on the question box.
     */
    void changeQuestionBox(String action);

}