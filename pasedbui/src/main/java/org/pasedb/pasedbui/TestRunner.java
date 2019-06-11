package org.pasedb.pasedbui;

public class TestRunner {

	public TestRunner() {
		try{
			AddNewLink newlink = new AddNewLink();
			newlink.fetchOGMetaData("https://farzadlaw.com/divorce-and-child-custody/what-is-parental-alienation", "comment", 99);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TestRunner();

	}

}
