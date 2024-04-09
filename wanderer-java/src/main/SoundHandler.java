package main;

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
        this.combatSound="src/main/resources/attack.wav";
        this.move="src/main/resources/move.wav";
        this.victory="src/main/resources/victory.wav";
        this.kill="src/main/resources/kill.wav";
        this.johnCena="src/main/resources/john-cena.wav";
        this.musicFiles=new String[]{"src/main/resources/drakath-theme.wav","src/main/resources/paradox-portal.wav","src/main/resources/wrath-theme.wav"};
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
                    new File("src/main/resources/nyan/nyan-cat.wav"));
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
