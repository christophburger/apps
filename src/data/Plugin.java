package data;

public abstract class Plugin {
	
	private Boolean active = false;
	
	public void run() {
		
	}
	
	public String getPluginName() {
		return null;
	}
	
	public String getAuthor() {
		return null;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean t) {
		active = t;
	}
}
