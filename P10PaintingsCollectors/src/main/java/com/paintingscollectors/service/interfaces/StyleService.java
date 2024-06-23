package com.paintingscollectors.service.interfaces;

import com.paintingscollectors.model.dtos.StyleName;
import com.paintingscollectors.model.entity.Style;

public interface StyleService {
    void dbInit();

    Style findStyleByName(StyleName style);
}
