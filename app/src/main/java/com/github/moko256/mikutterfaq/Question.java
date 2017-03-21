package com.github.moko256.mikutterfaq;

import java.io.Serializable;

/**
 * Created by moko256 on 2017/03/21.
 */

public interface Question extends Serializable {
    String getId();
    String getQuestion();
    String getAnswer();
}