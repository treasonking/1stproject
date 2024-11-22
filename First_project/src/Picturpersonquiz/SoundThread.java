package Picturpersonquiz;

import java.io.File;
import jaco.mp3.player.MP3Player;

public class SoundThread extends Thread {
	private volatile boolean running = true;
	private String filePath;
	private MP3Player mp3;

	public SoundThread(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {
		try {
			File file = new File(filePath);
			mp3 = new MP3Player(file);
			mp3.play();
			while (running) {
				Thread.sleep(5000);
			}
			mp3.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopRunning() {
		running = false;
		if (mp3 != null) {
			mp3.stop();
		}
	}
}
