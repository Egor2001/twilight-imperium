package base.controller;

public enum CommandResponse {
    ACCEPTED("ACCEPTED"),
    DECLINED("DECLINED"),
    END_EVENT("END-EVENT"),
    BREAK("BREAK"),
    END_GAME("END-GAME");

    final String status;
    CommandResponse(String status) {
        this.status = status;
    }
}
