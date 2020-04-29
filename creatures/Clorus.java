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

public class Clorus extends Creature{
  private static final int R=34;
  private static final int G=0;
  private static final int B=231;

  public Clorus(double e){
    super("clorus");
    energy=e;
  }

  public Clorus(){
    this(1);
  }

  public Color color() {
      return color(R, G, B);
  }

  public void attack(Creature c) {
    energy+=c.energy();
  }

  public void move() {
    energy-=0.03;
  }

  public void stay() {
    energy-=0.01;
  }

  public Clorus replicate() {
    double newEnergy = energy*0.5;
    energy*=0.5;
    return new Clorus(newEnergy);
  }

  public Action chooseAction(Map<Direction,Occupant> neighbors){
    Deque<Direction> emptySpace = new ArrayDeque();
    Deque<Direction> plipDirection = new ArrayDeque();
    for (Entry<Direction, Occupant> entry : neighbors.entrySet()) {
        if (entry.getValue().name().equals("empty")) {
            emptySpace.addFirst(entry.getKey());
        }
        if (entry.getValue().name().equals("plip")) {
            plipDirection.addFirst(entry.getKey());
        }
    }

    if(emptySpace.size()==0){
      return new Action(Action.ActionType.STAY);
    }else if (neighbors.get(Direction.TOP).name().equals("plip") ||
              neighbors.get(Direction.BOTTOM).name().equals("plip") ||
              neighbors.get(Direction.LEFT).name().equals("plip") ||
              neighbors.get(Direction.RIGHT).name().equals("plip"))
    {
      return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(plipDirection));
    }else if(energy>=1){
      return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptySpace));
    }else{
      return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptySpace));
    }
  }
}
