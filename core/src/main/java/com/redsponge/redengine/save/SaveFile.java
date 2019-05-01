package com.redsponge.redengine.save;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.redsponge.redengine.utils.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class SaveFile {

    private FileHandle file;
    private JsonValue values;
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

        if(newFile) {
            values = new JsonValue(JsonValue.ValueType.object);
        }
        else {
            values = new JsonReader().parse(file);
        }
        System.out.println(values);
    }

    public JsonValue getRoot() {
        return values;
    }

    public void setValue(String dotPath, JsonValue value) {
        String output = dotPath.

        end
    }

    public JsonValue getValue(String dotPath) {
        JsonValue end = values;
        for(String val : dotPath.split("\\.")) {
            end = end.getChild(val);
        }
        return end;
    }

    public void saveToFile() {
        System.out.println(values.prettyPrint(JsonWriter.OutputType.json, 1));
        String json = values.toJson(JsonWriter.OutputType.json);
        System.out.println(json);
        file.writeString(json, false);
    }
}
