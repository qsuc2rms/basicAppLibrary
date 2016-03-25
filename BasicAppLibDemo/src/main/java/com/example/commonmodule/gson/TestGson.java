package com.example.commonmodule.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class TestGson {  
    
    @SerializedName("first_field")  
    private String field1;  
      
    private String secondField;  
  
    public TestGson(String param1, String param2) {  
        field1 = param1;  
        secondField = param2;  
    }  
}  
