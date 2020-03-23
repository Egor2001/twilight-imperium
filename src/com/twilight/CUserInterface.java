package com.twilight;

public class CUserInterface {

    public interface IPlayerCommand {
        void procCommand();
    }

    public interface IPlayerActionCommand extends IPlayerCommand {

    }

    public static class CPlayerActionMove implements IPlayerActionCommand {
        @Override
        public void procCommand() {

        }
    }

    public CUserInterface() {

    }

    public IPlayerActionCommand requestAction(final CPlayer player) {
        return new CPlayerActionMove();
    }

    public Boolean refresh(final CGameState gameState) {
        return true;
    }
}
