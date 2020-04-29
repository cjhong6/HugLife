# HugLife
<p>
HugLife is a simulator that simulate creatures live on an NxN grid, with no wraparound. Each square may be empty, impassible, or contain exactly one creature. At each timestep, exactly one creature takes a single action. Creatures choose actions in a round-robin fashion; they take turns one after another.
</p>
<p>There is a global queue of all creatures in the world, waiting their turn to take an action. When a creature is at the front of the queue, the world simulator tells that creature who its four neighbors are and requests a choice of action from the creature. More specifically, the world simulator calls the creature’s chooseAction() method, which takes as an argument a collection of all four neighbors. Based on the identity of the four neighbors, the creature then chooses one of exactly five actions: MOVE, REPLICATE, ATTACK, STAY, or DIE.
</p>
<p>MOVE, REPLICATE, and ATTACK are directional actions, and STAY and DIE are stationary actions. If a creature takes a directional action, it must specify either a direction or a location. For example, if the acting creature sees a creature to its LEFT that it can eat, it might choose to ATTACK LEFT.
</p>
<p>After a creature chooses an Action, the simulator enacts the changes to the world grid. 
</p>
<p>For example, after the acting creature eats another creature, the acting Creature might become stronger, die, change colors, etc.
</p>
<p>This will be accomplished by a callback to the creature, which is required to provide move(), replicate(), attack(), and stay() methods that describe how the state of the acting creature will evolve after each of these respective actions.
</p>
<p>For example, if your creature chooses to replicate upwards by returning new Action(Action.ActionType.REPLICATE, Direction.TOP), then the game simulator is guaranteed to later call the replicate() method of the creature that made this choice.</p>

<h3>
There are 3 creatures in this simulator
</h3>
    <ul style="list-style-type: square">
      <li>Sample Creature</li>
        <ul>
          <li>A red square</li>
          <li>It know how to MOVE and STAY</li>
        </ul>
      <li>Plip</li>
        <ul>
          <li>
           A green square
          </li>
          <li>
            It know how to STAY and REPLICATE
          </li>
          <li>
            It will MOVE if they happen to see their mortal enemy, the Clorus.
          </li>
        </ul>
      <li>Clorus</li>
        <ul>
          <li>A fierce blue-colored predator that enjoys nothing more than snacking on hapless Plips.</li>
          <li>It know how to STAY, MOVE, REPLICATE</li>
          <li>It will ATTACK if they happen to see their snack, the Plip.</li>
        </ul>
    </ul>
    
<h3>Usage</h3>
<ol>
<li>javac creatures/*.java huglife/*.java</li>
<li>java huglife.HugLife strugggz<lo
</ol>

<h3>Demo</h3>
https://i.imgur.com/E2Kdowq.mp4
