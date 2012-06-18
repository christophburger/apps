package data;

public abstract class Plugin {
	
	private Boolean active = false;
	
	public abstract void run();
	
	public abstract String getPluginName();
	
	public abstract String getAuthor();
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean t) {
		active = t;
	}
	
	public abstract void stop();
	
}
