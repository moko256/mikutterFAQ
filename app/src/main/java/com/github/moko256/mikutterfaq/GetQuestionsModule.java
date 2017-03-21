package com.github.moko256.mikutterfaq;

import java.util.List;

import rx.Observable;

/**
 * Created by moko256 on 2017/03/21.
 */

public interface GetQuestionsModule {
    Observable<List<Question>> getQuestionsObservable();
}
