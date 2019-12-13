package Juego;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audios{
    private ArrayList<File> EFX= new ArrayList<>();
    public Audios(){
        IntroducirFx("backgroundMusic.wav");
        IntroducirFx("explosion.wav");
        IntroducirFx("playerLoose.wav");
        IntroducirFx("playerShoot.wav");
        IntroducirFx("");
        IntroducirFx("");
        IntroducirFx("");
        IntroducirFx("");
        IntroducirFx("");
        IntroducirFx("");
        IntroducirFx("");
    }

    public void IntroducirFx(String Ruta){
        try {
            File file=new File("").getAbsoluteFile();
            String rutt= file + "/Efectos/"+Ruta;
            file=new File(rutt);
            EFX.add(file);
        }
        catch (NullPointerException e2){
            System.out.println("Error la ruta o archivo no encontrado de audio....");    
        }
    }

    public void Fx(int indice){
        try{
            File  file =EFX.get(indice);

            //Se obtiene un clip de sonido
            Clip sonido = AudioSystem.getClip();

            //Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(file));
            
            //Comienza la reproducción
            sonido.start(); 
        }

        catch(LineUnavailableException ex){
            Logger.getLogger(Audios.class.getName()).log(Level.SEVERE, null, ex);
        }

        catch(UnsupportedAudioFileException ex){
            Logger.getLogger(Audios.class.getName()).log(Level.SEVERE,null, ex);
        }

        catch(IOException ex){
            Logger.getLogger(Audios.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
}