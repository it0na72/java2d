package main;

import main.panel;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        JFrame window = new JFrame();
        panel panel = new panel ();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        window.add(panel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.setupGame();
        panel.startGameThread();
    }
}