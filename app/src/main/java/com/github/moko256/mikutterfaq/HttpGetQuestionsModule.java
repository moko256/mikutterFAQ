package com.github.moko256.mikutterfaq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by moko256 on 2017/03/21.
 */

public class HttpGetQuestionsModule implements GetQuestionsModule {
    @Override
    public Observable<List<Question>> getQuestionsObservable() {
        Observable<List<Question>> observable = Observable.create(subscriber -> {
            try {
                subscriber.onNext(
                        covertFromJson(new OkHttpClient()
                                .newCall(
                                        new Request.Builder()
                                                .url("http://mikutter.hachune.net/faq.json")
                                                .build()
                                )
                                .execute()
                                .body()
                                .string()
                        )
                );
                subscriber.onCompleted();
            } catch (IOException | JSONException e) {
                subscriber.onError(e);
            }
        });
        return observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    private List<Question> covertFromJson(String jsonString) throws JSONException{
        JSONArray jsonArray = new JSONArray(jsonString);
        ArrayList<Question> result = new ArrayList<>();
        for (int i = 0;i < jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            result.add(new Question() {
                String id = jsonObject.getString("id");
                String question = jsonObject.getString("question");
                String answer = jsonObject.getString("answer");

                @Override
                public String getId() {
                    return id;
                }

                @Override
                public String getQuestion() {
                    return question;
                }

                @Override
                public String getAnswer() {
                    return answer;
                }
            });
        }
        return result;
    }
}
