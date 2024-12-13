package com.example.rest.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

/**
 * エンティティクラス
 * @author aee97
 * remark:エンティティにプリミティブ型使うの厳禁。
 *        null値検索や主キー設定なしでinsertが出来なくなる。
 *        エンティティのフィールド名はキャメルケースのみ。
 *        ＪＰＡがスネークケース対応できてない。
 *        ジェネレータで作成するのでベースは作らない
 *
 */
@Entity
@Table(name = "authentication")
@Data
@EntityListeners(AuditingEntityListener.class)	//監査情報の自動設定
public class AuthenticationEntity implements Serializable{

	/** 主キー */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/** メールアドレス */
	@Column(name = "mail_address")
	private String mailAddress;

	/** パスワード */
	@Column(name = "password")
	private String password;

	/** 権限 */
	@Column(name = "role")
	private String role;

	/** アカウント有効期限 */
	@Column(name = "expiration_date")
	private java.sql.Date expirationDate;

	/** アカウントロック */
	@Column(name = "lock")
	private Boolean lock;

	/** アカウント有効 */
	@Column(name = "enabled")
	private Boolean enabled;

	/** バージョン */
	@Version
	private Long version;

	//TODO:spring securityでログイン認証しないと設定されない
	/** 作成者 */
	@CreatedBy
	@Column(name = "created_user")
	private String createdUser;

	//TODO:spring securityでログイン認証しないと設定されない
	/** 作成日時 */
	@CreatedDate
	@Column(name = "create_datetime")
	private Timestamp createDatetime;

	//TODO:spring securityでログイン認証しないと設定されない
	/** 最終更新者 */
	@LastModifiedBy
	@Column(name = "last_modified_user")
	private String lastModifiedUser;

	//TODO:spring securityでログイン認証しないと設定されない
	/** 最終更新日時 */
	@LastModifiedDate
	@Column(name = "last_modified_datetime")
	private Timestamp lastModifiedDatetime;

}
