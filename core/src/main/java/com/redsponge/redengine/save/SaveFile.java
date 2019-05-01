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

public class SaveFile {

    private FileHandle file;
    private JsonValue values;

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

        if(newFile) {
            values = new JsonValue(JsonValue.ValueType.object);
        }
        else {
            values = new JsonReader().parse(file);
        }
    }

    public JsonValue getRoot() {
        return values;
    }

    public void setValue(String dotPath, boolean value) {
        getValueFromPath(dotPath, JsonValue.ValueType.booleanValue).set(value);
    }

    public void setValue(String dotPath, float value) {
        getValueFromPath(dotPath, JsonValue.ValueType.doubleValue).set(value, null);
    }

    public void setValue(String dotPath, int value) {
        getValueFromPath(dotPath, JsonValue.ValueType.longValue).set(value, null);
    }

    public void setValue(String dotPath, String value) {
        getValueFromPath(dotPath, JsonValue.ValueType.stringValue).set(value);
    }

    public JsonValue getValueFromPath(String dotPath, JsonValue.ValueType type) {
        String[] parts = dotPath.split("\\.");

        JsonValue out = values;

        for (String s : parts) {
            JsonValue next = out.get(s);
            if(next == null) {
                next = new JsonValue(s.equals(parts[parts.length - 1]) ? type : JsonValue.ValueType.object);
                out.addChild(s, next);
            }
            out = next;
        }
        return out;
    }

    public JsonValue getValue(String dotPath) {
        JsonValue end = values;
        for(String val : dotPath.split("\\.")) {
            end = end.getChild(val);
        }
        return end;
    }

    public JsonValue deleteValue(String dotPath) {
        JsonValue deleted = getValueFromPath(dotPath, null);


        Logger.log(this, "Removed", deleted.name);

        deleted.parent.remove(deleted.name);

        System.out.println(deleted.parent);

        return deleted;
    }

    public void saveToFile() {
        String json = values.toJson(JsonWriter.OutputType.json);
        file.writeString(json, false);
    }
}
