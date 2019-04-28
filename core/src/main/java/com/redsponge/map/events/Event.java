package com.redsponge.map.events;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Event {

    private JsonValue json;

    private HashMap<String, Object> params;

    private EventDataHolder edh;

    public Event(FileHandle json, EventDataHolder edh) {
        this.edh = edh;
        this.json = new JsonReader().parse(json);
        this.params = new HashMap<String, Object>();

        parseJson();
    }

    private void parseJson() {
        parseUserParams();
        parseCode();
    }

    private void parseCode() {
        parseCodeParams();
    }

    private void parseCodeParams() {
        JsonValue params = json.getChild("code");
        for(int i = 0; i < params.size; i++) {
            JsonValue param = params.get(i);
            String name = param.getString("name");
            String type = param.getString("type");
            Object obj;

            switch (type) {
                case "OrthographicCamera":
                    obj = edh.getCamera();
                    break;
                case "DummyObject":
                    obj = edh.getDummy();
                    break;
                default:
                    throw new IllegalArgumentException("Param type " + type + " unknown! for param " + name);
            }
            this.params.put(name, obj);
        }

    }

    public void execute() {
        JsonValue execRoot = json.getChild("code").next;
        for(int i = 0; i < execRoot.size; i++) {
            JsonValue execTask = execRoot.get(i);
            executeTask(execTask);
        }
    }

    private void executeTask(JsonValue execTask) {
        String type = execTask.getString("type"); // The type of operation
        String on = execTask.getString("on"); // On what to execute the operation
        String name = execTask.getString("name"); // The field/method's name
        String to = execTask.getString("to", ""); // The new val
        JsonValue params = execTask.getChild("params");

        switch (type) {
            case "setField":
                setFieldTask(on, name, to);
                break;
            case "execMethod":
                execMethodTask(on, name, params);
                break;
            default:
                throw new IllegalArgumentException("Unknown task " + type);
        }
    }

    private void setFieldTask(String on, String name, String to) {
        Object onObject = params.get(on);
        Object toObject = params.get(to);

        try {
            Field f = onObject.getClass().getField(name);
            f.set(onObject, toObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execMethodTask(String on, String name, JsonValue params) {
        Object onObject = this.params.get(on);
        System.out.println(params.parent.size + " " + onObject + " " + on + " " + name);

        Object[] paramArr = new Object[params.parent.size];
        Class[] paramClasses = new Class[params.parent.size];
        for(int i = 0; i < paramArr.length; i++) {
            Object param;
            if(params.isString()) {
                param = this.params.get(params.asString());
                if(param == null) {
                    param = params.asString();
                }
            } else if(params.isNumber()) {
                param = params.asFloat();
            } else {
                throw new IllegalArgumentException("Couldn't figure out arg " + params);
            }

            paramArr[i] = param;
            paramClasses[i] = param.getClass();
            try {
                Field TYPEField = paramClasses[i].getField("TYPE");
                paramClasses[i] = (Class) TYPEField.get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Non primitive type: " + paramClasses[i]);
            }
            params = params.next;
        }

        try {
            Method m = onObject.getClass().getDeclaredMethod(name, paramClasses);
            m.invoke(onObject, paramArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void parseUserParams() {
        JsonValue params = json.getChild("params");

        for(int i = 0; i < params.size; i++) {
            JsonValue param = params.get(i);
            this.params.put(param.name, null);
        }
    }


    public void setValue(String name, Object value) {
        params.put(name, value);
    }
}
