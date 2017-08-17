import java.io.File;

import javax.security.auth.login.Configuration;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Sound {
	private MediaPlayer mediaPlayer;
	private Media media;
	private File file;
	//private Clip clip;
	//private AudioInputStream inputStream;

	public Sound() {
	}

	public Sound(String file) {
		this(new File(file));
	}

	public Sound(File file) {
		this.file = file;
		this.media = new Media(this.file.toURI().toString());
		/*
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
		 */
		//Application.launch();
	}

	public void playSound() {
		this.mediaPlayer = new MediaPlayer(media);
		this.mediaPlayer.play();
		/*
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
		}*/
	}

}
