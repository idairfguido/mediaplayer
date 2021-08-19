/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Idair F. Guido
 */
public class Mp3 {

    private File musicFile;
    private SourceDataLine sDLine;
    private AudioInputStream aIOStream;
    private Thread aux;
    private boolean flag, fimMusica, criada;


    public Mp3(File caminho) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.musicFile = caminho;
        criarMusica();
    }

    public void criarMusica() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        setCriada(create());
         aux = new Thread(
            new Runnable() {
                 public void run() {
                 }
            }
         );
    }

    /**
     * Get the Inputs from the music file name
     */
    private boolean create() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(musicFile);
            AudioInputStream din = null;
            if (in != null) {
                AudioFormat baseFormat = in.getFormat();
                AudioFormat decodedFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                        baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                        false);
                din = AudioSystem.getAudioInputStream(decodedFormat, in);
                sDLine = getLine(decodedFormat);
                aIOStream = din;
            }
            return true;
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            return false;
        } catch (IOException iOException) {
            return false;
        } catch (LineUnavailableException lineUnavailableException) {
            return false;
        }
    }

    /**
     * Start or continue the music
     *
     * @param t
     *            A auxiliary thread when play the music keep running the
     *            program
     */
    public void play() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        try {
            start();
            aux.interrupt();
            aux = new Thread(
                    new Runnable() {
                        public void run() {
                            rawplay();
                        }
                    }
            );
        } catch (Exception e) {
            this.fimMusica = true;
            System.out.println("fim da musica");
        }

        aux.start();
        fimMusica = false;
    }

    /**
     * Play same part of the music
     */
    private void play(byte[] data) throws IOException {
        try {
            // Read a stream
            int length = aIOStream.read(data, 0, data.length);
            // Play the stream
            sDLine.write(data, 0, length);
        } catch (IllegalArgumentException e) {
            this.fimMusica = true;
            System.out.println("fim da musica play");
            stop();
            close();

        }
    }

    /**
     * Start the SourceDataLine for play
     */
    private void start() {
        sDLine.start();
        flag = true;
    }

    /**
     * The engine of the player
     */
    private void rawplay() {
        byte[] data = new byte[4096];
        try {
            while (flag) {
                play(data);
            }
        } catch (IOException e) {
            this.fimMusica = true;
            System.out.println("Music end");
        }
    }

    /**
     * Close all I/O
     */
    public void close() {
        aux.interrupt();
        sDLine.drain();
        sDLine.stop();
        sDLine.close();
        try {
            aIOStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop the SourceDataLine, if started again only can continue.
     */
    public void stop() {
        aux.interrupt();
        sDLine.stop();
        sDLine.drain();
        flag = false;
    }

    /**
     * If the SourceDataLine is already in stop continue playing.
     */
    public void resume() throws UnsupportedAudioFileException {
        sDLine.start();
        flag = true;
        try {
            play();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the SourceDataLine for Read and Write the music
     *
     * @param audioFormat
     *            The music as Stream
     */
    private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException {
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class,
                audioFormat);
        res = (SourceDataLine) AudioSystem.getLine(info);
        res.open(audioFormat);
        return res;
    }

    public long getTempoExecutado(){
        return sDLine.getMicrosecondPosition();
    }

    public boolean isFimMusica() {
        return fimMusica;
    }

    /**
     * @return the criada
     */
    public boolean isCriada() {
        return criada;
    }

    private void setCriada(boolean create) {
        this.criada = create;
    }
}
