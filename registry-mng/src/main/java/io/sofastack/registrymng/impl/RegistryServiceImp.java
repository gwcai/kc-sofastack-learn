package io.sofastack.registrymng.impl;

import io.sofastack.registrymng.facade.RegistryService;

public class RegistryServiceImp implements RegistryService {
    @Override
    public String hello(String name) {
        return "hello:" + name;
    }
}
