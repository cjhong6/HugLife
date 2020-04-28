package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Map.Entry;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    private double energyLose = 0.15;
    private double energyGain = 0.2;
    private double energyRetain = 0.5;
    private double energyGiven = 0.5;
    private double moveProbability = 0.5;
    private static final double MAX_ENERGY=2;
    private static final double MIN_ENERGY=0;



    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        if(energy==MIN_ENERGY){
          g=63;
        }else if(energy==MAX_ENERGY){
          g = 255;
        }
        return color(99, (int)(96*energy+63), 76);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = energy-energyLose;
        if(energy<MIN_ENERGY) energy=MIN_ENERGY;
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy = energy+energyGain;
        if(energy>MAX_ENERGY) energy=MAX_ENERGY;
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        double childEnergy = energy*energyGiven;
        energy=energy*energyRetain;

        return new Plip(childEnergy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> clorusDirections = new ArrayDeque<>();
        boolean anyClorus = false;

        for (Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.addFirst(entry.getKey());
            }
        }

        //no empty spaces, Plip should STAY
        if (emptyNeighbors.size()==0) {
            return new Action(Action.ActionType.STAY);
        }else if(energy>=1.0){
          return new Action(Action.ActionType.REPLICATE, emptyNeighbors.getFirst());
        }else if (neighbors.get(Direction.TOP).name().equals("clorus") && Math.random() < moveProbability) {
            anyClorus=true;
            return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
        } else if (neighbors.get(Direction.BOTTOM).name().equals("clorus") && Math.random() < moveProbability) {
            anyClorus=true;
            return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
        } else if (neighbors.get(Direction.LEFT).name().equals("clorus") && Math.random() < moveProbability) {
            anyClorus=true;
            return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
        } else if (neighbors.get(Direction.RIGHT).name().equals("clorus") && Math.random() < moveProbability) {
            anyClorus=true;
            return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
        }
        // Rule 4
        return new Action(Action.ActionType.STAY);
    }
}
