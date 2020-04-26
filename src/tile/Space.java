package tile;

import base.controller.HierarchyController;

public class Space extends TileObject{
    Space(){};

    @Override
    public HierarchyController.Viewable getView(HierarchyController.UserAcceptable parent) {
        return null;
    }

    @Override
    public HierarchyController.Viewable getView(HierarchyController.UserAcceptable parent, HierarchyController.GameObjectTarget target) {
        return null;
    }

    @Override
    public Object getObject(HierarchyController.GameObjectTarget target) throws Exception {
        if (target == null)
        {
            return this;
        }
        else
        {
            throw new Exception("Space has no child");
        }
    }


    public static class Target extends HierarchyController.GameObjectTarget{
        public Target() {
            super();
        }

        public Target(HierarchyController.GameObjectTarget next) {
            super(next);
        }
    }
};
