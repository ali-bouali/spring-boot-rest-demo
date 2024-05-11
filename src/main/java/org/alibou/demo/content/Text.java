package org.alibou.demo.content;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;


@Entity
@DiscriminatorValue("TEXT")
@EqualsAndHashCode(callSuper = true)
public class Text extends Content {
  private String text;

}
