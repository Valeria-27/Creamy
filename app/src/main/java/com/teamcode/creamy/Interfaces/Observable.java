package com.teamcode.creamy.Interfaces;

import java.util.ArrayList;

public abstract class Observable {
    private ArrayList<IObserver> suscribers = new ArrayList<>();

    public void suscribe(IObserver observer) {
        suscribers.add(observer);
    }

    public void notifySuscribers(Object value) {
        for (IObserver suscriber: getSuscribers()) {
            suscriber.update(value);
        }
    }

//    public void notifySuscribers() {
//        for (IObserver suscriber: getSuscribers()) {
//            suscriber.update();
//        }
//    }

    public ArrayList<IObserver> getSuscribers() {
        return suscribers;
    }

    public void setSuscribers(ArrayList<IObserver> suscribers) {
        this.suscribers = suscribers;
    }
}
