import control.AbstractController;
import control.DesktopAppController;

public class Main {

    public static void main(String[] args) {
        AbstractController controller = new DesktopAppController();
        controller.run();
    }
}
