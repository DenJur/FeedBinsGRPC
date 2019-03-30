package controller.interfaces;

import u1467085.feedbincontroller.ControllerBinStatusUpdate;

public interface ClientUpdater {
    void NotifyClients(ControllerBinStatusUpdate update);
}
