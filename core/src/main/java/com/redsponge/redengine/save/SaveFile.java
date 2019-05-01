package com.redsponge.redengine.save;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.redsponge.redengine.utils.Logger;

import java.io.IOException;

public class SaveFile<T> {

    private FileHandle file;
    private Json json;

    public SaveFile(String path) {
        final boolean newFile;

        file = Gdx.files.getFileHandle(path, Files.FileType.Absolute);
        if(!file.exists()) {
            try {
                Logger.log(this, "Creating new save file", path);
                if(!file.file().createNewFile()) {
                    throw new IOException("Couldn't Create New File!");
                }
                newFile = true;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            newFile = false;
        }

        json = new Json();
        json.addClassTag("player", Player.class);
    }

    public T get(Class<T> type) {
        return json.fromJson(type, file.readString());
    }
//
//    public JsonValue getValueFromPath(String dotPath, JsonValue.ValueType type) {
//        String[] parts = dotPath.split("\\.");
//
//        JsonValue out = values;
//
//        for (String s : parts) {
//            JsonValue next = out.get(s);
//            if(next == null) {
//                next = new JsonValue(s.equals(parts[parts.length - 1]) ? type : JsonValue.ValueType.object);
//                out.addChild(s, next);
//            }
//            out = next;
//        }
//        return out;
//    }
//
//    public JsonValue getValue(String dotPath) {
//        JsonValue end = values;
//        for(String val : dotPath.split("\\.")) {
//            end = end.getChild(val);
//        }
//        return end;
//    }
//
//    public void deleteValue(String dotPath) {
//        JsonValue deleted = getValueFromPath(dotPath, null);
//
//        Logger.log(this, "Before Removal", deleted.parent);
//        Logger.log(this, "Removed", deleted.name);
//        deleted.parent.remove(deleted.name);
//        Logger.log(this, "After Removal", deleted.parent);
//    }

    public void saveToFile(T t) {
        String out = json.toJson(t);
        file.writeString(out, false);
    }
}
