/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
    String token;

    public Token(@JsonProperty("token") String token) {
            this.token = token;
    }

    public String getToken() {
            return token;
    }
}