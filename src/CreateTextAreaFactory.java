import javax.swing.JTextArea;
public class CreateTextAreaFactory extends TextAreaFactory {
    @Override
    public JTextArea createTextArea(int rows, int cols) {
        return new JTextArea(rows, cols);
    }
}
