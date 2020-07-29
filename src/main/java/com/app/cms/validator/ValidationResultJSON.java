package com.app.cms.validator;

import com.app.cms.error.advice.NameAlreadyExistsException;
import org.json.JSONArray;
import org.json.JSONObject;

public class ValidationResultJSON extends JSONObject {

    public void create() {
            JSONObject entity = new JSONObject();
            entity.put("message","Validation Failed");


            JSONObject entity2 = new JSONObject();
            entity2.put("message","Category name is already in use");
            entity2.put("field","name");

            JSONArray errors = new JSONArray();
            errors.put(entity2);


            entity.put("errors",errors);
    }

}

class BaseObject {

    JSONObject base;

    BaseObject create() {
        JSONObject base = new JSONObject();
        base.put("message","Validation Failed");

        return this;
    }

    BaseObject appendError(String message, String field) {
        JSONObject error = new JSONObject();
        error.put("message",message);
        error.put("field", field);

  //      base.optJSONArray("errors").

    //    JSONArray errors = new JSONArray();
   //     errors.put(error);


        return this;
    }

    public String toString() {
        return base.toString();
    }
}
