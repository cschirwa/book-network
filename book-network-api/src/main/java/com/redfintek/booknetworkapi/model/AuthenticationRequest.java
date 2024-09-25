package com.redfintek.booknetworkapi.model;


public record AuthenticationRequest (
        String username,
        String password
) {
}
