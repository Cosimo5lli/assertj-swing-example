package com.example.assertj.swing;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ExampleJFrame extends JFrame {

	public ExampleJFrame() {
		super();
		setSize(200, 200);

		JTextField textToCopy = new JTextField("Text to copy");
		textToCopy.setName("textToCopy");
		textToCopy.setColumns(10);
		getContentPane().add(textToCopy, BorderLayout.NORTH);

		JLabel copiedText = new JLabel("Copied text");
		copiedText.setName("copiedText");
		getContentPane().add(copiedText, BorderLayout.SOUTH);

		JButton copyButton = new JButton("Copy");
		copyButton.setName("copyButton");

		copyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				copiedText.setText(textToCopy.getText());
			}
		});

		getContentPane().add(copyButton, BorderLayout.CENTER);

		setVisible(true);
	}
}
