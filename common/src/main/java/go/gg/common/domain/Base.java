package go.gg.common.domain;

import java.io.Serializable;

/**
 * Common 기본 도메인 (추상 클래스)
 * @apiNote Common 내 모든 도메인은 본 추상 도메인을 상속받아 사용
 * @apiNote Value Object 시리얼라이징을 위해 Serializable Interface 사용
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
public abstract class Base implements Serializable {

	private static final long serialVersionUID = 380727182756350288L;

	String ipAddr;

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
}
