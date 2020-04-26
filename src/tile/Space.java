package tile;

import ArmyUnits.Ships.Ship;
import base.controller.HierarchyController;

class Space extends TileObject{
    public Space(){};

    public boolean could_fly_throw(Ship ship) {
        return true;
    }

    public boolean could_end_flight_in(Ship ship)  {
        return true;
    }

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
