package base.controller;

public enum CommandResponse {
    ACCEPTED("ACCEPTED"),
    DECLINED("DECLINED"),
    NEW_EVENT("NEW-EVENT"),
    END_EVENT("END-EVENT"),
    BREAK("BREAK");

    final String status;
    CommandResponse(String status) {
        this.status = status;
    }
}
