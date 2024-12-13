package com.example.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 例外レスポンスモデル
 * @author hanada
 *
 */
@Data
@NoArgsConstructor
public class ExceptionResponseModel  implements Serializable  {

    // 例外HTTPステータスの値
    private int httpstatus;
    // 例外タイトル
    private String title;
    // 例外メッセージリスト
    private List<String> messages = new ArrayList<String>();

    public ExceptionResponseModel(int httpstatus, String title	,List<String> messages) {
		this.httpstatus = httpstatus;
		this.title = title;
		this.messages = messages;
    }

}
