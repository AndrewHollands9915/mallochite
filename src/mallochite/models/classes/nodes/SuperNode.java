package mallochite.models.classes.nodes;

import java.security.NoSuchAlgorithmException;

import mallochite.models.abstractClasses.nodes.Node;
import mallochite.models.classes.User;

public class SuperNode extends Node
{
	public SuperNode ( User thisUser ) throws NoSuchAlgorithmException
	{
		super ( thisUser );
	}

	public boolean contactUser( UUID uuid , User userToContact , User userToCheck )
	{
		socket
		ConnectionManager connection = new ConnectionManager( 
	}
}
