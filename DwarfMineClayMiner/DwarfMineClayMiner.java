import org.osbot.rs07.api.Bank;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.ConditionalSleep;



import java.awt.Graphics;


 
@ScriptManifest(name = "DwarfMineClayMiner", author = "Aidan", version = 0.1, info = "", logo = "") 
public class DwarfMineClayMiner extends Script {
 
        final String ROCK_NAME = "Clay";
 
        final Position BANK_SPOT = new Position(3013, 3355, 0);
        final Position MINE_ENTRANCE = new Position(3058, 3376, 0);
        final Position MINE_SPOT = new Position(3053, 9819, 0);
        final int DOOR_ID = 24058;
 
        // code used at start
        public void onStart() {
        	log("starting script");
        }
 
        // code to be executted at the end
        public void onExit() {
        	log("ending script");
        }
        
        @Override
        public final void onMessage(final Message message) {
            log("A message arrived in the chatbox: " + message.getMessage());
            if(message.contains("Mon")) {
            	sys.exit();
            }
        }
 
        // code in loop
        public int onLoop() throws InterruptedException {            
            getWalking().webWalk(BANK_SPOT);
            //getWalking().webWalk(MINE_ENTRANCE);
            
            getWalking().webWalk(MINE_SPOT);
            
            while(getInventory().getEmptySlotCount() != 24) {
            	Entity rock = getObjects().closest("Rocks");
                if (rock != null && rock.interact("Mine")) {
                      new ConditionalSleep(5000) {
                        @Override
                        public boolean condition() {
                            return myPlayer().isAnimating() || rock!=null;
                        }
                    }.sleep();
                }
            }
            
            getWalking().webWalk(BANK_SPOT);
            
               
            return 50;
        }
 
        // paint
        public void onPaint(Graphics g) {
        	g.drawString("Some text", 10, 10);
        }
 
}


