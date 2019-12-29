package com.diogothecoder.arewealone;

import com.diogothecoder.arewealone.actions.Action;
import com.diogothecoder.arewealone.actions.ActionEnum;
import com.diogothecoder.arewealone.map.Galaxy;
import com.diogothecoder.arewealone.map.Map;
import com.diogothecoder.arewealone.map.SolarSystem;
import com.diogothecoder.arewealone.map.Universe;
import com.diogothecoder.arewealone.tools.exceptions.NotFoundException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Game {
	private static Universe theUniverse;
	private static Player thePlayer;

	public static void main(String[] args) {
		theUniverse = Universe.getInstance();
		thePlayer = Player.getInstance();

		ArrayList<LinkedHashMap<ActionEnum, Method>> actionsList;

		while (true) {
			SolarSystem solarSystem = getUniverse().getGalaxy().getSolarSystem();
			if (solarSystem == null) {
				getUniverse().getGalaxy().display();
			} else {
				solarSystem.display();
			}

			try {
				Map.getCurrentMap().getPlayerPosition().display();

				LinkedHashMap<ActionEnum, Method> actions = getPlayer().getNavigation().getPossibleActions();
				Action.displayPossibleActions(actions);

				actionsList = new ArrayList<>();
				actionsList.add(actions);

				Action.executeFromInput(actionsList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Universe getUniverse() {
		return theUniverse;
	}
	
	public static Player getPlayer() {
		return thePlayer;
	}

}
