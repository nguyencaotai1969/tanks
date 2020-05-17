import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundLoader {
    static void play(String name ){
        try{
            File f = new File("./sound/"+name);
            AudioInputStream stream = AudioSystem.getAudioInputStream(f);
            Clip c = AudioSystem.getClip();
            c.open(stream);
            c.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
