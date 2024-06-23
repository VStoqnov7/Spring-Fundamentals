package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.dtos.StyleName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "styles")
public class Style extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StyleName styleName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "style")
    private List<Painting> paintings;

    public Style(StyleName style, String description) {
        this.styleName = style;
        this.description = description;
    }

    public Style() {
        this.paintings = new ArrayList<>();
    }
}
