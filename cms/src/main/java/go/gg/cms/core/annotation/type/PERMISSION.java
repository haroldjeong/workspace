package go.gg.cms.core.annotation.type;

/**
 * ENUM TYPE for Permission Annotation
 */
public enum PERMISSION {
	READ("R"), WRITE("W"), DOWNLOAD("S"), DELETE("D"), MASTER("M");

	private String value;

	PERMISSION(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
