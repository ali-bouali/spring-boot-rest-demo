package org.alibou.demo.embedded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ComposedId implements Serializable {

    private  String fn;
    private  String ln;
    private Integer x;
}
