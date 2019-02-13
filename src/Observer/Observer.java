package Observer;

import GUI.CalendarProgram;

public abstract class Observer {

	protected CalendarProgram calendar;
    
	public abstract void update();

}
