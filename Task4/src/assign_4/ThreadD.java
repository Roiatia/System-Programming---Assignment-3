package assign_4;

public class ThreadD extends Thread {
private final Control  ctrl;

public ThreadD(Control ctrl) {
    this.ctrl = ctrl;
}

private void doD() {
    System.out.println("D");
}

@Override
    public void run() {
    while(true) {
        synchronized (ctrl) {
            while (ctrl.states != State.States.D) {
                try {
                    ctrl.wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
            doD();
            synchronized (ctrl) {
                ctrl.dSwitchToB = true;
                ctrl.notifyAll();
            }
            Thread.yield();
        }
    }
}
}
