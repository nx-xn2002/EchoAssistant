package com.bupt.echoassistantbackend.common;

import com.google.gson.JsonObject;

/**
 * 传参构建器
 *
 * @author Ni Xiang
 */
public class ParamBuilder {
        private final JsonObject jsonObject = new JsonObject();

        public ParamBuilder add(String key, String val) {
            this.jsonObject.addProperty(key, val);
            return this;
        }

        public ParamBuilder add(String key, int val) {
            this.jsonObject.addProperty(key, val);
            return this;
        }

        public ParamBuilder add(String key, boolean val) {
            this.jsonObject.addProperty(key, val);
            return this;
        }

        public ParamBuilder add(String key, float val) {
            this.jsonObject.addProperty(key, val);
            return this;
        }

        public ParamBuilder add(String key, JsonObject val) {
            this.jsonObject.add(key, val);
            return this;
        }

        public ParamBuilder add(String key, ParamBuilder val) {
            this.jsonObject.add(key, val.jsonObject);
            return this;
        }

        @Override
        public String toString() {
            return this.jsonObject.toString();
        }
    }