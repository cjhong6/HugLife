package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus{
  @Test
  public void testBasics(){
    Clorus c = new Clorus(2);
    Plip p = new Plip(8);

    assertEquals(2,c.energy(),0.01);

    assertEquals(new Color(34,0,231), c.color());

    c.move();
    assertEquals(1.97,c.energy(),0.01);

    c.stay();
    assertEquals(1.96,c.energy(),0.01);

    c.attack(p);
    assertEquals(9.96,c.energy(),0.01);
  }

  @Test
  public void testReplicate(){
    Clorus parent = new Clorus(100);
    Clorus child = parent.replicate();
    assertNotSame(child,parent);
    assertEquals(50, child.energy(), 0.01);
    assertEquals(50, parent.energy(), 0.01);
  }

  @Test
  public void chooseAction(){
    // No empty adjacent spaces; stay.
    Clorus c = new Clorus(6);
    HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
    surrounded.put(Direction.TOP, new Impassible());
    surrounded.put(Direction.BOTTOM, new Impassible());
    surrounded.put(Direction.LEFT, new Impassible());
    surrounded.put(Direction.RIGHT, new Impassible());

    Action actual = c.chooseAction(surrounded);
    Action expected = new Action(Action.ActionType.STAY);
    assertEquals(expected, actual);

    //Plips are seen; ATTACK one of them randomly.
    HashMap<Direction, Occupant> topPlip = new HashMap<Direction, Occupant>();
    topPlip.put(Direction.TOP, new Plip(4));
    topPlip.put(Direction.BOTTOM, new Empty());
    topPlip.put(Direction.LEFT, new Empty());
    topPlip.put(Direction.RIGHT, new Empty());

    actual = c.chooseAction(topPlip);
    expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
    assertEquals(expected, actual);

    //Clorus has energy >= 1, REPLICATE to a random empty square.
    c = new Clorus(1);
    HashMap<Direction, Occupant> leftEmpty = new HashMap<Direction, Occupant>();
    leftEmpty.put(Direction.TOP, new Impassible());
    leftEmpty.put(Direction.BOTTOM, new Impassible());
    leftEmpty.put(Direction.LEFT, new Empty());
    leftEmpty.put(Direction.RIGHT, new Impassible());

    actual = c.chooseAction(leftEmpty);
    expected = new Action(Action.ActionType.REPLICATE, Direction.LEFT);
    assertEquals(expected, actual);

    //Clorus has energy < 1, Move to a random empty square.
    c = new Clorus(0.5);
    actual = c.chooseAction(leftEmpty);
    expected = new Action(Action.ActionType.MOVE, Direction.LEFT);
    assertEquals(expected, actual);
  }

}
