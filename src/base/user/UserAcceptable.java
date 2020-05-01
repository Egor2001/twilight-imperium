package base.user;

import base.view.Viewable;

public interface UserAcceptable {
    Viewable getView(UserAcceptable parent);
    Viewable getView(UserAcceptable parent, GameObjectTarget target);
    Object getObject(GameObjectTarget target) throws Exception;
}
