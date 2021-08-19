
package util.comp;

//import org.jsresources.apps.radio.*;

public class Constants {
    public static final int DIR_MIC = 0;
    public static final int DIR_SPK = 1;

    public static boolean DEBUG = true;
    public static boolean VERBOSE = true;

    // ----------------- constants for audio formats ----------------------

    public static final int FORMAT_CODE_CD=1;
    public static final int FORMAT_CODE_FM=2;
    public static final int FORMAT_CODE_TELEPHONE=3;
    public static final int FORMAT_CODE_GSM=4;
    public static final int FORMAT_CODE_MP3_HI=5;
    public static final int FORMAT_CODE_MP3_MID=6;
    public static final int FORMAT_CODE_MP3_LOW=7;
    public static final int FORMAT_CODE_OGG_HI=8;
    public static final int FORMAT_CODE_OGG_MID=9;
    public static final int FORMAT_CODE_OGG_LOW=10;

    public static final String[] FORMAT_NAMES={
    	"Ogg/Vorbis high (192KBit/s)",
    	"Ogg/Vorbis mid (128KBit/s)",
    	"Ogg/Vorbis low (64KBit/s)",
    	"MP3 high (192KBit/s)",
    	"MP3 mid (128KBit/s)",
    	"MP3 low (64KBit/s)",
	"WAVE CD quality (44100Hz, 16-bit, PCM stereo)",
	"WAVE FM quality (22050Hz, 8-bit, PCM stereo)",
	"AU Telephone (8000Hz, uLaw mono)",
	"Cell phone GSM (13.2KBit/s mono)",
    };

    public static final int[] FORMAT_CODES={
	FORMAT_CODE_OGG_HI,
	FORMAT_CODE_OGG_MID,
	FORMAT_CODE_OGG_LOW,
	FORMAT_CODE_MP3_HI,
	FORMAT_CODE_MP3_MID,
	FORMAT_CODE_MP3_LOW,
	FORMAT_CODE_CD,
	FORMAT_CODE_FM,
	FORMAT_CODE_TELEPHONE,
	FORMAT_CODE_GSM,
    };

    public static final int FORMAT_CODE_DEFAULT = FORMAT_CODE_CD; //FORMAT_CODE_OGG_MID;

    // ----------------- constants for the circular buffer ----------------------
    public static final String[] CIRCBUF_NAMES={
    	"10 sec.",
    	"20 sec.",
    	"30 sec.",
    	"45 sec.",
    	"60 sec.",
    	"90 sec.",
    	"2 min.",
    	"3 min.",
    	"4 min.",
    	"5 min.",
    };
    public static final int[] CIRCBUF_MILLIS = {
    	10000, 20000, 30000, 45000, 60000, 90000, 120000, 180000, 240000, 300000
    };
    public static final int CIRCBUF_INDEX_DEFAULT = 2; // 30 seconds


    // ----------------- constants for audio buffer ----------------------

    public static final int[] BUFFER_SIZE_MILLIS = {
    	10, 20, 30, 40, 50, 70, 85, 100, 130, 150, 180, 220, 400
    };
    public static final String[] BUFFER_SIZE_MILLIS_STR = {
    	"10", "20", "30", "40", "50", "70", "85", "100", "130", "150", "180", "220", "400"
    };
    public static final int BUFFER_SIZE_INDEX_DEFAULT = 3;


    // ----------------- constants for property changes ----------------------
    public static final String CONNECTION_PROPERTY = "CONNECTION";
    public static final String AUDIO_PROPERTY = "AUDIO";
    public static final String STARTED_PROPERTY = "STARTED";
    public static final String RECORDING_PROPERTY = "RECORDING";

    // ----------------- constants for sockets ----------------------
    public static final boolean TCP_NODELAY = false;
    // -1 means do not set the value
    public static final int TCP_RECEIVE_BUFFER_SIZE = 1024;
    public static final int TCP_SEND_BUFFER_SIZE = 1024;

    public static void out(String s) {
	Debug.out(s);
    }
}
