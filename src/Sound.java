import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private Clip clip;
	private AudioInputStream inputStream;
	private File file;
	
	public Sound(String file) {
		this(new File(file));
	}
	
	public Sound(File file) {
		try {
			this.file = file;
			this.inputStream = AudioSystem.getAudioInputStream(this.file);
			this.clip = AudioSystem.getClip();
			this.clip.open(this.inputStream);
		}catch(LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void playSound() {
		try {
			this.inputStream = AudioSystem.getAudioInputStream(this.file);
			this.clip = AudioSystem.getClip();
			this.clip.open(this.inputStream);
			this.clip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
