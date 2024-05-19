package Game;

public class Game_Launcher {
    public Window window;
    //public static SQL db;

    public Game_Launcher(){
        window = new Window();

    }

    public static Game_Launcher Instance;



    public static void main(String[] args)
    {
        Instance = new Game_Launcher();

    }

}
