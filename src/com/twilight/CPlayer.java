package com.twilight;

import java.util.ArrayList;

public class CPlayer {

    public CPlayer() {
        this.name = "player " + playersCnt;
        ++playersCnt;
    }

    public final String getName() {
        return name;
    }

    private static Integer playersCnt = 0;

    private String name;
}
