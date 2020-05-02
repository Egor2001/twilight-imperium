package base.user;

import base.view.Viewable;

public interface CommandRequestable {
    int requestNumber(String purpose);
    String requestName(String purpose);
    GameObjectTarget requestTarget(String purpose);
    void reportError(String error);
    void displayView(Viewable view);
}
