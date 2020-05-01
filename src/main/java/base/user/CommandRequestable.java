package base.user;

public interface CommandRequestable {
    int requestNumber(String purpose);
    String requestName(String purpose);
    GameObjectTarget requestTarget(String purpose);
    void reportError(String error);
}
