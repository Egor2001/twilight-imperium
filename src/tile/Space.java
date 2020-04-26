package tile;

import base.controller.HierarchyController;

class Space extends TileObject{
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


    static class Target extends HierarchyController.GameObjectTarget{
        public Target(HierarchyController.GameObjectTarget next_tar) {
        }

        public HierarchyController.GameObjectTarget getNext() {
            return null;
        }
    }
};
