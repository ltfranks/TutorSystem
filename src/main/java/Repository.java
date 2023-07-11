import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.Observable;

/**
 * This is the repository. It stores common variables held by other classes and uses a Singleton and an Observer Pattern
 *
 * @author Anthony Colin, Shiv Panchal, Luke Fanguna, Nathan Choi, Reza Mousakhani, Luke Franks
 */
public class Repository extends Observable implements RepositoryInterface, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String filePath = getDesktopPath() + "diagram.bin";
    private int loopsProgress = 40, conditionalProgress = 70, metricsProgress = 20, diagramsProgress = 0;
    private int overallProgress = (loopsProgress+conditionalProgress+metricsProgress+diagramsProgress)/5;
    private String shape;
    private Stack<Shape> shapes = new Stack<Shape>();
    private Line line;
    private Stack<Line> lines = new Stack<Line>();
    private Stack<Shape> connection = new Stack<Shape>();
    private final Stack<Object> all = new Stack<>();
    private static Repository repo;
    private String textbox;
    private JTextPane chatTextArea;
    private JTextField messageTextField;
    private int question = 1;
    private String username = "acolinhe";
    private Map<String,List<String>> userAns=new HashMap<>();
    private Map<String, Map<String, Integer>> flowchartAnswers = new HashMap<>();
    private String gptkey;
    private String mapsKey;
    private String weatherKey;
    private String youtubeKey;
    private JPanel mainPanel;
    private CardLayout cards;
    private JComboBox<String> codeOrFlow;
    private JComboBox<String> questions;
    /**
     * Allows Repository to not be changed out of scope
     */
    private Repository() {
        textbox = "";
    }

    /**
     * Gets desktop path for the file to be saved in
     * @return desktop directory
     */
    private static String getDesktopPath() {
        String home = System.getProperty("user.home");
        return home + File.separator + "Desktop" + File.separator;
    }

    public String getFilePath(){
        return filePath;
    }

    /**
     * Allows us to get the instance of the Respository
     *
     * @return instance of Repository
     */
    public static Repository getR() {
        if (repo == null) {
            repo = new Repository();
        }
        return repo;
    }

    /**
     * SetLoadShapes takes the shapes from the loaded file
     * and sets the shapes stack above with those shapes
     *
     * @param: stack of objects to be loaded
     */
    public void setLoadShapes(Stack<Shape> newShapes) {
        shapes = newShapes;
        setChanged();
        notifyObservers("Load");
    }

    public void setLoadLines(Stack<Line> newLines) {
        lines = newLines;
        setChanged();
        notifyObservers("Load");
    }

    public void setLoadConnections(Stack<Shape> newConnections){
        connection = newConnections;
        setChanged();
        notifyObservers("Load");
    }

    /**
     * Sets the shape field to the value passed in
     *
     * @param shape to be used
     */
    public void setShape(String shape) {
        this.shape = shape;
    }

    /**
     * Adds a shape to the stack
     *
     * @param shape
     */
    public void addShape(Shape shape) {
        shapes.push(shape);
        setChanged();
        notifyObservers("Shape");
    }

    /**
     * Sets the line field to the value passed in
     *
     * @param line
     */
    public void setLine(Line line) {
        this.line = line;
    }

    /**
     * Adds the line value to the stack
     *
     * @param line
     */
    public void addLine(Line line) {
        repo.incrementFlowchartAns("q1", "Line");
        lines.push(line);
        setChanged();
        notifyObservers("Line");

    }

    /**
     * Sets the textbox of the shape to the value passed in
     *
     * @param textbox
     */
    public void setTextbox(String textbox) {
        this.textbox = textbox;
    }

    /**
     * Adds a shape to the connection stack that helps make lines
     *
     * @param shape
     */
    public void addC(Shape shape) {
        connection.push(shape);
    }

    /**
     * Gets the shape stack
     *
     * @return stack of shapes
     */
    public Stack<Shape> getShapes() {
        return shapes;
    }

    /**
     * Gets the stack of lines
     *
     * @return stack of lines
     */
    public Stack<Line> getLines() {
        return lines;
    }

    public Stack<Shape> getConnection(){return connection;}

    /**
     * Gets the connection stack that helps build lines
     *
     * @return stack of shapes
     */
    public Stack<Shape> getC() {
        return connection;
    }

    /**
     * Clears the connection stack
     */
    public void clearC() {
        connection.clear();
    }

    /**
     * Gets the Textbox within a shape
     *
     * @return textbox of a shape
     */
    public String getTextbox() {
        return textbox;
    }


    /**
     * Gets the shape stored
     *
     * @return shape object stored
     */
    public String getShape() {
        return shape;
    }

    /**
     * Puts all the objects into one stack
     */
    public void setAll() {
        for (Object object : shapes) {
            all.push(object);
        }
        for (Object object : connection) {
            all.push(object);
        }
        for (Object object : lines) {
            all.push(object);
        }
    }

    /**
     * Puts all objects into all stack
     *
     * @return stack of objects
     */
    public Stack<Object> getAll() {
        return all;
    }

    /**
     * Sets the chat text area for displaying chat messages.
     */
    public void setChatTextArea(){
        this.chatTextArea = new JTextPane();
    }
    /**
     * Sets the message text field for entering chat messages.
     */
    public void setMessageTextField(){
        this.messageTextField = new JTextField();
    }
    /**
     * Returns the chat text area.
     * @return The chat text area.
     */
    public JTextPane getChatTextArea(){
        return repo.chatTextArea;
    }
    /**
     * Returns the message text field.
     * @return The message text field.
     */
    public JTextField getMessageTextField(){
        return repo.messageTextField;
    }
    /**
     * Sets the username.
     * @param username The username to set.
     */
    public void setUsername(String username) {this.username = username;}
    /**
     * Returns the username.
     * @return The username.
     */
    public String getUsername() {return username;};
    /**
     * Returns the progress in the loops category.
     * @return The loops progress.
     */
    public int getLoopsProgress(){
        return loopsProgress;
    }
    /**
     * Returns the progress in the conditional category.
     * @return The conditional progress.
     */
    public int getConditionalProgress(){
        return conditionalProgress;
    }
    /**
     * Returns the progress in the metrics category.
     * @return The metrics progress.
     */
    public int getMetricsProgress(){
        return metricsProgress;
    }
    /**
     * Returns the progress in the diagrams category.
     * @return The diagrams progress.
     */
    public int getDiagramsProgress(){
        return diagramsProgress;
    }
    /**
     * Decrements the current question number.
     * If the question number is less than 1, it remains unchanged.
     */
    public void decQuestion()
    {
        if (question >6)
        {
            question = 6;
        }
        if (question != 1)
            question -= 1;

    }
    /**
     * Increments the current question number.
     */
    public void incQuestion() {
            question+=1;
    }
    /**
     * Returns the current question number.
     * @return The current question number.
     */
    public int getQuestion() {return question;}
    /**
     * Returns the user's answers.
     * @return The user's answers.
     */
    public Map<String, List<String>> getUserAns() {
        return userAns;
    }
    /**
     * Sets the user's answers for a specific key.
     * @param key The key for the user's answers.
     * @param value The user's answers.
     */
    public void setUserAns(String key, List<String> value) {
        userAns.put(key,value);
        notifyObservers("submit2");
        setChanged();
    }
    /**
     * Increments the count of answers for a specific outer key and inner key in the flowchartAnswers map.
     * If the outer key or inner key does not exist, they are added to the map.
     * @param outerKey The outer key.
     * @param innerKey The inner key.
     */

    public void incrementFlowchartAns(String outerKey, String innerKey) {
        if (flowchartAnswers.containsKey(outerKey)) {
            Map<String, Integer> innerMap = flowchartAnswers.get(outerKey);

            if (innerMap.containsKey(innerKey)) {
                int incrementedValue = innerMap.get(innerKey) + 1;
                innerMap.put(innerKey, incrementedValue);
            } else {
                innerMap.put(innerKey, 1);
            }
        } else {
            Map<String, Integer> innerMap = new HashMap<>();
            innerMap.put(innerKey, 1);
            flowchartAnswers.put(outerKey, innerMap);
        }
    }

    /**
     * Returns the flowchart answers map.
     * @return The flowchart answers map.
     */
    public Map<String,Map<String, Integer>> getFlowchartAnswers() {
        return flowchartAnswers;
    }
    /**
     * Returns the GPT key.
     * @return The GPT key.
     */
    public String getGptkey() {
        return gptkey;
    }
    /**
     * Sets the GPT key.
     * @param gptkey The GPT key to set.
     */
    public void setGptkey(String gptkey) {
        this.gptkey = gptkey;
    }
    /**
     * Returns the maps key.
     * @return The maps key.
     */
    public String getMapsKey() {
        return mapsKey;
    }
    /**
     * Sets the maps key.
     * @param mapsKey The maps key to set.
     */
    public void setMapsKey(String mapsKey) {
        this.mapsKey = mapsKey;
    }
    /**
     * Returns the weather key.
     * @return The weather key.
     */
    public String getWeatherKey() {
        return weatherKey;
    }
    /**
     * Sets the weather key.
     * @param weatherKey The weather key to set.
     */
    public void setWeatherKey(String weatherKey) {
        this.weatherKey = weatherKey;
    }
    /**
     * Returns the YouTube key.
     * @return The YouTube key.
     */
    public String getYoutubeKey() {
        return youtubeKey;
    }
    /**
     * Sets the YouTube key.
     * @param youtubeKey The YouTube key to set.
     */
    public void setYoutubeKey(String youtubeKey) {
        this.youtubeKey = youtubeKey;
    }
    /**
     * Returns the overall progress.
     * @return The overall progress.
     */
    public int getOverallProgress(){
        return overallProgress;
    }
    /**
     * Changes the panel in the main panel using the specified string identifier.
     * @param s The string identifier of the panel to show.
     */
    @Override
    public void changePanel(String s) {
        cards.show(mainPanel,s);
    }
    /**
     * Sets the card layout for managing panels.
     * @param cardLayout The CardLayout to set.
     */
    @Override
    public void setCardLayout(CardLayout cardLayout) {
        cards = cardLayout;
    }
    /**
     * Returns the card layout for managing panels.
     * @return The CardLayout.
     */
    @Override
    public CardLayout getCardLayout() {
        return cards;
    }
    /**
     * Returns the main panel.
     * @return The main panel.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }
    /**
     * Sets the main panel.
     * @param mainPanel The main panel to set.
     */
    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    /**
     * Returns the code or flow JComboBox.
     * @return The code or flow JComboBox.
     */
    public JComboBox<String> getCodeOrFlow() {
        return codeOrFlow;
    }
    /**
     * Sets the question box JComboBox.
     * @param questions The question box JComboBox to set.
     */
    @Override
    public void setQuestionBox(JComboBox<String> questions) {
        this.questions = questions;
    }
    /**
     * Changes the question box options based on the selected option.
     * @param option The selected option ("Flowchart to Code" or "Code to Flowchart").
     */
    public void changeQuestionBox(String option)
    {
        QAController QAC = new QAController(new AnswerController());
        String[] codeQuestions = {
                "Code Question 1", "Code Question 2", "Code Question 3",
                "Code Question 4", "Code Question 5"};
        String[] flowQuestions = {"Flowchart Question 1",
                "Flowchart Question 2", "Flowchart Question 3"};
        switch (option) {
            case "Flowchart to Code":
                questions.setModel(new DefaultComboBoxModel<>(flowQuestions));
                questions.addItemListener(QAC);

                break;
            case "Code to Flowchart":
                questions.setModel(new DefaultComboBoxModel<>(codeQuestions));
                questions.addItemListener(QAC);

                break;
        }
    }

    /**
     * Sets the code or flow JComboBox.
     * @param codeOrFlow The code or flow JComboBox to set.
     */
    public void setCodeOrFlow(JComboBox codeOrFlow) {
        this.codeOrFlow = codeOrFlow;
    }
}