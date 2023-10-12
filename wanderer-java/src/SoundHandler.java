import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler {
    String combatSound;
    String victory;

    String move;

    String[] musicFiles;

    String kill;

    Area area;

    String johnCena;


    public SoundHandler(Area area) {
        this.area=area;
        this.combatSound="./src/music/attack.wav";
        this.move="./src/music/move.wav";
        this.victory="./src/music/victory.wav";
        this.kill="./src/music/kill.wav";
        this.johnCena="./src/music/john-cena.wav";
        this.musicFiles=new String[]{"./src/music/drakath-theme.wav","./src/music/paradox-portal.wav","./src/music/wrath-theme.wav"};
    }

    private void playSound(String string, double volume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(string));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log10(volume) * 20.0);
            gainControl.setValue(dB);

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void combatSound() {
        playSound(combatSound,1);
    }

    public void victorySound() {
        playSound(victory,1);
    }

    public void moveSound() {
        playSound(move,1);
    }

    public void killSound() {
        playSound(kill,1.5);
    }

    public void johnCenaSound() {
        playSound(johnCena,2);
    }

    public void playMusic(double volume) {
        if (area.clip != null) {
            area.clip.stop();
        }
        try {
            int number = area.random.nextInt(3);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(musicFiles[number]));
            area.clip = AudioSystem.getClip();
            area.clip.open(audioInputStream);
            area.clip.setLoopPoints(0, -1);

            FloatControl gainControl = (FloatControl) area.clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log10(volume) * 20.0);
            gainControl.setValue(dB);

            area.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void nyanMusic(double volume) {
        if (area.clip != null) {
            area.clip.stop();
        }
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("./src/music/nyan/nyan-cat.wav"));
            area.clip = AudioSystem.getClip();
            area.clip.open(audioInputStream);
            area.clip.setLoopPoints(0, -1);

            FloatControl gainControl = (FloatControl) area.clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log10(volume) * 20.0);
            gainControl.setValue(dB);

            area.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

}
