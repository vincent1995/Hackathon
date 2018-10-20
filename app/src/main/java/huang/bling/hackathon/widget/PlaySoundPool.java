package huang.bling.hackathon.widget;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by 沈东 on 2016/4/11.
 */
public class PlaySoundPool {

    private Context context;
    private  int streamVolume;
    private SoundPool soundPool;
    private HashMap<Integer,Integer> soundPoolMap;

    public PlaySoundPool(Context context) {
        this.context = context;
        initSounds();
    }

    public void initSounds() {
        soundPool = new SoundPool(100, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

    //    streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = 100;
    }

    /**
     * raw代表资源文件,sound表示声音id，ID代表声音id，uLoop代表是否循环
     * @param raw
     * @param ID
     * @param sound
     * @param uLoop
     */
    public void startSound(int raw,int ID,final int sound,final int uLoop){
        soundPoolMap.put(ID, soundPool.load(context, raw, ID));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1, uLoop, 1f);
            }
        },120);
    }

//    public void loadSfx(int raw, int ID) {
//        soundPoolMap.put(ID, soundPool.load(context, raw, ID));
//    }
//
//    public void play(int sound, int uLoop) {
//        soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1, uLoop, 1f);
//    }
}
