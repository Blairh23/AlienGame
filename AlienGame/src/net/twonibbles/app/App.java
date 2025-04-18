package net.twonibbles.app;

import java.awt.EventQueue;

import net.twonibbles.ui.GameMainFrame;

public class App {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
			new GameMainFrame();
		});
		
	}
}
