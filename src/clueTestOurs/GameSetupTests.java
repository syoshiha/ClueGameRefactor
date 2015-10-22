package clueTestOurs;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;

public class GameSetupTests {
	
	Board board;
	
	@Before
	public void setup() {
		board = new Board();
		board.initialize();
	}

}
