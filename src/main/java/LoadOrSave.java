import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Stack;

/**
 * This class provides methods for saving and loading a list of MyShape objects to and from a file.
 */

public class LoadOrSave {

    /**
     * Saves a stack of shapes to a file specified by the filePath parameter.
     * @param shapes the stack of shapes to be saved
     * @param lines the stack of shapes to be saved
     * @param filePath the file path to which the shapes will be saved
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void saveObjects(Stack<Shape> shapes, Stack<Line> lines, Stack<Shape> connections, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            Object[] objects = new Object[]{shapes, lines, connections};
            oos.writeObject(objects);
        }
    }

    /**
     * Loads a stack of shapes from a file specified by the filePath parameter.
     * @param filePath the file path from which the shapes will be loaded
     * @return a stack of shapes loaded from the file
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ClassNotFoundException if the class of the serialized object cannot be found
     */
    public static Object[] loadObjects(String filePath) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Object[]) ois.readObject();
        }
    }
}