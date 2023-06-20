package es2_groupbf;

public class App {
    public static void main(String[] args) {
        try {
            AppContext.init();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
