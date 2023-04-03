/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import util.Informacoes;
import util.Tempo;
import util.ProcuraArquivo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import modelo.Album;
import audio.Mp3;
import modelo.Musica;
import modelo.PlayList;
import persistencia.AlbumDAO;
import persistencia.ListaDAO;
import persistencia.MusicaDAO;
import util.GAudioSystem;
import visao.JFInfoMusica;
import visao.JFOpcoes;
import visao.JFSobre;
import visao.JFTelaMiniPlayer;
import visao.JFTelaPrincipal;

/**
 *
 * @author Idair F. Guido
 */
public class MediaPlayer {

    private List<File> listaArquivos;
    private JFTelaPrincipal telaPrincipal = null;
    private JFTelaMiniPlayer telaMiniPlayer;
    private List<Musica>  playlist = new LinkedList<Musica>();
    private Musica musica = null;
    private Mp3 musicaEstanciada;
    private Thread timer;
    private boolean continuar = false;
    private boolean executando = false;
    private boolean pausado = false;
    private boolean repetir;
    private boolean sequencia;
    private boolean aleatorio;
    private boolean verificou = false;
    private int nroMusicaExecutando = -1;
    private List<String> musicasExecutadas = new LinkedList<String>();

    public List<PlayList> listas;
    public PlayList listaExecutando = null;

    public GAudioSystem volume = new GAudioSystem();

    public static void main(String[] args) {
        new MediaPlayer();
    }

    public MediaPlayer() {
        DaoDb4o.conectar();
        estanciaTelaPrincipal();
    }

    private boolean estanciaMusica(int nroMUsica) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if(nroMUsica >= 0){
            musica = playlist.get(nroMUsica);
            nroMusicaExecutando = nroMUsica;
            musicaEstanciada = new Mp3(musica.getCaminho());
            if(musicaEstanciada.isCriada()){
                verificou = false;
                addMusicaExecutada(nroMUsica);
                atualizador();
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public void play(int musicaId) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if(isExecutando()){
            if(pausado){
                musicaEstanciada.resume();
                telaPrincipal.setIconPause();
                pausado = false;
            }else{
                musicaEstanciada.stop();
                telaPrincipal.setIconPlay();
                pausado = true;
            }
        }else{
            if(estanciaMusica(musicaId)){
                musicaEstanciada.play();
                telaPrincipal.setIconPause();
                executando = true;
            }else{
                if((telaMiniPlayer != null) && telaMiniPlayer.isVisible()){
                    if(estanciaMusica(musicaId)){
                        musicaEstanciada.play();
                        telaPrincipal.setIconPause();
                        executando = true;
                    }
                }else{
                    JOptionPane.showMessageDialog(telaPrincipal, "Erro ao iniciar Musica selecionada.\n" +
                        "O arquivo pode estar corrompido,\n" +
                        " pode ter sido mudado de pasta ou\n" +
                        " er sido deletado!!!!!", "Erro!!!!", 1);
                }
            }
        }
        
    }

    public void stop(){
        musicaEstanciada.stop();
        telaPrincipal.setIconPlay();
        verificou = false;
        executando = false;
        pausado = false;
        continuar = false;
    }

    public void salvaPlayList(String nome){
        PlayList playList = new PlayList();
        playList.setTitulo(nome);
        playList.setCodigo(ListaDAO.codigoPlayListMaisMais());
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < playlist.size(); i++) {
            Musica mu = playlist.get(i);
            temp.add(mu.getCodigo()+"");
        }
        playList.setMusicaIds(temp);

        if(ListaDAO.existe(nome)){
            ListaDAO.alterar(playList);
        }else{
            ListaDAO.inserir(playList);
            listaExecutando = playList;
        }
    }

