package com.app.cms.validator;

import org.json.JSONArray;
import org.json.JSONObject;

public class ValidationError {
    private JSONObject base;

    public ValidationError() {
        base = new JSONObject();
        base.put("message","Validation Failed");
    }

    public ValidationError appendDetail(String field, String message) {
        JSONObject error = new JSONObject();
        error.put("field", field);
        error.put("message",message);

        JSONArray errors = base.optJSONArray("errors");
        if(errors == null) {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(error);
            base.put("errors", jsonArray);
        }
        else {
            errors.put(error);
        }

        return this;
    }

    public String toString() {
        return base.toString();
    }
}
