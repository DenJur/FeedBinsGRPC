package controller.interfaces;

import u1467085.feedbincontroller.ControllerBinStatusUpdate;

/**
 * Interface that allows to notify client about any bin state changes
 */
public interface ClientUpdater {
    void NotifyClients(ControllerBinStatusUpdate update);
}
