package assign_4;

public class ThreadD extends Thread {
    private final Control ctrl;

    public ThreadD(Control ctrl) {
        this.ctrl = ctrl;
        setName("D");
    }

    private void doD() {
        System.out.println("D");
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ctrl) {
                while (ctrl.state != State.D) {
                    try {
                        ctrl.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                doD();

                // D always moves back to B (so A is only the first phase)
                ctrl.state = State.B;

                ctrl.notifyAll();
            }
        }
    }
}
