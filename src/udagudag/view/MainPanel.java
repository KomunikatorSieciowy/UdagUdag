package udagudag.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

public class MainPanel extends JPanel {

	private JTextArea chatArea;
	private JScrollPane scrollChatPane;
	private JTextArea messageArea;
	private JScrollPane scrollMessagePane;
	private JList<String> emailsList;
	private JScrollPane scrollEmailsListPane;
	private JButton sendButton;
	
	public MainPanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout());
		
		chatArea = new JTextArea();
		chatArea.setFont(new Font("Courier New", Font.BOLD, 18));
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		((DefaultCaret)chatArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); // Makes scrollbar always scrolled down.
		scrollChatPane = new JScrollPane(chatArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		messageArea = new JTextArea();
		messageArea.setFont(new Font("Courier New", Font.BOLD, 16));
		messageArea.setLineWrap(true);
		((DefaultCaret)messageArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); // Makes scrollbar always scrolled down.
		scrollMessagePane = new JScrollPane(messageArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		emailsList = new JList<String>();
		scrollEmailsListPane = new JScrollPane(emailsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		sendButton = new JButton("Send");
		sendButton.putClientProperty("Parent", "MainPanel");

		scrollChatPane.setPreferredSize(new Dimension(width, height / 8 * 5));
		scrollMessagePane.setPreferredSize(new Dimension(width / 2, height / 4));
		scrollEmailsListPane.setPreferredSize(new Dimension(width / 2, height / 4));
		sendButton.setPreferredSize(new Dimension(width, height / 8));


		add(scrollChatPane, BorderLayout.PAGE_START);
		add(scrollMessagePane, BorderLayout.LINE_START);
		add(scrollEmailsListPane, BorderLayout.LINE_END);
		add(sendButton, BorderLayout.PAGE_END);	
	}
	
	public JTextArea getChatArea() {
		return chatArea;
	}

	public JTextArea getMessageArea() {
		return messageArea;
	}

	public JButton getSendButton() {
		return sendButton;
	}
	
	public JList<String> getEmailsList() {
		return emailsList;
	}
}