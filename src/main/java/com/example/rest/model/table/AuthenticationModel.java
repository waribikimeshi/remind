package com.example.rest.model.table;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.example.rest.model.validated.group.PATCHRequired;
import com.example.rest.model.validated.group.POSTRequired;
import com.example.rest.model.validated.group.PUTRequired;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * モデルクラス
 * @author hanada
 * remark:モデルはstringで統一する。
 *        JPAを使用する為、変数名はキャメル。
 *        ジェネレータで生成
 *        バリデーションのアノテーションはjavaxじゃ効かなかった。jakartaに変えたら効いた。どーなってるのか追えてない。
 *
 */
@Data
public class AuthenticationModel implements Serializable  {

	@NotNull(groups = {PUTRequired.class})
	@ApiModelProperty(value = "主キー")
	private Long id;


	@NotNull(groups = {POSTRequired.class,PUTRequired.class})
	@Email(groups = {POSTRequired.class})
	@ApiModelProperty(value = "メールアドレス")
	private String mailAddress;

	@NotNull(groups = {POSTRequired.class,PUTRequired.class})
	@Pattern(regexp="[a-zA-Z0-9\\\\\\-\\!:\"#\\$%&'\\(\\)\\*\\+,\\-\\./:;<>=\\?@\\[\\]\\^_`\\{\\}\\|~]*"
			, groups = {POSTRequired.class})
	@ApiModelProperty(value = "パスワード")
	private String password;

	@NotNull(groups = {POSTRequired.class,PUTRequired.class})
	@ApiModelProperty(value = "権限")
	private String role;

	@NotNull(groups = {POSTRequired.class,PUTRequired.class})
	@ApiModelProperty(value = "アカウント有効期限")
	private java.sql.Date expirationDate;

	@NotNull(groups = {POSTRequired.class,PUTRequired.class})
	@ApiModelProperty(value = "アカウントロック")
	private Boolean lock;

	@NotNull(groups = {POSTRequired.class,PUTRequired.class})
	@ApiModelProperty(value = "アカウント有効")
	private Boolean enabled;

	
	@NotNull(groups = {POSTRequired.class,PUTRequired.class,PATCHRequired.class})
	@ApiModelProperty(value = "バージョン")
	private Long version;

	@ApiModelProperty(value = "作成者")
	private String createdUser;

	@ApiModelProperty(value = "作成日時")
	private Timestamp createDatetime;

	@ApiModelProperty(value = "最終更新者")
	private String lastModifiedUser;

	@ApiModelProperty(value = "最終更新日時")
	private Timestamp lastModifiedDatetime;

}
