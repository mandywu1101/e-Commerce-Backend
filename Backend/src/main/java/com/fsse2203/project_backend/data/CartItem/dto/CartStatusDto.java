package com.fsse2203.project_backend.data.CartItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartStatusDto {

    @JsonProperty("result")
    private String result;

    public CartStatusDto(boolean isResultSuccess) {
        if (isResultSuccess == true){
            this.result = "SUCCESS";
        }
        else {
            this.result = "Failed";
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "result='" + result;
    }
}
