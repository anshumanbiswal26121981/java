package improved;

public class Transition {
	  private EventActions events;

	  private BaseState target;

	  public Transition(EventActions ev, BaseState target) {
	    this.events = ev;
	    this.target = target;
	  }

	  public EventActions getName() {
	    return events;
	  }

	  public BaseState getTarget() {
	    return target;
	  }
	}