package Game;

import javax.swing.JFrame;
public class Window extends JFrame {

    public Window()
    {
        setTitle("Arachnid Guy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280,720));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void dispose(){
        this.setVisible(false);
        System.exit(0);
        //this.dispose();
    }
}
