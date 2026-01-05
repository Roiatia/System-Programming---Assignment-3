package assign_4;

public class DtoB extends Thread {
private final Control ctrl;

public DtoB(Control ctrl) {
    this.ctrl = ctrl;
}

@Override
    public void run() {
    while(true) {
        synchronized (ctrl) {
            while (!(ctrl.states == State.D && ctrl.dSwitchToB)) {
                try {
                    ctrl.wait();
                } catch (InterruptedException e) {
                    return;
                }
                ctrl.states = State.B;
                ctrl.bSwitchToC = false;
                ctrl.dSwitchToB = false;
                ctrl.notifyAll();
            }
            Thread.yield();
        }
    }

}

}
