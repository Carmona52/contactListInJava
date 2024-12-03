import javax.swing.JButton;
public class CreateButtonFactory extends ButtonFactory {
    @Override
    public JButton createButton(String text) {
        return new JButton(text);
    }
}
