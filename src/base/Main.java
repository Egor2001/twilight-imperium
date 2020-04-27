package base;

import ArmyUnits.FactoryUnit;
import ArmyUnits.Ships.Flagship;
import base.controller.GameController;
import base.controller.HierarchyController;
import base.controller.HierarchyController.*;
import base.model.Army;
import base.model.Player;
import tile.Board;
import tile.Planet;
import tile.Tile;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static class Parent implements UserAcceptable {

        private Child child;

        public Parent(Child child) {
            this.child = child;
        }

        public static class Target extends GameObjectTarget {

            public Target() {
                super();
            }

            public Target(Child.Target childTarget) {
                super(childTarget);
            }
        }

        public class View implements Viewable {

            View(Viewable childView) {
                this.childView = childView;
            }

            @Override
            public void display(Writer writer) throws IOException {
                writer.write("Hello from parent " + Parent.this.toString() + "!\n");

                if (childView != null) {
                    childView.display(writer);
                }
            }

            @Override
            public String toString(String s) {
                return null;
            }

            Viewable childView;
        }

        @Override
        public Viewable getView(UserAcceptable parent) {
            return new View(child.getView(this));
        }

        @Override
        public Viewable getView(UserAcceptable parent, GameObjectTarget target) {
            if (target instanceof Target) {
                if (target.getNext() != null) {
                    return child.getView(this, target.getNext());
                }

                return new View(child.getView(this));
            }

            return null;
        }

        @Override
        public Object getObject(GameObjectTarget target) {
            if (target instanceof Target) {
                if (target.getNext() == null) {
                    return this;
                } else {
                    return child.getObject(target.getNext());
                }
            }

            return null;
        }
    }

    public static class Child implements UserAcceptable {

        public static class Target extends GameObjectTarget {

            public Target() {
                super();
            }

            public Target(GameObjectTarget next) {
                super(next);
            }
        }

        public class View implements Viewable {

            View() {
            }

            @Override
            public void display(Writer writer) throws IOException {
                writer.write("Hello from child " + Child.this.toString() + "!\n");
            }

            @Override
            public String toString(String s) {
                return null;
            }
        }

        @Override
        public Viewable getView(UserAcceptable parent) {
            return new View();
        }

        @Override
        public Viewable getView(UserAcceptable parent, GameObjectTarget target) {
            if (target instanceof Target && target.getNext() == null) {
                return new View();
            }

            return null;
        }

        @Override
        public Object getObject(GameObjectTarget target) {
            if (target instanceof Target && target.getNext() == null) {
                return this;
            }

            return null;
        }
    }

    public static void testTarget() {
        GameObjectTarget simpleTarget = HierarchyController.parseTarget("parent");
        GameObjectTarget complexTarget = HierarchyController.parseTarget("parent.child");

        Parent hierarchy = new Parent(new Child());

        System.out.println(hierarchy.getObject(simpleTarget).toString());
        System.out.println(hierarchy.getObject(complexTarget).toString());

        PrintWriter writer = new PrintWriter(System.out);

        Viewable simpleView = hierarchy.getView(null, simpleTarget);
        Viewable complexView = hierarchy.getView(null, complexTarget);

        try {
            writer.write("parent view:\n");
            simpleView.display(writer);

            writer.write("parent.child view:\n");
            complexView.display(writer);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

        writer.flush();
    }

    public static void testArmy() {
        GameObjectTarget target = HierarchyController.parseTarget("Army.Ship.1");

        Army arm = new Army();
        FactoryUnit SFR = new FactoryUnit("Race1");
        PrintWriter writer = new PrintWriter(System.out);

        try {
            arm.getView(null, target.getNext()).display(writer);
        }
        catch (IOException e) {
            e.printStackTrace();
            writer.write("biba");
        }

        Player user = new Player("Boba", "Race1");
        user.addUnit("Flagship");
        user.addUnit("PDS");
        user.addUnit("Flagship");

        try {
            user.getView(null).display(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.flush();
    }

    public static void main(String[] args) {
        //testArmy();
        testTarget();

        GameController gameController = GameController.getInstance();

        gameController.gameInit();
        gameController.gameLoop();
        gameController.gameLoop();
    }
}
