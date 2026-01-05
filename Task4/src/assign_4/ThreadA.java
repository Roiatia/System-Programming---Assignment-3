package assign_4;

public class ThreadA extends Thread {
private final Control ctrl;

public ThreadA(Control ctrl) {
    this.ctrl = ctrl;
}

private void doA() {
    System.out.println("A");
}

@Override
    public void run() {
    while(true){
        synchronized (ctrl) {
            while (ctrl.states != State.States.A) {
                try {
                    ctrl.wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
            doA();
            synchronized (ctrl) {
                ctrl.aDone++;
                if(ctrl.aDone == 3) {
                    ctrl.state = State.B;
                    ctrl.bSwitchToC =false;
                }
                ctrl.notifyAll();
            }
        }
    }
}



}
