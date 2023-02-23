package com.gupao.edu.account.server.service;

import com.gupao.edu.account.client.req.EntranceReq;
import com.gupao.edu.account.client.resp.AgreementResp;
import com.gupao.edu.account.client.resp.CardResp;
import com.gupao.edu.account.client.resp.RecommendSalesmanResp;
import com.gupao.edu.account.client.resp.SchoolRollResp;
import com.gupao.edu.common.result.Result;

/**
 * 学籍
 *
 * @author leon
 * @date 2020-04-27 13:51:07
 */
public interface StudentService {

	/**
	 * 根据用户唯一ID 获取学籍信息
	 *
	 * @param userUniqueCode 用户唯一码
	 * @return SchoolRollResp
	 */
	SchoolRollResp schoolRoll(String userUniqueCode);

	/**
	 * 根据用户唯一ID 获取用户黑卡
	 *
	 * @param userUniqueCode 用户唯一码
	 * @return CardResp
	 */
	CardResp card(String userUniqueCode);

	/**
	 * 根据推荐码获取推荐老师
	 *
	 * @param recommendCode 推荐码
	 * @return Result<RecommendSalesmanResp>
	 */
	RecommendSalesmanResp recommendSalesman(String recommendCode);


	/**
	 * 办理入学
	 * @param entranceReq entranceReq
	 * @return Object
	 */
	Result<String> entrance(EntranceReq entranceReq);

	/**
	 * 获取服务协议
	 *
	 * @param userUniqueCode 用户唯一码
	 * @return AgreementResp
	 */
	AgreementResp getAgreement(String userUniqueCode);

	String test();


}
