package go.gg.cms.core.domain;

import go.gg.common.domain.Base;
import go.gg.common.util.DeepfineUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * CMS 기본 도메인 (추상 클래스)
 * @apiNote CMS 내 모든 도메인은 본 추상 도메인을 상속받아 사용 (단, DB object와 mapping 필요 시, Database.java를 상속받아 사용)
 * @apiNote Value Object 시리얼라이징을 위해 Serializable Interface 사용
 * @author jm.lee (DEEP.FINE)
 * @since 2020.07.16
 * @version 1.0.0
 */
public abstract class CmsBase extends Base implements Serializable, Cloneable {

	private static final long serialVersionUID = 3462771383890862656L;

	private Code langCd;
	private Code channelCd;
	private Code nationCd;

	private String traceId;
	private String id;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Code getLangCd() {
		return langCd;
	}

	public void setLangCd(Code langCd) {
		this.langCd = langCd;
	}

	public Code getChannelCd() {
		return channelCd;
	}

	public void setChannelCd(Code channelCd) {
		this.channelCd = channelCd;
	}

	public Code getNationCd() {
		return nationCd;
	}

	public void setNationCd(Code nationCd) {
		this.nationCd = nationCd;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = DeepfineUtils.defaultString(traceId);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void initId() {
		this.id = DeepfineUtils.generateUUID();
	}

}
