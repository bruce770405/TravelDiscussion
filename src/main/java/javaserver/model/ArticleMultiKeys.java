package javaserver.model;

import java.io.Serializable;

/**
 * 
 * @author BruceHsu
 * 
 * @Param Id
 * @Param stepId
 * 
 * 多key連結
 */
public class ArticleMultiKeys implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7075314889231294987L;

	private Long id;
	private Long stepId;

	public ArticleMultiKeys() {
		super();
	}

	public ArticleMultiKeys(Long id, Long stepId) {
		this.id = id;
        this.stepId = stepId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		result = PRIME * result + ((stepId == null) ? 0 : stepId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		final ArticleMultiKeys other = (ArticleMultiKeys) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (stepId == null) {
			if (other.stepId != null) {
				return false;
			}
		} else if (!stepId.equals(other.stepId)) {
			return false;
		}
		
		return true;
	}
}
