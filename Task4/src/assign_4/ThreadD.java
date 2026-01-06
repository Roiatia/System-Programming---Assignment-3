package assign_4;

public class ThreadD extends Thread {
    private final Control ctrl;

    public ThreadD(Control ctrl) {
        this.ctrl = ctrl;
        setName("D");
    }

    private void doD() {
        System.out.print("D");
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ctrl) {
                while (ctrl.states != assign_4.State.D) {
                    try {
                        ctrl.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                doD();

                // D always moves back to B (so A is only the first phase)
                ctrl.states = assign_4.State.B;

                ctrl.notifyAll();
            }
        }
    }
}
