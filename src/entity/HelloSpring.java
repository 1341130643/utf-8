package entity;

public class HelloSpring {
	private String who;
	public void print(){
		System.out.println("Hello,"+this.getWho()+"!");
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}
}
