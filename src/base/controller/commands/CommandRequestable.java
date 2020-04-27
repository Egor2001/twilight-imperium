package base.controller.commands;

import base.controller.HierarchyController.GameObjectTarget;

public interface CommandRequestable {
    int requestNumber(String purpose);
    String requestName(String purpose);
    GameObjectTarget requestTarget(String purpose);
}
