package jp.techblog.epsiloncode.soundpoolwrapper;

import android.content.Context;
import android.media.SoundPool;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuishihara on 15/09/27.
 */
public class SoundPoolWrapper implements SoundPool.OnLoadCompleteListener {
    public interface OnLoadCompleteCallback {
        void onLoadComplete(boolean status);
    }

    private SoundPool mSoundPool;
    private SparseIntArray mResourceIdToSoundIdMap = new SparseIntArray();
    private List<Integer> mLoadedResourceIds = new ArrayList<>();
    private Map<Integer, OnLoadCompleteCallback> mResourceIdToCallbackMap = new HashMap<>();

    public SoundPoolWrapper(int maxStreams, int streamType, int srcQuality) {
        this(new SoundPool(maxStreams, streamType, srcQuality));
    }

    public SoundPoolWrapper(SoundPool soundPool) {
        mSoundPool = soundPool;
        mSoundPool.setOnLoadCompleteListener(this);
    }

    public void autoPause() {
        mSoundPool.autoPause();
    }

    public void autoResume() {
        mSoundPool.autoResume();
    }

    public void load(Context context, int resourceId, int priority) {
        load(context, resourceId, priority, null);
    }

    public void load(Context context, int resourceId, int priority, OnLoadCompleteCallback callback) {
        int soundId = mSoundPool.load(context, resourceId, priority);
        mResourceIdToSoundIdMap.put(resourceId, soundId);
        if (callback != null) {
            mResourceIdToCallbackMap.put(resourceId, callback);
        }
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int soundId, int status) {
        int resourceId = toResourceId(soundId);
        mLoadedResourceIds.add(resourceId);
        OnLoadCompleteCallback callback = mResourceIdToCallbackMap.get(resourceId);
        if (callback != null) {
            // Status 0 means success
            callback.onLoadComplete(status == 0);
            mResourceIdToCallbackMap.remove(resourceId);
        }
    }

    public void pause(int streamId) {
        mSoundPool.pause(streamId);
    }

    public int play(int resourceId) {
        return play(resourceId, 1.0f, 0);
    }

    public int play(int resourceId, float volume, int loop) {
        return play(resourceId, volume, volume, 0, loop, 1.0f);
    }

    public int play(int resourceId, float leftVolume, float rightVolume, int priority, int loop, float rate) {
        int soundId = mResourceIdToSoundIdMap.get(resourceId);
        return mSoundPool.play(soundId, leftVolume, rightVolume, priority, loop, rate);
    }

    public void release() {
        mSoundPool.release();
        mSoundPool = null;

        mResourceIdToSoundIdMap.clear();
        mResourceIdToCallbackMap.clear();
        mLoadedResourceIds.clear();
    }

    public void resume(int streamId) {
        mSoundPool.resume(streamId);
    }

    public void setLoop(int streamId, int loop) {
        mSoundPool.setLoop(streamId, loop);
    }

    public void setRate(int streamId, float rate) {
        mSoundPool.setRate(streamId, rate);
    }

    public void setPriority(int streamId, int priority) {
        mSoundPool.setPriority(streamId, priority);
    }

    public void setVolume(int streamId, float leftVolume, float rightVolume) {
        mSoundPool.setVolume(streamId, leftVolume, rightVolume);
    }

    public void stop(int streamId) {
        mSoundPool.stop(streamId);
    }

    public void unload(int resourceId) {
        mSoundPool.unload(mResourceIdToSoundIdMap.get(resourceId));
        mResourceIdToSoundIdMap.delete(resourceId);
        mLoadedResourceIds.remove((Integer)resourceId);
    }

    public boolean isLoaded(int resourceId) {
        return mLoadedResourceIds.contains(resourceId);
    }

    private int toResourceId(int soundId) {
        int index = mResourceIdToSoundIdMap.indexOfValue(soundId);
        return mResourceIdToSoundIdMap.keyAt(index);
    }
}
