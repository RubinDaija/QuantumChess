import javax.swing.*;
/*getAboutMenu should be here in the class. */
public class HelpMenu extends JPanel {
    menuPanel mp;
    public HelpMenu(){
        mp = new menuPanel();
        this.setVisible(true);

    }
    public static void main(String []args){
        new HelpMenu();
    }

}
