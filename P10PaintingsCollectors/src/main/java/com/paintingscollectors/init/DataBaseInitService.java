package com.paintingscollectors.init;

import javax.annotation.PostConstruct;

public interface DataBaseInitService {
    @PostConstruct
    void init();
}