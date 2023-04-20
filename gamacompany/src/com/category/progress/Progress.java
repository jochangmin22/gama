package com.category.progress;

import javax.swing.JProgressBar;

public class Progress extends Thread {

	private final JProgressBar progressBar;

	private final String message;

	public Progress(JProgressBar progress, String message) {
		this.progressBar = progress;
		this.message = message;
	}

	@Override
	public void run() {
		progressBar.setValue(50);
		progressBar.setIndeterminate(true);
		progressBar.setString(message);
	}

	@Override
	public void interrupt() {
		super.interrupt();

		progressBar.setValue(0);
		progressBar.setIndeterminate(false);
		progressBar.setString("");
	}
}
