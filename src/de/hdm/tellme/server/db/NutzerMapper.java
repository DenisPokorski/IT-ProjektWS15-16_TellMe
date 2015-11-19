package de.hdm.tellme.server.db;

public class NutzerMapper {
	private static NutzerMapper nutzerMapper = null;
	protected NutzerMapper () {
		
	}

	public static NutzerMapper nutzermapper () {
		if (nutzerMapper == null) {
			nutzerMapper = new NutzerMapper ();
		}
		return nutzerMapper;
		
	}
	
}
