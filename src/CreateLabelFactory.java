import javax.swing.JLabel;
public class CreateLabelFactory extends LabelFactory {
    @Override
    public JLabel createLabel(String text) {
        return new JLabel(text);
    }
}
