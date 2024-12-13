package com.example.rest.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.NamingConventions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rest.entity.AuthenticationEntity;
import com.example.rest.exception.DataNotFoundException;
import com.example.rest.model.table.AuthenticationModel;
import com.example.rest.repository.AuthenticationRepository;

/**
 * サービスクラス
 * @author hanada
 * ジェネレータで生成する
 *
 */
@Service
@Transactional(readOnly = true)
public class AuthenticationService {

	public static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

	@Autowired
	private AuthenticationRepository authenticationRepository;


	/**
	 * findById
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public AuthenticationModel findById(AuthenticationModel model)  throws Exception{

		AuthenticationModel result = new AuthenticationModel();

		AuthenticationEntity managedEntity = null;


		//SQL検索
		managedEntity = authenticationRepository.findById(Long.parseLong(model.getId()))
												//対象データなし
												.orElseThrow(() -> new DataNotFoundException());

		//entityからmodelへマッピング。編集が必要な場合はプロパティごとにロジック展開
		result = new ModelMapper().map(managedEntity, AuthenticationModel.class);

		return result;
	}

	/**
	 * findAll
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<AuthenticationModel> findAll()  throws Exception{

		List<AuthenticationModel> resultList = new ArrayList<AuthenticationModel>();

		List<AuthenticationEntity>managedEntityList = null;

		//SQL検索
		managedEntityList = authenticationRepository.findAll();

		//entityからmodelへマッピング。編集が必要な場合はプロパティごとにロジック展開
		Type targetListType = new TypeToken<List<AuthenticationModel>>() {}.getType();
		resultList = new ModelMapper().map(managedEntityList, targetListType);

		return resultList;
	}


	/**
	 * insert
	 * @param model
	 * @return
	 * @throws Exception
	 * @remark DB制約違反はDataIntegrityViolationExceptionが発生する。
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AuthenticationModel insert(AuthenticationModel model)  throws Exception{
		AuthenticationModel result = new AuthenticationModel();

		AuthenticationEntity entity = new ModelMapper().map(model, AuthenticationEntity.class);
		//主キー自動採番の為、クリア
		entity.setId(null);

		//SQL実行
		AuthenticationEntity managedEntity = authenticationRepository.save(entity);

		//entityからmodel
		result = new ModelMapper().map(managedEntity, AuthenticationModel.class);

		return result;

	}

	/**
	 * update
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public AuthenticationModel update(AuthenticationModel model)  throws Exception{
		AuthenticationModel result = new AuthenticationModel();

		//SQL検索
		AuthenticationEntity managedEntity = authenticationRepository.findById(Long.parseLong(model.getId()))
										//対象データなし
										.orElseThrow(() -> new DataNotFoundException());
		//排他チェック
		if(model.getVersion() == null
			|| model.getVersion().equals("")
			|| Long.parseLong(model.getVersion()) == managedEntity.getVersion() == false) {
			throw new OptimisticLockingFailureException(model.getId());
		}

		//modelからentityコピー。null値はコピー対象外
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.NONE);
		modelMapper.map(model, managedEntity);

		//TODO:versionがputでカウントされなかったのでクリア入れる。JPAの仕様調べ
		managedEntity.setVersion(null);
		
		//SQL実行
		managedEntity = authenticationRepository.save(managedEntity);

		//entityからmodel
		result = new ModelMapper().map(managedEntity, AuthenticationModel.class);

		return result;
	}

	/**
	 * delete
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public boolean delete(AuthenticationModel model)  throws Exception{
		boolean result = false;

		//SQL検索
		AuthenticationEntity managedEntity = authenticationRepository.findById(Long.parseLong(model.getId()))
										//対象データなし
										.orElseThrow(() -> new DataNotFoundException());

		//SQL実行
		authenticationRepository.deleteById(managedEntity.getId());

		result = true;

		return result;
	}


}
