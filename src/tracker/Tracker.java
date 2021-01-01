package tracker;


public class Tracker {
    private static Tracker instance = null;


    private Tracker() {
    }

    public static Tracker getInstance() { //singleton pattern
        if (instance == null)
                instance = new Tracker();

        return instance;
    }

}