package pvpoverhaulmain;

import org.bukkit.boss.BossBar;

public class PlayerCountDown {

	String name = "";
	int count = 0;
	long countS = 0;
	double tempSec = 0;
	double tempSec2 = 0;
	boolean canJump = true;
	BossBar b = null;
	
	public PlayerCountDown(String s) {
		name = s;
	}
	
	public BossBar getBossBar() {
		return b;
	}
	
	public void replaceBossBar(BossBar bb) {
		b = bb;
	}
	
	public void addTime(int time, double time2) {
		countS = System.currentTimeMillis();
		count = time;
		if(time2==0) {
		tempSec = (Math.abs(time-1.25))*1000;
		}
		else {
			tempSec = time2;
		}
		tempSec2 = (Math.abs(time-1.25))*1000;
		canJump = false;
	}
	
	public void removeTime(int time) {
		if(time == 0) {
			count = count - 1;
			if(count==0) {
				canJump = true;
			}
		}
	}

}
