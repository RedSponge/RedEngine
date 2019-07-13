package com.redsponge.redengine.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.redsponge.redengine.utils.Logger;

public class MusicHolder implements Disposable {

    private FileHandle path;
    private Music music;

    public MusicHolder(FileHandle path) {
        this.path = path;
    }

    public void load() {
        music = Gdx.audio.newMusic(path);
    }

    public Music getMusic() {
        if(music == null) {
            Logger.log(this, "[Warning] Music", path, "Not Loaded!");
        }
        return music;
    }

    @Override
    public void dispose() {
        music.dispose();
        music = null;
    }
}
