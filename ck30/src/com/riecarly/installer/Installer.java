package com.riecarly.installer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JProgressBar;

import static com.riecarly.installer.worker.Download.dnu;
import java.awt.SystemColor;

public class Installer extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public Installer() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		setResizable(false);
		setTitle("Installer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(410, 260);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Dungeon Call Installer");
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
		lblTitle.setBounds(85, 24, 241, 32);
		contentPane.add(lblTitle);

		JLabel lblVersion = new JLabel("for Windows x86");
		lblVersion.setHorizontalAlignment(SwingConstants.LEFT);
		lblVersion.setVerticalAlignment(SwingConstants.TOP);
		lblVersion.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lblVersion.setBounds(142, 54, 126, 25);
		contentPane.add(lblVersion);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setMaximum(100);
		progressBar.setForeground(UIManager.getColor("Button.light"));
		progressBar.setBounds(10, 189, 374, 25);
		contentPane.add(progressBar);

		JLabel lblProgressPercent = new JLabel("");
		lblProgressPercent.setBounds(195 - (lblProgressPercent.getWidth() / 2), 174, 29, 14);
		contentPane.add(lblProgressPercent);

		Runnable thread = new Runnable() {
			@Override
			public void run() {
				while (true)
					lblProgressPercent.setText(String.valueOf(progressBar.getValue()) + "%");
			}
		};
		new Thread(thread).start();

		JButton btnInstall = new JButton("Start Install");
		btnInstall.setBounds(161, 104, 89, 23);
		btnInstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						dnu(progressBar);
					}
				});
			}
		});
		contentPane.add(btnInstall);

		JLabel lblV = new JLabel("v1.0, Minecraft Represent");
		lblV.setForeground(UIManager.getColor("CheckBox.darkShadow"));
		lblV.setBounds(0, 0, 130, 14);
		contentPane.add(lblV);
		
		JLabel lblcMojangStudios = new JLabel("(c) Mojang Studios.");
		lblcMojangStudios.setForeground(SystemColor.controlDkShadow);
		lblcMojangStudios.setBounds(0, 14, 93, 14);
		contentPane.add(lblcMojangStudios);
		
		JLabel lblForSomeCopyright = new JLabel("For some copyright reason,");
		lblForSomeCopyright.setVerticalAlignment(SwingConstants.TOP);
		lblForSomeCopyright.setHorizontalAlignment(SwingConstants.LEFT);
		lblForSomeCopyright.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblForSomeCopyright.setBounds(136, 138, 139, 14);
		contentPane.add(lblForSomeCopyright);
		
		JLabel lblWeJustDownload = new JLabel("we just download the Minecraft Installer.");
		lblWeJustDownload.setVerticalAlignment(SwingConstants.TOP);
		lblWeJustDownload.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeJustDownload.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblWeJustDownload.setBounds(106, 148, 199, 14);
		contentPane.add(lblWeJustDownload);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Installer frame = new Installer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}