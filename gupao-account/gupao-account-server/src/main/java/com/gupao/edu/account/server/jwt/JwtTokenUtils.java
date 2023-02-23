package com.gupao.edu.account.server.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import lombok.Builder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;


/**
 * jwt token生成和 解密 校验
 */
@Slf4j
public class JwtTokenUtils {

	/**
	 * @param secret 加密密钥
	 * @param msg    加密内容
	 * @return
	 */
	public static String creatJwtToken(String secret, String msg) {
		//先对信息进行aes加密(防止被破解）
		msg = new AESUtil(msg).encrypt();
		String token = null;
		try {
			token = JWT.create().withExpiresAt(DateTime.now().plusHours(1).toDate())
					.withClaim("user", msg)
					.sign(Algorithm.HMAC256(secret));
		} catch (Exception e) {
			throw e;
		}
		log.info("加密后：" + token);
		return token;
	}

	/**
	 * 解密jwt并验证是否正确
	 */
	public Result<String> freeJwt(String token, String secret) {
		DecodedJWT decodedJWT = null;
		try {
			//使用hmac256加密算法
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
					.withIssuer("wangzhong")
					.build();
			decodedJWT = verifier.verify(token);
			log.info("签名人：" + decodedJWT.getIssuer() + " 加密方式：" + decodedJWT.getAlgorithm() + " 携带信息：" + decodedJWT.getClaim("user").asString());
		} catch (Exception e) {
			log.info("jwt解密出现错误，jwt或私钥或签证人不正确");
			return Result.fail(CodeMessage.ILLEGAL_TOKEN_ERROR);
		}
		//获得token的头部，载荷和签名，只对比头部和载荷
		String[] headPayload = token.split("\\.");
		//获得jwt解密后头部
		String header = decodedJWT.getHeader();
		//获得jwt解密后载荷
		String payload = decodedJWT.getPayload();
		if (!header.equals(headPayload[0]) && !payload.equals(headPayload[1])) {
			return Result.fail(CodeMessage.ILLEGAL_TOKEN_ERROR);
		}
		return Result.success(new AESUtil(decodedJWT.getClaim("user").asString()).decrypt());
	}

}