    public void proxMusica() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if(isExecutando()){
            stop();
        }
        if(verificou){

        }else{
            if((playlist.size()-1) == nroMusicaExecutando){
                nroMusicaExecutando = 0;
                play(nroMusicaExecutando);
            }else{
                nroMusicaExecutando++;
                play(nroMusicaExecutando);
            }
        }
        telaPrincipal.setSelectedRow(nroMusicaExecutando);
    }

    public void musicaAnt() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if(isExecutando()){
            stop();
        }
        if(verificou){

        }else{
            if(nroMusicaExecutando == 0){
                nroMusicaExecutando = playlist.size()-1;
                play(nroMusicaExecutando);
            }else{
                nroMusicaExecutando--;
                play(nroMusicaExecutando);
            }
        }
        telaPrincipal.setSelectedRow(nroMusicaExecutando);
    }

    public void estanciaTelaPrincipal() {
        if (telaPrincipal == null) {
            telaPrincipal = new JFTelaPrincipal(this);
            telaPrincipal.setVisible(true);
        } else {
            telaPrincipal.setVisible(true);
        }
        if (telaMiniPlayer != null) {
            telaMiniPlayer.dispose();
        }
        telaPrincipal.setVolume((int) (volume.getVolumeLevel(1)));
        telaPrincipal.setaIconVolume();
    }

    public void estanciaOpcoes(){
        JFOpcoes opcoes = new JFOpcoes(this);
        opcoes.setVisible(true);
    }

    public void esstanciaInformacoesMusica(){
        if(musica != null){
            JFInfoMusica info = new JFInfoMusica(musica);
            info.setVisible(true);
        }
    }

    public void estanciaSobre(){
        JFSobre sobre = new JFSobre();
        sobre.setVisible(true);
    }

    public void estanciaMiniPlayer() {
        if (telaMiniPlayer == null) {
            telaMiniPlayer = new JFTelaMiniPlayer(this);
            telaMiniPlayer.setVisible(true);
        } else {
            telaMiniPlayer.setVisible(true);
        }
        if (telaPrincipal != null) {
            telaPrincipal.dispose();
        }
        telaMiniPlayer.setVolume((int) (volume.getVolumeLevel(1)));
        telaMiniPlayer.setaIconVolume();
    }

    public void adicionaArquivo() throws UnsupportedAudioFileException, IOException {
        ProcuraArquivo procura = new ProcuraArquivo();
        procura.procuraMusica();
        listaArquivos = procura.getArquivos();
        if (procura.getArquivos().size() >= 1) {
            int max = procura.getArquivos().size();
            for (int i = 0; i < listaArquivos.size(); i++) {
                File arquivo = listaArquivos.get(i);
                String artista = Informacoes.getAutor(arquivo);
                String album = Informacoes.getAlbum(arquivo);
                String titulo = Informacoes.getTitulo(arquivo);

                Musica musicaAdd = new Musica();
                musicaAdd.setCaminho(arquivo);
                musicaAdd.setDuração(Tempo.getTempo(arquivo));
                musicaAdd.setFormato(Informacoes.getExtensao(arquivo));
                musicaAdd.setQualidade(Informacoes.getQualidade(arquivo));
                musicaAdd.setTempo(Tempo.getTempoReal(arquivo));
                musicaAdd.setTitulo(titulo);
                musicaAdd.setAlbum(album);
                musicaAdd.setArtista(artista);
                musicaAdd.setNome(arquivo.getName());

                verificaAlbum(album, artista);

                if(MusicaDAO.existe(musicaAdd)){
                }else{
                    musicaAdd.setCodigo(MusicaDAO.codigoMusicaMaisMais());
                    if (MusicaDAO.inserir(musicaAdd)) {
                        telaPrincipal.addMusicaNaTabela(musicaAdd);
                    }
                }
            }
             System.out.println("max: "+max);
        }
    }

    private void verificaAlbum(String titulo, String artista) throws UnsupportedAudioFileException, IOException {
        Album album2 = new Album();
        album2.setTitulo(titulo);
        album2.setArtista(artista);
        if(AlbumDAO.existe(album2)){
        }else{
            album2.setCodigo(AlbumDAO.codigoAlbumMaisMais());
            if(AlbumDAO.inserir(album2)){
            }
        }
    }

    public void addMusica(Musica musica){
        playlist.add(musica);
    }

    public boolean playListIsEmpty(){
        if(playlist.size() < 1){
            return true;
        }else{
            return false;
        }
    }

    public void addListaMusicas(List<Musica> musicas){
        playlist = new ArrayList<Musica>();
        playlist = musicas;
    }
    
    private void atualizador(){
        timer = new Thread(() -> {
            while (continuar) {
                try {
                    try {
                        atualizar();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        Logger.getLogger(MediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        continuar = true;
        timer.start();
    }

    private void atualizar() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        int valorMaximo = (int) (musica.getTempo() / 1000);
        int valor = (int) (musicaEstanciada.getTempoExecutado() / 1000);
        Long tempoRealExecutado = musicaEstanciada.getTempoExecutado();
        String tempoTrans = Tempo.transfomaTempo(tempoRealExecutado)+" / "+musica.getDuração();
        String nomeMusica = musica.getTitulo();
        if(telaPrincipal != null){
            telaPrincipal.setProgresso(valorMaximo, valor);
            telaPrincipal.setTempoDecorrido(tempoTrans);
        }
        if(telaMiniPlayer != null){
            telaMiniPlayer.setProgresso(valorMaximo, valor);
            telaMiniPlayer.setTempoDecorrido(tempoTrans, nomeMusica, musica.getAlbum());
        }
        if(musicaEstanciada.isFimMusica()){
            stop();
            continuar = false;
            verificaModoExecucao();
        }
    }

    public int getNroMusicaExecutando() {
        return nroMusicaExecutando;
    }

    private void verificaModoExecucao() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if(isExecutando()){
        }else{
            if(isSequencia()){
                proxMusica();
            }
            if(isAleatorio()){
                play(getRandomico(0, playlist.size()-1));
            }
            telaPrincipal.setSelectedRow(nroMusicaExecutando);
        }
    }

    public int getNroRandomico(){
        int jaexecutou = musicasExecutadas.size();
        int start = 0;
        int end = playlist.size()-1;
        int posicao =  getRandomico(start, end);
        if(jaexecutou != 0){
            while (jaTocou(posicao)) {
                posicao = getRandomico(start, end);
            }
        }
        return posicao;
    }

    public static int getRandomico(int start, int end) {
        Random generator = new Random();
        long range = (long) end - (long) start + 1;
        long fraction = (long) (range * generator.nextDouble());
        int numero = (int) (fraction + start);
        return numero;
    }

    public void criaNewPlayList(String nome, int[] musica){
        PlayList novaLista = new PlayList();
        novaLista.setCodigo(ListaDAO.codigoPlayListMaisMais());
        novaLista.setTitulo(nome);
    }


    /**
     * @return the repetir
     */
    public boolean isRepetir() {
        return repetir;
    }

    public void addMusicaExecutada(int posicao){
        if(!jaTocou(posicao)){
            musicasExecutadas.add(posicao+"");
        }
    }

    public boolean jaTocou(int posicao){
        int jaexecutou = musicasExecutadas.size();
        boolean igual = false;
        if(jaexecutou != 0){
            for (int i = 0; i < jaexecutou; i++) {
                int nro = Integer.parseInt(musicasExecutadas.get(i));
                if(posicao == nro){
                    igual = true;
                }
            }
        }
        return igual;
    }

    public void limpaPlayList(){
        playlist.clear();
    }

    /**
     * @param repetir the repetir to set
     */
    public void setRepetir(boolean repetir) {
        this.repetir = repetir;
    }

    /**
     * @return the sequencia
     */
    public boolean isSequencia() {
        return sequencia;
    }

    /**
     * @param sequencia the sequencia to set
     */
    public void setSequencia(boolean sequencia) {
        this.sequencia = sequencia;
    }

    /**
     * @return the aleatorio
     */
    public boolean isAleatorio() {
        return aleatorio;
    }

    /**
     * @param aleatorio the aleatorio to set
     */
    public void setAleatorio(boolean aleatorio) {
        this.aleatorio = aleatorio;
    }

    /**
     * @return the executando
     */
    public boolean isExecutando() {
        return executando;
    }
}

